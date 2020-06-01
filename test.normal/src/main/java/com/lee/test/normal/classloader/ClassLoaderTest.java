package com.lee.test.normal.classloader;

import sun.misc.Launcher;

import java.net.URL;

public class ClassLoaderTest {
    public static void main(String[] args) {
        printClassLoader();
    }

    private static void getBootstrap() {
        URL[] urls = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());

        }

        System.out.println(System.getProperty("sun.boot.class.path"));
    }

    private static void getExtensionClass() {

    }

    private static void getAppClass() {

    }

    private static void printClassLoader() {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();
        while (loader != null) {
            System.out.println(loader);
            loader = loader.getParent();
        }
        System.out.println(loader);
    }
}
