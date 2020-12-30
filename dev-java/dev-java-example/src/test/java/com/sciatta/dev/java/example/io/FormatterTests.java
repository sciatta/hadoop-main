package com.sciatta.dev.java.example.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by yangxiaoyu on 2019-03-05<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * FormatterTests
 */
public class FormatterTests {
    @Test
    public void testFormatOutput() throws IOException {
        // 以指定分隔符读入数据，默认为whitespace
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(AbstractIO.BATH_PATH + "file/test")));

        while (scanner.hasNext()) {
            System.out.println(scanner.next()); // PrintStream
        }
    }

    @Test
    public void testFormatOutputUseLineNumber() throws IOException {
        Scanner scanner = new Scanner(new BufferedReader(new FileReader(AbstractIO.BATH_PATH + "file/test")));
        int i = 1;
        while (scanner.hasNext()) {
            String next = scanner.next();
            System.out.format("%-5d%-20s%d%n", i++, next, next.length());// PrintStream   %n平台相关回车
        }
    }
}
