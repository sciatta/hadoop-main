package com.sciatta.dev.java.database.hive.video.etl;

/**
 * Created by yangxiaoyu on 2020/4/5<br>
 * All Rights Reserved(C) 2017 - 2020 SCIATTA<br><p/>
 * VideoUtil
 */
public class VideoUtil {
    public static String washData(String data) {
        // FM1KUDE3C3k  renetto	736	News & Politics	1063	9062    4.57	525	488	LnMvSxl0o0A IKMtzNuKQso
        if (data == null || data.equals("")) {
            return null;
        }

        // 0 - FM1KUDE3C3k
        // 1 - renetto
        // 2 - 736
        // 3 - News & Politics
        // 4 -1063
        // 5 - 9062
        // 6 - 4.57
        // 7 - 525
        // 8 - 488
        // 9 - LnMvSxl0o0A
        // 10 - IKMtzNuKQso
        String[] datas = data.split("\t");

        // 前9个字段必须存在
        if (datas.length < 9) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < datas.length; i++) {
            if (i < 9) {
                if (i == 3) {
                    // 第4个字段替换空格为空串
                    sb.append(datas[3].replace(" ", ""));
                } else {
                    sb.append(datas[i]);
                }
                // 如果只有9个字段，则最后一个不添加\t
                if (i + 1 != datas.length) {
                    sb.append("\t");
                }
            } else {
                sb.append(datas[i]);
                // 从第10个字段开始，将后续字段以&分隔
                if (i + 1 != datas.length) {
                    sb.append("&");
                }
            }
        }

        return sb.toString();
    }
}
