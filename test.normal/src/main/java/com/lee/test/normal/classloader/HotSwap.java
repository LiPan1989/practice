package com.lee.test.normal.classloader;

import com.lee.test.normal.classloader.cases.HelloWorld;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.TimeUnit;

/**
 * @author cdlipan5
 * @create 2019-12-27 下午3:18
 **/
public class HotSwap {

    /**
     * desClazz
     */
    private static Class desClazz = HelloWorld.class;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InterruptedException {
        String filePath = "/Users/cdlipan5/IdeaProjects/practice/test.normal/src/main/java/com/lee/test/normal/classloader/cases/";
        String fileName = "HelloWorld.class";
        listen(filePath, fileName);
        while (true) {
            System.out.println("==============================");
            System.out.println(desClazz.hashCode());
            HelloWorld helloWorld = (HelloWorld) desClazz.newInstance();


            System.out.println("==============================");
            TimeUnit.SECONDS.sleep(1L);
        }
    }

    private static void listen(String filePath, String fileName) {
        new Thread(() -> {
            try {
                FileSystem fileSystem = FileSystems.getDefault();
                WatchService watcher = fileSystem.newWatchService();
                File file = new File(filePath + fileName);
                if (!file.exists()) {
                    return;
                }
                Path myDir = fileSystem.getPath(filePath);
                myDir.register(watcher, StandardWatchEventKinds.ENTRY_MODIFY);
                WatchKey watchKey = null;
                while (true) {
                    watchKey = watcher.take();
                    for (WatchEvent<?> event : watchKey.pollEvents()) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                            reloadFile(file);
                        }
                    }

                    boolean reset = watchKey.reset();
                    if (!reset)
                        break;
                }
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void reloadFile(File newFile) {
        HotSwapClassLoader hotSwapClassLoader = new HotSwapClassLoader();
        hotSwapClassLoader.setFile(newFile);
        try {
            Class<?> objClass = hotSwapClassLoader.findClass("com.lee.test.normal.classloader.cases.HelloWorld");
            desClazz = objClass;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
