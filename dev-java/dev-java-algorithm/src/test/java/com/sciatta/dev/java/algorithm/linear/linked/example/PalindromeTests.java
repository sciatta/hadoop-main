package com.sciatta.dev.java.algorithm.linear.linked.example;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2021/1/19<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * PalindromeTests
 */
public class PalindromeTests {
    @Test
    public void testNoChar() {
        Palindrome<Character> palindrome = new Palindrome<>(new Character[]{});
        boolean test = palindrome.isPalindrome();
        assertFalse(test);
    }
    
    @Test
    public void testOneChar() {
        Palindrome<Character> palindrome = new Palindrome<>(new Character[]{'a'});
        boolean test = palindrome.isPalindrome();
        assertTrue(test);
    }
    
    @Test
    public void testOddChars() {
        Palindrome<Integer> palindrome = new Palindrome<>(new Integer[]{1, 2, 3, 2, 1});
        boolean test = palindrome.isPalindrome();
        assertTrue(test);
    }
    
    @Test
    public void testOddCharsNotPalindrome() {
        Palindrome<Integer> palindrome = new Palindrome<>(new Integer[]{1, 2, 3, 2, 0});
        boolean test = palindrome.isPalindrome();
        assertFalse(test);
    }
    
    @Test
    public void testEvenChars() {
        Palindrome<Integer> palindrome = new Palindrome<>(new Integer[]{1, 2, 2, 1});
        boolean test = palindrome.isPalindrome();
        assertTrue(test);
    }
    
    @Test
    public void testEvenCharsNotPalindrome() {
        Palindrome<Character> palindrome = new Palindrome<>(new Character[]{'a','b','c','a'});
        boolean test = palindrome.isPalindrome();
        assertFalse(test);
    }
}
