package com.sciatta.dev.java.algorithm.linear.stack.resolve;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yangxiaoyu on 2021/2/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CheckBracesTests
 */
public class CheckBracesTests {
    @Test
    public void testEmpty() {
        CheckBraces checkBraces = new CheckBraces(new char[]{});
        boolean test = checkBraces.isValid();
        Assert.assertFalse(test);
    }
    
    @Test
    public void testOneChar() {
        CheckBraces checkBraces = new CheckBraces(new char[]{'('});
        boolean test = checkBraces.isValid();
        Assert.assertFalse(test);
    }
    
    @Test
    public void testValid() {
        CheckBraces checkBraces = new CheckBraces(new char[]{'(', '(', '{', '{', '[', ']', '}', '}', ')', ')'});
        boolean test = checkBraces.isValid();
        Assert.assertTrue(test);
    }
    
    @Test
    public void testInvalid() {
        CheckBraces checkBraces = new CheckBraces(new char[]{'(', '(', '{', '{', '[', '}', '}', '}', ')', ')'});
        boolean test = checkBraces.isValid();
        Assert.assertFalse(test);
    }
    
    @Test
    public void testInvalidAsOddNumber() {
        CheckBraces checkBraces = new CheckBraces(new char[]{'(', '(', ')'});
        boolean test = checkBraces.isValid();
        Assert.assertFalse(test);
    }
    
}
