package com.lee.test.normal.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author cdlipan5
 * @create 2019-12-25 下午2:23
 **/
public class CustomClassLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try (InputStream in = new FileInputStream(name)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            int i = 0;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
            byte[] byteArray = out.toByteArray();
            return defineClass(byteArray, 0, byteArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
