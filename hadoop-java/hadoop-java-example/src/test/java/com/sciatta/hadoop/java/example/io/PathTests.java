package com.sciatta.hadoop.java.example.io;

import org.junit.Before;
import org.junit.Test;

import static com.sciatta.hadoop.java.example.io.AbstractIO.BATH_PATH;
import static com.sciatta.hadoop.java.example.io.AbstractIO.BATH_PATH_NOT_SLASH;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yangxiaoyu on 2019-02-26<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * PathTests
 */
public class PathTests {
    @Before
    public void setWorkDirectory() {
        System.setProperty("user.dir", BATH_PATH_NOT_SLASH);
    }

    @Test
    public void testCreatePath() {
        // 操作路径本身，不访问文件系统
        Path path = Paths.get(BATH_PATH_NOT_SLASH);   // 委托给相应的FileSystem来获取Path
        assertEquals(BATH_PATH_NOT_SLASH, path.toString());

        String p = "//abc";
        path = Paths.get(p);
        assertEquals("/abc", path.toString()); // 去除多余 /

        p = "/abc/a/c/";
        path = Paths.get(p);
        assertEquals("/abc/a/c", path.toString());  // 去除末尾多余 /
    }

    @Test
    public void testAbsolutePath() {
        // 操作路径本身，不访问文件系统
        String p = BATH_PATH_NOT_SLASH;
        Path path = Paths.get(p);

        assertEquals(p, path.toString());
        assertEquals("javadatas", path.getFileName().toString());   // 根路径最远一级的目录或文件
        assertEquals("Users", path.getName(0).toString());  // 以 / 分组，第一个索引为0
        assertEquals(5, path.getNameCount());   // 以 / 分组
        assertEquals("Users/yangxiaoyu", path.subpath(0, 2).toString()); // [0, 2) 不包括根路径
        assertEquals("/Users/yangxiaoyu/work/test", path.getParent().toString());  // 除去FileName的路径
        assertEquals("/", path.getRoot().toString());  // unix的root目录就是 /
    }

    @Test
    public void testRelativePath() {
        // 操作路径本身，不访问文件系统
        String p = "java/test";
        Path path = Paths.get(p);

        assertEquals(p, path.toString());
        assertEquals("test", path.getFileName().toString());
        assertEquals("java", path.getName(0).toString());
        assertEquals(2, path.getNameCount());
        assertEquals("java/test", path.subpath(0, 2).toString());
        assertEquals("java", path.getParent().toString());
        assertNull(path.getRoot()); // 相对路径没有根路径
    }

    @Test
    public void testToAbsolutePath() {
        // 操作路径本身，不访问文件系统
        String p = "/home/rain/java/test";
        // 绝对路径 -> 不变
        assertEquals(p, Paths.get(p).toAbsolutePath().toString());

        p = "test";
        // 相对路径 -> 工作目录/相对路径
        assertEquals(BATH_PATH_NOT_SLASH + File.separator + p, Paths.get(p).toAbsolutePath().toString());
    }

    @Test
    public void testJoinPath() {
        // 操作路径本身，不访问文件系统
        Path p = Paths.get("/home/joe/foo");
        // 传入的是相对路径，不管调用方是相对或绝对路径，都会追加到末尾
        assertEquals("/home/joe/foo/bar", p.resolve("bar").toString());
        // 传入的是绝对路径，不管调用方是相对或绝对路径，都直接返回这个绝对路径
        assertEquals("/home/joe", Paths.get("foo").resolve("/home/joe").toString());
    }

    @Test
    public void testRelativize() {
        // 操作路径本身，不访问文件系统
        Path p1 = Paths.get("a");
        Path p2 = Paths.get("b");
        // 1 cd ..
        // 2 cd b
        assertEquals("../b", p1.relativize(p2).toString()); // p1导航到p2

        p1 = Paths.get("home");
        p2 = Paths.get("home/a/b");
        // 1 cd a
        // 2 cd b
        assertEquals("a/b", p1.relativize(p2).toString());
        // 1 cd ..
        // 2 cd ..
        assertEquals("../..", p2.relativize(p1).toString());
    }

    @Test(expected = IOException.class)
    public void testToRealPathNotExists() throws IOException {
        // 访问文件系统，若不存在则抛出异常
        String p = "file1";    // 不存在

        Paths.get(p).toRealPath();
    }

    @Test
    public void testToRealPathExists() throws IOException {
        // 访问文件系统，若不存在则抛出异常
        String p = "file";    // 存在

        // 相对路径转为绝对路径
        assertEquals(BATH_PATH_NOT_SLASH + File.separator + p, Paths.get(p).toRealPath().toString());
    }

    @Test
    public void testToRealPathResolveLink() throws IOException {
        // 访问文件系统，若不存在则抛出异常
        String p = BATH_PATH + "file/t";

        // 解析符号链接 ln -s source target
        assertEquals(BATH_PATH + "file/test", Paths.get(p).toRealPath().toString());
    }

    @Test
    public void testToRealPathResolveRedundantPath() throws IOException {
        // 访问文件系统，若不存在则抛出异常
        String p = BATH_PATH + "file/" + "./test"; // . 当前目录
        // 解析冗余路径 .
        assertEquals(BATH_PATH + "file/test",
                Paths.get(p).toRealPath().toString());

        // 所有的目录都要存在
        p = BATH_PATH + "file/dir/" + "../test";   // .. 上级目录
        assertEquals(BATH_PATH + "file/test",
                Paths.get(p).toRealPath().toString());
    }
}
