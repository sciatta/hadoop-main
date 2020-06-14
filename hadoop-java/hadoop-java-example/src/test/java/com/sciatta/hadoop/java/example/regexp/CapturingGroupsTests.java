package com.sciatta.hadoop.java.example.regexp;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-04-12<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * CapturingGroupsTests
 */
public class CapturingGroupsTests extends AbstractRegExp {
    @Test
    public void testOneGroup() {
        String str = "hi(hello)+";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher("bye");
        // matcher.groupCount() 是 matcher 的 pattern 所拥有的的 () 的个数
        assertEquals(1, matcher.groupCount());
    }

    @Test
    public void testTWoGroup() {
        String str = "hi(hello)+(df)*";

        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher("hihellohellodfhihello");

        // pattern有两个()
        assertEquals(2, matcher.groupCount());

        while (matcher.find()) {
            System.out.format("group{%s} start{%d} end{%d}%n", matcher.group(), matcher.start(), matcher.end());
            System.out.format("group0{%s} group1{%s} group2{%s}%n", matcher.group(0), matcher.group(1), matcher.group(2));
            System.out.println();
        }
    }

    @Test
    public void testBackReferences() {
        // 反向引用一次第一组，反向引用的目的是为了方便重复匹配规则
        expression = "(\\d\\d)\\1";

        check = "1212";
        process();

        check = "aa1212aa3434";
        process();

        check = "1234";
        process();

        expression = "(\\d\\d)(\\D\\D)\\2";
        check = "12wcwc";
        process();

        // 反向引用一次第二组
        expression = "(\\d\\d)(\\d\\d)\\2";
        check = "12343434";
        process();

        // 反向引用两次第二组
        expression = "(\\d\\d)(\\d\\d)\\2\\2";
        check = "12343434";
        process();

        expression = "(\\d\\d)(\\d\\d)\\1\\2";
        check = "12341234";
        process();
    }
}
