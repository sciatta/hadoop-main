package com.sciatta.dev.java.designpattern.structure.composite;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/6/25<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FileSystemTests
 */
public class FileSystemTests {
    @Test
    public void testFindFileFromDirectory() {
        FileSystem fileSystem = new FileSystem(buildDirectory());
        boolean test = fileSystem.findFile("file212");
        assertTrue(test);
        
        test = fileSystem.findFile("file222");
        assertFalse(test);
    }
    
    @Test
    public void testFindFileFromFile() {
        FileSystem fileSystem = new FileSystem(new File("abc"));
        boolean test = fileSystem.findFile("abc");
        assertTrue(test);
    }
    
    @Test
    public void testCountFileFromDirectory() {
        FileSystem fileSystem = new FileSystem(buildDirectory());
        assertEquals(3, fileSystem.countFile());
        assertEquals(3, fileSystem.countFile());
    }
    
    @Test
    public void testCountFileFromFile() {
        FileSystem fileSystem = new FileSystem(new File("abc"));
        assertEquals(1, fileSystem.countFile());
    }
    
    @Test
    public void testCountNodeFromDirectory() {
        FileSystem fileSystem = new FileSystem(buildDirectory());
        assertEquals(7, fileSystem.countNode());
        assertEquals(7, fileSystem.countNode());
    }
    
    @Test
    public void testCountNodeFromFile() {
        FileSystem fileSystem = new FileSystem(new File("abc"));
        assertEquals(1, fileSystem.countNode());
    }
    
    private Node buildDirectory() {
        Directory root = new Directory("root");
        
        Directory dir21 = new Directory("dir21");
        Directory dir22 = new Directory("dir22");
        root.addNode(dir21);
        root.addNode(dir22);
        
        Directory dir211 = new Directory("dir211");
        File file211 = new File("file211");
        File file212 = new File("file212");
        dir21.addNode(dir211);
        dir21.addNode(file211);
        dir21.addNode(file212);
        
        File file221 = new File("file221");
        dir22.addNode(file221);
        
        return root;
    }
}
