package com.sciatta.dev.java.example.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangxiaoyu on 2019-04-11<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * AbstractRegExp
 */
public abstract class AbstractRegExp {
    protected String expression;
    protected String check;

    protected Finder process() {
        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(check);
        Finder f = new Finder();

        System.out.println("******************************************************");
        System.out.println("匹配表达式: " + expression);
        System.out.println("输入字符串: " + check);

        // find 搜索匹配
        while (m.find()) {
            f.find = true;
            f.times++;
            System.out.format("find{%d} group{%s} start{%d} end{%d}%n", f.times, m.group(), m.start(), m.end());
        }
        if (!f.find) {
            System.out.println("find not found");
        }

        // matches 完全匹配
        boolean test = m.matches();
        if (test) {
            System.out.format("matches group{%s} start{%d} end{%d}%n", m.group(), m.start(), m.end());
        } else {
            System.out.println("matches not success");
        }

        System.out.println("******************************************************");

        System.out.println();

        return f;
    }

    class Finder {
        boolean find = false;
        int times = 0;
    }
}
