package com.lee.test.jdk8.chap11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.lee.test.jdk8.chap11.Util.delay;
import static com.lee.test.jdk8.chap11.Util.format;

/**
 * Created by LIPAN on 2018/12/8.
 */
public class AsyncShop {
    private final String name;
    private final Random random;

    public AsyncShop(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public Future<Double> getPrice(String product) {
        /*
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = 0;
            try {
                price = calculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception e) {
                futurePrice.completeExceptionally(e);
            }
        });
        return futurePrice;
        */
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    private double calculatePrice(String product) {
        delay();
        if (true) {
            throw new RuntimeException("Product not avaliable");
        }
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }
}
