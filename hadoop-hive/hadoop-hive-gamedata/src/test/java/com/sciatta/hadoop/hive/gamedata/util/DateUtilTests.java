package com.sciatta.hadoop.hive.gamedata.util;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2020/6/18<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * DateUtilTests
 */
public class DateUtilTests {
    @Test
    public void testMonthAdd1() {
        String s = DateUtil.monthAdd1("2020-06-18");
        assertEquals("2020-07-18", s);
    }

    @Test
    public void testMonthLess1() {
        String s = DateUtil.monthLess1("2020-06-18");
        assertEquals("2020-05-18", s);
    }

    @Test
    public void testGetMonthByDate() {
        String s = DateUtil.getMonthByDate("2020-06-18");
        assertEquals("2020-06", s);
    }

    @Test
    public void testGetMonthBetween() {
        // 相同日期，间隔为本月
        List<String> monthBetween = DateUtil.getMonthBetween("2020-06-18", "2020-06-18");
        assertEquals(1, monthBetween.size());
        System.out.println(monthBetween);

        monthBetween = DateUtil.getMonthBetween("2020-06-18", "2020-09-18");
        assertEquals(4, monthBetween.size());
        System.out.println(monthBetween);

        monthBetween = DateUtil.getMonthBetween("2020-01-01", "2020-02-01");
        assertEquals(2, monthBetween.size());
        System.out.println(monthBetween);
    }

    @Test
    public void testGetCurrentTime() {
        String currentTime = DateUtil.getCurrentTime();
        assertNotNull(currentTime);
        System.out.println(currentTime);
    }
}
