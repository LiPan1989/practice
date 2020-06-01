package com.lee.test.netty;

import java.nio.ByteBuffer;

public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(88);
        System.out.println(buffer);

        String value = "Netty权威指南";
        buffer.put(value.getBytes());
        System.out.println(buffer);

        buffer.flip();
        System.out.println(buffer);

        byte[] v = new byte[buffer.remaining()];
        buffer.get(v);

        System.out.println(buffer);
        System.out.println(new String(v));
    }
}
