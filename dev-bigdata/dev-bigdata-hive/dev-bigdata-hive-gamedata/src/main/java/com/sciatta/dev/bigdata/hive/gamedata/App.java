package com.sciatta.dev.bigdata.hive.gamedata;

import com.sciatta.dev.bigdata.hive.gamedata.down.DownloadData;

/**
 * Created by yangxiaoyu on 2020/6/20<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * App
 */
public class App {
    public static void main(String[] args) {
        DownloadData downloadData = new DownloadData();
        downloadData.doDownLoad(args);
    }
}
