package com.sciatta.dev.java.example.character;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-02-12<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * APITests
 */
public class APITests {
    @Test
    public void testConcept() {
        // UnicodeBlock
        // 是Subset的子类，代表Unicode规范中的字符块，从编码角度将Unicode划分为若干子集。如：0000-007F	基本拉丁文；属于0号平面。
        Character.UnicodeBlock ub = Character.UnicodeBlock.of('A');
        assertEquals("BASIC_LATIN", ub.toString());
        
        ub = Character.UnicodeBlock.of('汉');
        assertEquals("CJK_UNIFIED_IDEOGRAPHS", ub.toString());
        
        ub = Character.UnicodeBlock.of('，');
        assertEquals("HALFWIDTH_AND_FULLWIDTH_FORMS", ub.toString());
        
        ub = Character.UnicodeBlock.of(',');
        assertEquals("BASIC_LATIN", ub.toString());
        
        // UnicodeScript
        // 从应用角度将Unicode划分为若干子集。每一个Unicode只属于唯一一个UnicodeScript。
        Character.UnicodeScript us = Character.UnicodeScript.of('A');
        assertEquals("LATIN", us.toString());
        
        us = Character.UnicodeScript.of('#');
        assertEquals("COMMON", us.toString());
    }
    
    @Test
    public void testCharacter() {
        // autoboxing
        Character character = 'h';
        assertEquals('h', character.charValue());
        
        // unboxing
        char c = new Character('h');
        assertEquals('h', c);
        
        // 缓存128位ASCII码
        assertSame(Character.valueOf('A'), Character.valueOf('A'));
        assertNotSame(new Character('A'), new Character('A'));
        
        // 有效的codepoint
        assertTrue(Character.isValidCodePoint(0));
        assertTrue(Character.isValidCodePoint(1_114_111));
        assertFalse(Character.isValidCodePoint(1_114_112));
        
        // 判读第一平面
        // Basic Multilingual Plane (BMP)
        assertTrue(Character.isBmpCodePoint('0'));
        assertTrue(Character.isBmpCodePoint(65535));    // 0~65536 第0平面
        assertFalse(Character.isBmpCodePoint(65536));
        
        // 非第一平面
        // supplementary character
        assertTrue(Character.isSupplementaryCodePoint(65536));
        
        // 字符个数，最多两个字符
        assertEquals(1, Character.charCount('A'));
        assertEquals(2, Character.charCount(65536));    // 大于65535，需要引入surrogate字符
        
        //高位代理区间+低位代理区间 <-> 实际codepoint
        assertEquals(65536, Character.toCodePoint((char) 0xd800, (char) 0xdc00));
        char[] cs = Character.toChars(65536);
        assertEquals((char) 0xd800, cs[0]);
        assertEquals((char) 0xdc00, cs[1]);
        
        // 字符判断
        assertTrue(Character.isLowerCase('a'));
        assertTrue(Character.isUpperCase('A'));
        assertTrue(Character.isLetter('a'));
        assertTrue(Character.isDigit('0'));
        
        assertEquals('A', Character.toTitleCase('a'));
        
        // 基于36进制的数字系统
        // 0-9
        // a-10 z-35
        assertEquals(10, Character.getNumericValue('a'));
        assertEquals(10, Character.getNumericValue('A'));
        assertEquals(35, Character.getNumericValue('z'));
        assertEquals(35, Character.getNumericValue('Z'));
        assertEquals(1, Character.getNumericValue('1'));
    }
    
    @Test
    public void testString() {
        String hws = "hello world";
        char[] hwc = hws.toCharArray();
        
        assertEquals("hello world", hws);
        
        // 构造函数通过偏移值创建
        assertEquals("ello", new String(hwc, 1, 4));
        assertEquals("", new String(hwc, 1, 0));
        
        // 字符个数
        assertEquals(11, hws.length());
        
        // index from 0
        assertEquals('e', hws.charAt(1));
        
        // 特殊字符
        String special = "123\uD83D\uDE00abc";
        assertEquals(8, special.length());
        assertEquals(49, special.codePointAt(0));
        assertEquals(128512, special.codePointAt(3));   // 两个连续的字节
        assertEquals(97, special.codePointAt(5));
        
        // 获取src的字符串[0, 5)，覆盖dest从1开始5个位置（5-0）的字符
        char[] dest = "1234567890".toCharArray();
        hws.getChars(0, 5, dest, 1);
        assertEquals("1hello7890", new String(dest));
    }
    
    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder("hello");
        sb.append(" ");
        sb.append("world");
        assertEquals("hello world", sb.toString());
    }
    
    @Test
    public void testStringBuffer() {
        // 线程安全
        StringBuffer sb = new StringBuffer("hello");
        sb.append(" ");
        sb.append("world");
        assertEquals("hello world", sb.toString());
    }
    
    @Test
    public void testStringImmutable() {
        String t1 = "hello";
        String t2 = "hello";
        
        assertTrue(t1 == t2);   // 引用 堆中 字符串常量池 中的同一个对象
        
        String t3 = new String("hello");
        assertFalse(t1 == t3);
        
        String t4 = new String("hello");
        assertFalse(t3 == t4);
        
        String t5 = "a" + "bc";
        String t6 = "ab" + "c";
        assertTrue(t5 == t6);
    }
    
    @Test
    public void testStringEq() {
        String a = new String("ab"); // a 为一个引用
        String b = new String("ab"); // b为另一个引用,对象的内容一样
        String aa = "ab"; // 放在常量池中
        String bb = "ab"; // 从常量池中查找
        assertTrue(aa == bb);
        assertFalse(a == b);    // 两个不同对象
        assertTrue(a.equals(b));    // object默认比较对象地址，String重写了equals方法
    }
}
