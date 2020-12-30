package com.sciatta.dev.bigdata.hive.video.etl;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yangxiaoyu on 2020/4/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * VideoUtilTests
 */
public class VideoUtilTests {
    @Test
    public void testWashDataIsNull() {
        String test = "FM1KUDE3C3k\trenetto\t736\tNews & Politics\t1063\t9062";
        assertNull(VideoUtil.washData(test));

        assertNull(VideoUtil.washData(""));

        assertNull(VideoUtil.washData(null));
    }

    @Test
    public void testHaveNineFields() {
        String test = "FM1KUDE3C3k\trenetto\t736\tNews & Politics\t1063\t9062\t4.57\t525\t488";
        String result = "FM1KUDE3C3k\trenetto\t736\tNews&Politics\t1063\t9062\t4.57\t525\t488";

        assertEquals(result, VideoUtil.washData(test));
    }

    @Test
    public void testHaveTenFileds() {
        String test = "FM1KUDE3C3k\trenetto\t736\tNews & Politics\t1063\t9062\t4.57\t525\t488\tLnMvSxl0o0A";
        String result = "FM1KUDE3C3k\trenetto\t736\tNews&Politics\t1063\t9062\t4.57\t525\t488\tLnMvSxl0o0A";

        assertEquals(result, VideoUtil.washData(test));
    }

    @Test
    public void testHaveMoreFileds() {
        String test = "FM1KUDE3C3k\trenetto\t736\tNews & Politics\t1063\t9062\t4.57\t525\t488\tLnMvSxl0o0A\tIKMtzNuKQso\tIKMtzNuKQso";
        String result = "FM1KUDE3C3k\trenetto\t736\tNews&Politics\t1063\t9062\t4.57\t525\t488\tLnMvSxl0o0A&IKMtzNuKQso&IKMtzNuKQso";

        assertEquals(result, VideoUtil.washData(test));
    }
}
