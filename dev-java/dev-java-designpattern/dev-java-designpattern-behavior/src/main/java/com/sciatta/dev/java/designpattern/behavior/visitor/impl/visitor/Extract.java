package com.sciatta.dev.java.designpattern.behavior.visitor.impl.visitor;

import com.sciatta.dev.java.designpattern.behavior.visitor.Visitor;
import com.sciatta.dev.java.designpattern.behavior.visitor.impl.PdfFile;
import com.sciatta.dev.java.designpattern.behavior.visitor.impl.TxtFile;
import com.sciatta.dev.java.designpattern.behavior.visitor.impl.WordFile;

/**
 * Created by yangxiaoyu on 2021/7/6<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * Extract
 */
public class Extract implements Visitor {
    @Override
    public void visit(PdfFile pdfFile) {
        System.out.println("PdfFile Extract " + pdfFile.getFilePath());
    }
    
    @Override
    public void visit(TxtFile txtFile) {
        System.out.println("TxtFile Extract " + txtFile.getFilePath());
    }
    
    @Override
    public void visit(WordFile wordFile) {
        System.out.println("WordFile Extract " + wordFile.getFilePath());
    }
}
