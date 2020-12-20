package com.sciatta.hadoop.java.guava.string;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangxiaoyu on 2020/12/20<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * StringTests
 */
public class StringTests {
    @Test
    public void testJoin() {
        ArrayList<String> list = Lists.newArrayList("1", "2", "3");
        String join = Joiner.on(",").join(list);
        assertEquals("1,2,3", join);
    }
    
    @Test
    public void testSplit() {
        List<String> list = Splitter.on(",").splitToList("1,2,3");
        assertEquals("1", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("3", list.get(2));
    }
}
