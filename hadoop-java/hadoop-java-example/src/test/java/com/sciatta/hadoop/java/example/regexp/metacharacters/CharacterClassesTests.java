package com.sciatta.hadoop.java.example.regexp.metacharacters;

import com.sciatta.hadoop.java.example.regexp.AbstractRegExp;
import org.junit.Test;

/**
 * Created by yangxiaoyu on 2019-04-11<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * CharacterClassesTests
 */
public class CharacterClassesTests extends AbstractRegExp {
    @Test
    public void testSimpleClass() {
        expression = "[abc]";
        check = "ab";
        process();
    }

    @Test
    public void testNegation() {
        expression = "[^abc]";
        check = "adeb";
        process();
    }

    @Test
    public void testRange() {
        expression = "[a-bC-F]";
        check = "C";
        process();
    }

    @Test
    public void testUnion() {
        expression = "[a-b[C-F]]";
        check = "D";
        process();
    }

    @Test
    public void testIntersection() {
        expression = "[a-b&&[b-w]]";
        check = "c";
        process();
    }

    @Test
    public void testSubtraction() {
        expression = "[a-z&&[^c-d]]";
        check = "c";
        process();

        // a-b e-z
        check = "b";
        process();
    }
}
