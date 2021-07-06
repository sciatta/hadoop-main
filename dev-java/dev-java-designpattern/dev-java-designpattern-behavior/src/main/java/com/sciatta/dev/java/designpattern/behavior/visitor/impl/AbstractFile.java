package com.sciatta.dev.java.designpattern.behavior.visitor.impl;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * AbstractFile
 */
public abstract class AbstractFile {
    protected String filePath;
    
    public AbstractFile(String filePath) {
        this.filePath = filePath;
    }
    
    public String getFilePath() {
        return filePath;
    }
}
