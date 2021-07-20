package com.sciatta.dev.java.database.hive.gamedata.down;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.sciatta.dev.java.database.hive.gamedata.util.URLUtil;
import org.apache.log4j.Logger;

public class DownloadWriteUrlFile {

    private static Logger logger = Logger.getLogger(DownloadWriteUrlFile.class);

    /**
     * 批量写入成功的下载数据的url
     */
    public static void writeOutUrl(List<String> writeUrls, String tmpDataPath, String game, String mysqlTable, String where) {
        for (String writeUrl : writeUrls) {
            writeOutUrl(writeUrl, tmpDataPath, game, mysqlTable, where);
        }
    }

    /**
     * 单条写入成功的下载数据的url
     */
    public static void writeOutUrl(String writeUrl, String tmpDataPath, String game, String mysqlTable, String where) {

        String path = URLUtil.getDoneURL(tmpDataPath, game, mysqlTable, where);

        try {
            File file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(writeUrl);
            bw.newLine();
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
