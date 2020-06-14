package com.sciatta.hadoop.java.example.regexp.metacharacters;

import com.sciatta.hadoop.java.example.regexp.AbstractRegExp;
import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019-04-12<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * BoundaryMatchersTests
 */
public class BoundaryMatchersTests extends AbstractRegExp {
    @Test
    public void testBeginningAndEnd() {
        // ^ 起始限制，必须在行首
        expression = "^88.*";
        check = "88a";
        process();

        // $ 结尾限制，必须在行尾
        expression = "\\s*dog$";
        check = "   dog";
        process();

        // 最后的\n不满足完全匹配，但满足搜索匹配1次
        check = "   dog\n";
        process();

        expression = "dog$";
        check="dog dog";
        process();

        expression = "dog$";
        check="dog dog ";
        process();
    }

    @Test
    public void testCharacterBeginningAndEnd() {
        // 以dog开头，相当于^
        expression = "\\Adog";
        check = "dog hello";
        process();

        // \A \Z 匹配的输入字符串最后可以有，也可以没有终止字符，如 \n
        expression = "dog\\Z";
        check = "abc dog\n";
        process();

        // \A \z 相当于 ^ $ 完全匹配
        expression = "dog\\z";
        check = "abc dog";
        process();

        // 字符串是以\n结尾，最后不是以dog结尾，所以不匹配
        check = "abc dog\n";
        process();

        expression = "\n\\z";
        check = "dog\n";
        process();
    }

    @Test
    public void testWord() {
        // 查找单词，前后边界约束
        expression = "\\bdog\\b";
        check = "hello dog";
        process();

        // 查找单词，前边界不约束，因为有\B
        expression = "\\Bdog\\b";
        check = "hellodog cc";
        process();

        expression = "\\bdog\\b";
        check = "hellodog cc";
        process();
    }

    @Test
    public void testEndOfPrevious() {
        // 紧接着上一次匹配的后面进行匹配，直到不匹配为止
        // 首先匹配第一个dog，然后紧接着上一次匹配的后面匹配第二个dog；第二个dog后面是空格，所以匹配结束，即使空格后面还有一个dog。
        // 使用场景：匹配多个相同子序列之间无其他字符干扰。
        expression = "\\Gdog";
        check = "dogdog dog";
        process();
    }
}
