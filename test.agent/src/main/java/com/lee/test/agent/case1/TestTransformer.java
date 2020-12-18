package com.lee.test.agent.case1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

/**
 * @author cdlipan5
 * @create 2020-12-11 下午3:37
 **/
public class TestTransformer implements ClassFileTransformer {

    public static final String classNumberReturns2 = "/Users/cdlipan5/IdeaProjects/practice/test.agent/target/HelloSample#JavaAgent.class";

    public static byte[] getBytesFromFile(String fileName) {
        try {
            // precondition
            File file = new File(fileName);
            InputStream is = new FileInputStream(file);
            long length = file.length();
            byte[] bytes = new byte[(int) length];

            // Read in the bytes
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length
                    && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }

            if (offset < bytes.length) {
                throw new IOException("Could not completely read file "
                        + file.getName());
            }
            is.close();
            return bytes;
        } catch (Exception e) {
            System.out.println("error occurs in _ClassTransformer!"
                    + e.getClass().getName());
            return null;
        }
    }

    public byte[] transform(ClassLoader l, String className, Class<?> c, ProtectionDomain pd, byte[] b) {
//        System.out.println("Load Class: " + className);
        if (!className.equals("com/lee/test/agent/case1/HelloSample")) {
            return null;
        }
        return getBytesFromFile(classNumberReturns2);
    }
}