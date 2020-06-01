package com.lee.test.netty;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BufferTest {
    public static void main(String[] args) throws IOException {
        String file = "g:\\Desktop\\gc.log";
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");
        FileChannel fileChannel = accessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        int byteRead = fileChannel.read(buffer);
        while (byteRead != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char)buffer.get());
            }
            System.out.println();
            buffer.rewind();
            buffer.mark();
            buffer.clear();
            byteRead = fileChannel.read(buffer);
        }
    }
}
