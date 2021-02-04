package com.sciatta.dev.java.algorithm.linear.stack.resolve;

import com.sciatta.dev.java.algorithm.linear.stack.impl.LinkedListStack;

import java.util.Arrays;

/**
 * Created by yangxiaoyu on 2021/2/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CalculateExpression
 */
public class CalculateExpression {
    private LinkedListStack<String> operandStack = new LinkedListStack<>();
    private LinkedListStack<String> operatorStack = new LinkedListStack<>();
    private String[] chars;
    
    public CalculateExpression(String str) {
        this.chars = getChars(str);
    }
    
    public int calculate() {
        for (String c : chars) {
            if (checkOperand(c)) {
                // 操作数
                operandStack.push(c);
            } else if (checkOperator(c)) {
                // 操作符
                if (operatorStack.getCount() == 0) {
                    // 空栈，操作符入栈
                    operatorStack.push(c);
                } else {
                    // 同操作符栈顶比较操作符优先级
                    int priority = compare(c, operatorStack.peek());
                    
                    if (priority < 0) {
                        // 栈顶优先级高
                        String operand2 = operandStack.pop();
                        String operand1 = operandStack.pop();
                        String operator = operatorStack.pop();
                        String result = doCalculate(operand1, operand2, operator);
                        operandStack.push(result);  // 计算结果入栈
                        operatorStack.push(c);  // 操作符入栈
                    } else {
                        // 栈顶优先级低，相等
                        operatorStack.push(c);
                    }
                }
                
            } else {
                throw new IllegalArgumentException(String.join(" ", chars) + " is invalid!");
            }
        }
        
        while (operatorStack.getCount() != 0) {
            String operand2 = operandStack.pop();
            String operand1 = operandStack.pop();
            
            if (operand1 == null || operand2 == null) {
                throw new IllegalArgumentException(String.join(" ", chars) + " is invalid!");
            }
            
            String operator = operatorStack.pop();
            String result = doCalculate(operand1, operand2, operator);
            operandStack.push(result);  // 计算结果入栈
        }
        
        return Integer.parseInt(operandStack.pop());
    }
    
    private String doCalculate(String operand1, String operand2, String operator) {
        if (operator.equals("+")) {
            int result = Integer.parseInt(operand1) + Integer.parseInt(operand2);
            return Integer.toString(result);
        } else if (operator.equals("-")) {
            int result = Integer.parseInt(operand1) - Integer.parseInt(operand2);
            return Integer.toString(result);
        } else if (operator.equals("*")) {
            int result = Integer.parseInt(operand1) * Integer.parseInt(operand2);
            return Integer.toString(result);
        } else {
            int result = Integer.parseInt(operand1) / Integer.parseInt(operand2);
            return Integer.toString(result);
        }
    }
    
    private int compare(String c, String t) {
        int result = 0;
        
        if ((c.equals("+") || c.equals("-")) && (t.equals("*") || t.equals("/"))) {
            result = -1;
        } else if ((c.equals("*") || c.equals("/")) && (t.equals("+") || t.equals("-"))) {
            result = 1;
        }
        
        return result;
    }
    
    public static boolean checkOperand(String c) {
        return c.matches("[0-9]");
    }
    
    public static boolean checkOperator(String c) {
        return c.matches("[\\+\\-\\*\\/]");
    }
    
    public static String[] getChars(String str) {
        return Arrays.stream(str.split("")).filter(s -> !s.trim().equals("")).toArray(String[]::new);
    }
}