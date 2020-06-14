package com.sciatta.hadoop.java.example.io;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;

import static com.sciatta.hadoop.java.example.io.AbstractIO.BATH_PATH;
import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-03-02<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * FileTests
 */
public class FileTests {
    @Test
    public void testCheck() throws IOException {
        // 注意 !exists == notExists 不成立
        // 如果exist是true，表示文件存在；则notExists是false，表示存在或不确定（不能访问文件）
        // 如果exist是false，表示不存在或不确定；则notExists是true，表示不存在
        // exist 和 notExists 对于返回true，表示肯定结果；返回false，有一定的不确定性
        // 目录
        assertTrue(Files.exists(Paths.get(BATH_PATH + "file"))); // true 文件存在；false 不存在或不确定是否存在
        // 文件
        assertTrue(Files.exists(Paths.get(BATH_PATH + "file/test")));
        // 文件
        assertTrue(Files.notExists(Paths.get(BATH_PATH + "file/test1"))); // true 文件不存在；false 存在或不确定是否存在


        // 读、写、可执行属性
        // 文件
        Path pf = Paths.get(BATH_PATH + "file/test");
        assertTrue(Files.isRegularFile(pf));    // 判断是否是文件
        assertTrue(Files.isReadable(pf));
        assertTrue(Files.isWritable(pf));
        assertFalse(Files.isExecutable(pf));
        // 目录
        Path pd = Paths.get(BATH_PATH + "file");
        assertFalse(Files.isRegularFile(pd));
        assertTrue(Files.isReadable(pd));
        assertTrue(Files.isWritable(pd));
        assertTrue(Files.isExecutable(pd));

        // Checking Whether Two Paths Locate the Same File
        // ln -s 创建软连接
        Path p1 = Paths.get(BATH_PATH + "file/t"); // link
        Path p2 = Paths.get(BATH_PATH + "file/test"); // actual file
        assertTrue(Files.isSameFile(p1, p2));
    }

    @Test(expected = IOException.class)
    public void testDeleteHaveException() throws IOException {
        Path p = Paths.get(BATH_PATH + "file/test1");
        Files.delete(p);    //抛出异常 1、文件不存在；2、目录不为空；3、其他IO失败
    }

    @Test
    public void testDeleteWithSilence() throws IOException {
        Path p = Paths.get(BATH_PATH + "file/test1");
        assertFalse(Files.deleteIfExists(p));    // 不存在静默，返回删除结果
    }

    @Test
    public void testCopy() throws IOException {
        // 文件
        Path sourceFile = Paths.get(BATH_PATH + "file/test");
        Path targetFile = Paths.get(BATH_PATH + "file/test1");

        // 目录
        Path sourceDir = Paths.get(BATH_PATH + "file/dir");
        Path targetDir = Paths.get(BATH_PATH + "file/dir1");

        try {
            // 拷贝文件 拷贝symbolic link，则指向的文件被拷贝。使用NOFOLLOW_LINK来拷贝symbolic link本身
            targetFile = Files.copy(sourceFile, targetFile);
            // 拷贝目录 目录内的文件和目录不能拷贝，只能拷贝顶级目录（空目录）
            targetDir = Files.copy(sourceDir, targetDir);

            // 判断存在
            assertTrue(Files.exists(targetFile));
            assertTrue(Files.exists(targetDir));
        } finally {
            // 清理
            Files.deleteIfExists(targetFile);
            Files.deleteIfExists(targetDir);

            // 判断不存在
            assertTrue(Files.notExists(targetFile));
            assertTrue(Files.notExists(targetDir));
        }
    }

    @Test
    public void testMove() throws IOException {
        // 文件
        Path sourceFile = Paths.get(BATH_PATH + "file/test");
        Path targetFile = Paths.get(BATH_PATH + "file/test1");

        // 目录
        Path sourceDir = Paths.get(BATH_PATH + "file/dir");
        Path targetDir = Paths.get(BATH_PATH + "file/dir1");

        try {
            // 文件改名
            targetFile = Files.move(sourceFile, targetFile);
            assertTrue(Files.exists(targetFile));
            assertTrue(Files.notExists(sourceFile));

            // 目录改名，内部不变
            targetDir = Files.move(sourceDir, targetDir);
            assertTrue(Files.exists(targetDir));
            assertTrue(Files.notExists(sourceDir));

        } finally {
            // 还原
            Files.move(targetFile, sourceFile);
            assertTrue(Files.notExists(targetFile));
            assertTrue(Files.exists(sourceFile));

            Files.move(targetDir, sourceDir);
            assertTrue(Files.notExists(targetDir));
            assertTrue(Files.exists(sourceDir));
        }
    }

    @Test
    public void testMetaData() throws IOException {
        Path file = Paths.get(BATH_PATH + "file/test");
        Path directory = Paths.get(BATH_PATH + "file/dir");

        // 单一属性
        System.out.println(Files.getOwner(file));
        System.out.println(Files.getLastModifiedTime(directory));

        System.out.println();

        // 为提高性能，同时获取多个属性
        System.out.println(Files.readAttributes(file, "posix:owner,group"));

        System.out.println();

        // BasicFileAttributes类型的属性对象
        BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println(attrs.creationTime());
        System.out.println(attrs.lastModifiedTime());
        System.out.println(attrs.lastAccessTime());

        System.out.println();

        // PosixFileAttributes
        PosixFileAttributes pfa = Files.readAttributes(file, PosixFileAttributes.class);
        System.out.format("%s %s %s%n",
                pfa.owner().getName(),
                pfa.group().getName(),
                PosixFilePermissions.toString(pfa.permissions()));
    }

    @Test
    public void testReadAndWriteFileByLine() throws IOException {
        Path readPath = Paths.get(BATH_PATH + "file/test");
        Path writePath = Paths.get(BATH_PATH + "file/test1");

        try {
            List<String> readStr = Files.readAllLines(readPath);
            assertEquals("hadoop", readStr.get(0));

            Files.write(writePath, readStr, StandardOpenOption.CREATE_NEW); // 创建一个新文件，存在则失败
            assertTrue(Files.exists(writePath));

            List<String> writeStr = Files.readAllLines(writePath);
            assertEquals("hadoop", writeStr.get(0));

            assertArrayEquals(readStr.toArray(), writeStr.toArray());
        } finally {
            Files.delete(writePath);
        }
    }

    @Test
    public void testListDirectoryContents() throws IOException {
        Path bathPath = Paths.get(BATH_PATH + "file");

        // 只能列举子目录和文件
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(bathPath)) {
            for (Path path : ds) {
                System.out.println(path);
            }
        }

        System.out.println();

        // 只列举文件
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(bathPath, p -> Files.isRegularFile(p))) {
            for (Path path : ds) {
                System.out.println(path);
            }
        }

        System.out.println();

        // 只列举目录
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(bathPath, p -> Files.isDirectory(p))) {
            for (Path path : ds) {
                System.out.println(path);
            }
        }
    }

    @Test
    public void testVisitFile() throws IOException {
        Path p = Paths.get(BATH_PATH);
        PathMatcher pm = FileSystems.getDefault().getPathMatcher("glob:*t*"); // glob 匹配

        // 先遍历当前目录的文件，再遍历子目录
        Files.walkFileTree(p, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("before visit directory:" + dir);
                if (dir.getFileName().toString().equals("com")) {
                    return FileVisitResult.SKIP_SUBTREE;
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (pm.matches(file.getFileName())) {
                    System.out.println("current file is " + file);
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println(exc.getMessage());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("after visit directory:" + dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
