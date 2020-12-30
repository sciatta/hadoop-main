package com.sciatta.dev.java.example.regexp;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019-04-12<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * EscapeTests
 */
public class EscapeTests extends AbstractRegExp {
    @Test
    public void testTransferredMeaning() {
        // \d 代表任意数字；在字符串常量中要将\先转义，否则字符串字面常量会出错
        expression = "a\\db";
        check = "a1b";
        process();

        // \d 代表任意数字；字符串中要使用转义字符\\d；看做普通字符要加\；在字符串中要加\\
        expression = "a\\\\db";
        check = "1a\\db2";
        process();
    }

    @Test
    public void testNormalCharacter() {
        // 将引擎内置解析特殊字符看做普通字符，加\；字符串常量转义\\
        expression = "\\[abc\\]";
        check = "[abc]";
        process();

        // \Q 转义开始，\E 转义结束    Quote
        expression = "\\Q[\\Eabc\\Q]\\E";
        check = "[abc]";
        process();
    }
}
