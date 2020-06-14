package com.sciatta.hadoop.java.example.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.sciatta.hadoop.java.example.io.AbstractIO.BATH_PATH;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yangxiaoyu on 2019-02-23<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * IOStreamTests
 */
public class IOStreamTests {
    private static final String INPUT_FILENAME = BATH_PATH + "file/test";
    private static final String OUTPUT_FILENAME = BATH_PATH + "file/test_out";
    private static final Path INPUT_PATH = Paths.get(INPUT_FILENAME).toAbsolutePath();
    private static final Path OUTPUT_PATH = Paths.get(OUTPUT_FILENAME).toAbsolutePath();

    public void init() throws IOException {
        Files.createFile(OUTPUT_PATH);
    }

    public void destroy() throws IOException {
        Files.delete(OUTPUT_PATH);
    }

    @Test
    public void testCopyBytesFromFile() throws IOException {
        init();
        IOStream.copyBytesFromFile(INPUT_PATH.toString(), OUTPUT_PATH.toString());
        assertEquals("hadoop", Files.readAllLines(OUTPUT_PATH).get(0));
        destroy();
    }

    @Test
    public void testCopyCharactersFromFile() throws IOException {
        init();
        IOStream.copyCharactersFromFile(INPUT_PATH.toString(), OUTPUT_PATH.toString());
        assertEquals("hadoop", Files.readAllLines(OUTPUT_PATH).get(0));
        destroy();
    }

    @Test
    public void testCopyBytesFromByteArray() throws IOException {
        String from = "Hello";
        String to = IOStream.copyBytesFromString("Hello");
        assertEquals(from, to);
    }

    @Test
    public void testCopyCharactersFromCharArray() throws IOException {
        String from = "Hello";
        String to = IOStream.copyCharactersFromString("Hello");
        assertEquals(from, to);
    }

    @Test
    public void testReplaceTwoNumberAsOneAsterisk() throws IOException {
        String from = "123ab3c";
        String to = IOStream.replaceTwoNumberAsOneAsterisk(from);
        assertEquals("*3ab3c", to);

        from = "1a3ab3c";
        to = IOStream.replaceTwoNumberAsOneAsterisk(from);
        assertEquals("1a3ab3c", to);

        from = "113ab33";
        to = IOStream.replaceTwoNumberAsOneAsterisk(from);
        assertEquals("*3ab*", to);
    }
}
