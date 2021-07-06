package com.sciatta.dev.java.designpattern.behavior.visitor;

import com.sciatta.dev.java.designpattern.behavior.visitor.impl.PdfFile;
import com.sciatta.dev.java.designpattern.behavior.visitor.impl.TxtFile;
import com.sciatta.dev.java.designpattern.behavior.visitor.impl.WordFile;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Visitor 函数重载只支持静态绑定
 */
public interface Visitor {
    void visit(PdfFile pdfFile);
    
    void visit(TxtFile txtFile);
    
    void visit(WordFile wordFile);
}
