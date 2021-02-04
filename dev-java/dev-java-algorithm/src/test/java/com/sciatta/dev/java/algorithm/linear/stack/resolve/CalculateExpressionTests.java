package com.sciatta.dev.java.algorithm.linear.stack.resolve;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by yangxiaoyu on 2021/2/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CalculateExpressionTests
 */
public class CalculateExpressionTests {
    @Test
    public void testCheckOperand() {
        boolean test = CalculateExpression.checkOperand("0");
        Assert.assertTrue(test);
        
        test = CalculateExpression.checkOperand("1");
        Assert.assertTrue(test);
        
        test = CalculateExpression.checkOperand("9");
        Assert.assertTrue(test);
    }
    
    @Test
    public void testCheckOperandInvalid() {
        boolean test = CalculateExpression.checkOperand("99");
        Assert.assertFalse(test);
        
        test = CalculateExpression.checkOperand("10");
        Assert.assertFalse(test);
        
        test = CalculateExpression.checkOperand("a");
        Assert.assertFalse(test);
    }
    
    @Test
    public void testCheckOperator() {
        boolean test = CalculateExpression.checkOperator("+");
        Assert.assertTrue(test);
        
        test = CalculateExpression.checkOperator("-");
        Assert.assertTrue(test);
        
        test = CalculateExpression.checkOperator("*");
        Assert.assertTrue(test);
        
        test = CalculateExpression.checkOperator("/");
        Assert.assertTrue(test);
    }
    
    @Test
    public void testCheckOperatorInvalid() {
        boolean test = CalculateExpression.checkOperator("1");
        Assert.assertFalse(test);
        
        test = CalculateExpression.checkOperator("a");
        Assert.assertFalse(test);
        
        test = CalculateExpression.checkOperator("%");
        Assert.assertFalse(test);
    }
    
    @Test
    public void testGetChars() {
        String[] chars = CalculateExpression.getChars("1 + 2");
        String test = String.join("", chars);
        Assert.assertEquals(3, chars.length);
        Assert.assertEquals("1+2", test);
        
        chars = CalculateExpression.getChars("1 +      2");
        test = String.join("", chars);
        Assert.assertEquals(3, chars.length);
        Assert.assertEquals("1+2", test);
        
        chars = CalculateExpression.getChars("1 +      2*4-2");
        test = String.join("", chars);
        Assert.assertEquals(7, chars.length);
        Assert.assertEquals("1+2*4-2", test);
    }
    
    @Test
    public void testCalculate() {
        CalculateExpression cal = new CalculateExpression("1+2");
        int result = cal.calculate();
        Assert.assertEquals(3, result);
        
        cal = new CalculateExpression("1+ 2");
        result = cal.calculate();
        Assert.assertEquals(3, result);
        
        cal = new CalculateExpression("1+2*3+2");
        result = cal.calculate();
        Assert.assertEquals(9, result);
        
        cal = new CalculateExpression("1+2*3+2/2");
        result = cal.calculate();
        Assert.assertEquals(8, result);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateHasInvalidChar() {
        CalculateExpression cal = new CalculateExpression("1&2");
        cal.calculate();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateHasInvalidExpression() {
        CalculateExpression cal = new CalculateExpression("1++2");
        cal.calculate();
    }
}
