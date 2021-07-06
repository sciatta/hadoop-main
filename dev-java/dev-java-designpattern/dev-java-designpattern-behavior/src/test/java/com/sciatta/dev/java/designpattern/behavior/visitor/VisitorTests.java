package com.sciatta.dev.java.designpattern.behavior.visitor;

import com.sciatta.dev.java.designpattern.behavior.visitor.impl.FileFactory;
import com.sciatta.dev.java.designpattern.behavior.visitor.impl.PdfFile;
import com.sciatta.dev.java.designpattern.behavior.visitor.impl.visitor.Compress;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * VisitorTests
 */
public class VisitorTests {
    @Test
    public void testCompress() {
        Visitor visitor = new Compress();
        Visitable visitable = null;
        
        try {
            visitable = FileFactory.getFile("abc.txt");
            visitable.accept(visitor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testExtract() {
        Visitor visitor = new Compress();
        Visitable visitable = null;
        
        try {
            visitable = FileFactory.getFile("xyz.pdf");
            visitable.accept(visitor);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
