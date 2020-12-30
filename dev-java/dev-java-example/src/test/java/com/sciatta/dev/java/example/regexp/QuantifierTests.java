package com.sciatta.dev.java.example.regexp;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019-04-12<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * QuantifierTests
 */
public class QuantifierTests extends AbstractRegExp {
    @Test
    public void testQuestion() {
        // 0-1
        expression = "a?";
        check = "";
        process();
    }

    @Test
    public void testPlus() {
        // 1-*
        expression = "(foo)+";
        check = "foo";
        process();
    }

    @Test
    public void testAsterisk() {
        // 0-*
        expression = "(foo)*";
        check = "";
        process();

        expression = "[a]*";
        check = "a";
        process();

        expression = "[a]*";
        check = "";
        process();

        expression = "[ab]*";
        check = "afb";
        process();
    }

    @Test
    public void testMinTimes() {
        expression = "a{3,}";
        check = "bbaaaaccc";
        process();
    }

    @Test
    public void testMinMaxTimes() {
        expression = "a{3,4}";
        check = "bbaaaaaccc";
        process();

        expression = "a{3,4}";
        check = "bbaaccc";
        process();
    }

    @Test
    public void testGreedy() {
        // 尽可能多的匹配字符，当到达末尾或下一个字符无法匹配后，则向前回溯匹配通配符的下一个字符，直到匹配完成
        // 1、匹配通配符 .* 匹配到整个字符串
        // 2、匹配通配符 f ，到达末尾，回溯一个字符o匹配f，再回溯一个字符o匹配f，再回溯一个字符f匹配f，成功
        // 3、匹配通配符 o ，匹配成功
        // 4、匹配通配符 o ，匹配成功
        // xfooxxxxxxfoo
        expression = ".*foo";
        check = "xfooxxxxxxfoo";
        process();

        expression = ".*foo";
        check = "xfooxxxxxxfood";
        process();

        expression = "[ab]*foo";
        check = "abababfoodd";
        process();

        // 1、匹配通配符 [ab]* 匹配到 ababab，匹配 f，不匹配则回溯一个字符
        // 2、匹配通配符 f， 匹配成功
        // 3、匹配通配符 o ，匹配成功
        // 4、匹配通配符 o ，匹配成功
        // abababfoo

        // 1、匹配通配符 [ab]* 匹配到 f，不匹配回溯一个字符（注意此时满足 [ab]* 语义，0个字符）
        // 2、匹配通配符 f， 匹配成功
        // 3、匹配通配符 o ，匹配成功
        // 4、匹配通配符 o ，匹配成功
        // foo
        expression = "[ab]*foo";
        check = "abababfooxxfoodd";
        process();
    }

    @Test
    public void testReluctant() {
        // 尽可能少的匹配字符，只有当下一个字符匹配不成功，才会回溯去匹配
        // 1、.* 表示最少匹配0个任意字符，因此匹配通配符 f，字符串的第一个字符x，不匹配，回溯一个字符，用 .* 取匹配 x，匹配成功
        // 2、匹配通配符 f， 匹配成功
        // 3、匹配通配符 o ，匹配成功
        // 4、匹配通配符 o ，匹配成功
        // xfoo

        // 1、采用最少匹配规则，xxxxxx 匹配 .*，当匹配到 f 时，读取通配符 f 去匹配，匹配成功
        // 2、匹配通配符 o ，匹配成功
        // 3、匹配通配符 o ，匹配成功
        // xxxxxxfoo
        expression = ".*?foo";
        check = "xfooxxxxxxfoo";
        process();

        expression = ".*?foo";
        check = "xfooxfooxfo";
        process();
    }

    @Test
    public void testPossessive() {
        // 尽可能多的匹配字符，但匹配当到达末尾或下一个字符无法匹配后，不会发生回溯
        // 1、匹配通配符 .* 匹配到整个字符串
        // 2、到达尾部，无法匹配通配符 foo，因此匹配不成功
        expression = ".*+foo";
        check = "xfooxxxxxxfoo";
        process();

        // axxxb
        expression = "a.{3,4}b";
        check = "axxxb";
        process();

        // axxxbb（选择中间最大匹配长度）
        expression = "a.{3,4}b";
        check = "axxxbb";
        process();

        // 不匹配
        expression = "a.{3,4}b";
        check = "axxbx";
        process();
    }
}
