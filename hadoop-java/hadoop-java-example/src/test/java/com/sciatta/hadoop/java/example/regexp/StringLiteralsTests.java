package com.sciatta.hadoop.java.example.regexp;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019-04-11<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * StringLiteralsTests
 */
public class StringLiteralsTests extends AbstractRegExp {
    @Test
    public void testMatches() {
        expression = "abc";
        check = "abc";
        process();
    }

    @Test
    public void testNotMatches() {
        expression = "a";
        check = "aaa";
        process();
    }

    @Test
    public void testNotFind() {
        expression = "b";
        check = "aaa";
        process();
    }
}
