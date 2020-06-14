package com.sciatta.hadoop.java.example.regexp.metacharacters;

import com.sciatta.hadoop.java.example.regexp.AbstractRegExp;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2019-04-11<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * PredefinedCharacterClassesTests
 */
public class PredefinedCharacterClassesTests extends AbstractRegExp {
    @Test
    public void testAnyCharacter() {
        expression = ".";
        check = "a";
        process();

        check = "he";
        process();
    }

    @Test
    public void testDigital() {
        expression = "\\d";
        check = "1";
        process();

        expression = "\\d";
        check = "a";
        process();

        expression = "\\D";
        check = "a";
        process();

        expression = "\\D";
        check = "1";
        process();
    }

    @Test
    public void testWhitespace() {
        expression = "\\s";
        check = "a";
        process();

        check = "\n";
        process();

        expression = "\\S";
        check = "a";
        process();

        check = "\n";
        process();
    }

    @Test
    public void testWord() {
        expression = "\\w";
        check = "a";
        process();

        check = "!";
        process();

        check = "_";
        process();

        expression = "\\W";
        check = "$";
        process();
    }
}
