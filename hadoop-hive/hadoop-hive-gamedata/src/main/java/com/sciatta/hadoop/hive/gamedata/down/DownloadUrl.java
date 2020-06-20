package com.sciatta.hadoop.hive.gamedata.down;

import com.sciatta.hadoop.hive.gamedata.util.TableNameUtil;
import com.sciatta.hadoop.hive.gamedata.util.URLUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DownloadUrl {

    public static ConcurrentLinkedQueue<String> getUrls(String path, String tmpDataPath, String game, String mysqlTable, String where) {
        ConcurrentLinkedQueue<String> taskCountQueue = new ConcurrentLinkedQueue<>();

        // 原始url文件的路径，获取所有待导入数据库地址
        List<String> allUrls = readUrl(path);

        // 已下载数据成功的路径
        String writeUrlPath = URLUtil.getDoneURL(tmpDataPath, game, mysqlTable, where);
        List<String> writeUrls = readUrl(writeUrlPath);

        if (writeUrls != null && writeUrls.size() > 0) {
            // 排除部分已经导入的数据库地址，避免重复导入
            for (String url : allUrls) {
                if (!writeUrls.contains(url)) {
                    taskCountQueue.offer(url);
                }
            }
        } else {
            for (String url : allUrls) {
                taskCountQueue.offer(url);
            }
        }

        return taskCountQueue;
    }

    /*
     * 读取指定文件的url
     */
    public static List<String> readUrl(String path) {
        List<String> taskCountQueue = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                taskCountQueue.add(tempString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return taskCountQueue;
    }
}
