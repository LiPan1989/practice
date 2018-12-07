package com.lee.test.jdk8.chap11;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by LIPAN on 2018/12/7.
 */
public class Util {
    private static final Random RANDOM = new Random(0);

    private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));

    public static void delay() {
        int delay = 1000;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static double format(double number) {
        synchronized (formatter) {
            return new Double(formatter.format(number));
        }
    }

    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
//        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
//        return allDoneFuture.thenApply(y -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
        return CompletableFuture.supplyAsync(() -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
    }
}
