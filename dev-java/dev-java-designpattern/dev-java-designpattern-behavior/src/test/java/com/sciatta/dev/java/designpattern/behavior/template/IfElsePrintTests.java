package com.sciatta.dev.java.designpattern.behavior.template;

import com.sciatta.dev.java.designpattern.behavior.template.compose.IfElsePrint;
import com.sciatta.dev.java.designpattern.behavior.template.inharit.AbstractIfElsePrint;
import com.sciatta.dev.java.designpattern.behavior.template.inharit.IfPrint;
import org.junit.Test;

/**
 * Created by yangxiaoyu on 2021/7/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * IfElsePrintTests
 */
public class IfElsePrintTests {
    @Test
    public void testIfPrint() {
        AbstractIfElsePrint print = new IfPrint();
        print.print();
    }
    
    @Test
    public void testElsePrint() {
        AbstractIfElsePrint print = new AbstractIfElsePrint() {
            @Override
            protected boolean ifElse() {
                return false;
            }
        };
        print.print();
    }
    
    @Test
    public void testIfPrintBaseCompose() {
        IfElsePrint print = new IfElsePrint();
        print.addIfElseListener(new IfElsePrint.IfElseListener() {
            @Override
            public boolean ifElse() {
                return true;
            }
        });
        
        print.print();
    }
}
