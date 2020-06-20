package com.sciatta.hadoop.hive.gamedata;

import org.junit.Test;

/**
 * Created by yangxiaoyu on 2020/6/20<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * AppTests
 */
public class AppTests {
    @Test
    public void testApp() {
        App.main(args());
    }

    private String[] args() {
        return new String[]{
                "20",
                "6000",
                "gamecenter",
                "/Users/yangxiaoyu/work/bigdata/project/hadoop-main/hadoop-hive/hadoop-hive-gamedata/src/main/resources/gamecenter_url.txt",
                "res_active_users",
                "n",
                "\\*",
                " date(event_date)>='2019-10-01' and date(event_date)<='2019-10-10' ",
                "/Users/yangxiaoyu/work/test/hivedatas/gamecenter/download"
        };
    }
}
