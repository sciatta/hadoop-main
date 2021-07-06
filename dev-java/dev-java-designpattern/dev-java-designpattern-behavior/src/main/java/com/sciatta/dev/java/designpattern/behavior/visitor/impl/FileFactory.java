package com.sciatta.dev.java.designpattern.behavior.visitor.impl;

import com.sciatta.dev.java.designpattern.behavior.visitor.Visitable;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * FileFactory
 */
public class FileFactory {
    private static Map<String, Class<?>> fileTypes = new HashMap<>();
    
    static {
        fileTypes.put("pdf", PdfFile.class);
        fileTypes.put("txt", TxtFile.class);
        fileTypes.put("word", WordFile.class);
    }
    
    public static Visitable getFile(String filePath)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        
        String suffix = getSuffix(filePath);
        
        Class<?> fileType = fileTypes.get(suffix);
        return (Visitable) fileType.getConstructor(String.class).newInstance(filePath);
    }
    
    private static String getSuffix(String filePath) {
        int index = filePath.lastIndexOf(".");
        String suffix = filePath.substring(index + 1, filePath.length());
        return suffix;
    }
}
