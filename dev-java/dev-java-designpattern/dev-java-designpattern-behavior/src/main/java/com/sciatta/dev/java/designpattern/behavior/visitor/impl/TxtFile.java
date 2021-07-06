package com.sciatta.dev.java.designpattern.behavior.visitor.impl;

import com.sciatta.dev.java.designpattern.behavior.visitor.Visitable;
import com.sciatta.dev.java.designpattern.behavior.visitor.Visitor;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * TxtFile
 */
public class TxtFile extends AbstractFile implements Visitable {
    public TxtFile(String filePath) {
        super(filePath);
    }
    
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
