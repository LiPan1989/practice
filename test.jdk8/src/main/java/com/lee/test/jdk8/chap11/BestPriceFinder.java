package com.lee.test.jdk8.chap11;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by LIPAN on 2018/12/7.
 */
public class BestPriceFinder {
    private final List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"),
            new Shop("ShopEasy")
    );

    private final Executor executor = Executors.newFixedThreadPool(shops.size(), r -> {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    });

    public List<String> findPricesSequential(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applayDiscount)
                .collect(Collectors.toList());
    }

    public List<String> findPricesParallel(String product) {
        return shops.stream()
                .map(shop -> shop.getPrice(product))
                .map(Quote::parse)
                .map(Discount::applayDiscount)
                .collect(Collectors.toList());
    }

    public List<String> findPricesFuture(String product) {
        List<CompletableFuture<String>> pricesFutures = findPricesStream(product).collect(Collectors.toList());
        return pricesFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    public Stream<CompletableFuture<String>> findPricesStream(String product) {
        return shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(quote -> CompletableFuture.supplyAsync(() -> Discount.applayDiscount(quote), executor)));
    }

    public void printPricesStream(String product) {
        long start = System.nanoTime();
        CompletableFuture[] futures = findPricesStream(product)
                .map(f -> f.thenAccept(s -> System.out.println(s + " done in " + ((System.nanoTime() - start) / 1_000_000) + "msecs")))
                .toArray(size -> new CompletableFuture[size]);
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have now responded in " + ((System.nanoTime() - start) / 1_000_000) + "msecs");
    }
}
