package com.lee.test.jdk8.chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by LIPAN on 2018/11/18.
 */
public class ExecuteAround {
    public static void main(String[] args) throws IOException {
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("-------------------");

        String oneLine = processFile(BufferedReader::readLine);
        System.out.println(oneLine);

        String oneLine2 = processFile(bufferedReader -> bufferedReader.readLine());

        System.out.println(oneLine2);

        String twoLines = processFile(bufferedReader -> bufferedReader.readLine() + bufferedReader.readLine());
        System.out.println(twoLines);

        System.out.println();
    }

    public static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("lambdainaction/chap3/data.txt"))) {
            return br.readLine();
        }
    }

    public static String processFile(BufferedReaderProcessor p) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader("lambdainaction/chap3/data/txt"))) {
            return p.process(br);
        }
    }

    public interface BufferedReaderProcessor {
        String process(BufferedReader bufferedReader) throws IOException;
    }

}
