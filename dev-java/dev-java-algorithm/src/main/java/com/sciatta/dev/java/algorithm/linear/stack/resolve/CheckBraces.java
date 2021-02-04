package com.sciatta.dev.java.algorithm.linear.stack.resolve;

import com.sciatta.dev.java.algorithm.linear.stack.impl.LinkedListStack;

/**
 * Created by yangxiaoyu on 2021/2/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * CheckBraces
 */
public class CheckBraces {
    private char[] chars;
    private LinkedListStack<Character> helpStack = new LinkedListStack<>();
    
    public CheckBraces(char[] chars) {
        this.chars = chars;
    }
    
    public boolean isValid() {
        if (chars.length == 0) return false;
        
        for (char c : chars) {
            if (isLeftBrace(c)) {
                helpStack.push(c);
            } else if (isRightBrace(c)) {
                Character left = helpStack.pop();
                if (!doCheckBrace(left, c)) {
                    return false;
                }
            } else {
                // 非法字符
                return false;
            }
        }
        
        return helpStack.getCount() == 0;
    }
    
    private boolean doCheckBrace(char left, char right) {
        return (left == '(' && right == ')') || (left == '[' && right == ']') || (left == '{' && right == '}');
    }
    
    private boolean isLeftBrace(char c) {
        return c == '(' || c == '[' || c == '{';
    }
    
    private boolean isRightBrace(char c) {
        return c == ')' || c == ']' || c == '}';
    }
}
