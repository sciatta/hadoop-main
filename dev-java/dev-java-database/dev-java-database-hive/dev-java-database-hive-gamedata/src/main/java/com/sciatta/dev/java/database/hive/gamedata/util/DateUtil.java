package com.sciatta.dev.java.database.hive.gamedata.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    /**
     * 根据日期获取 加一个月的时间
     *
     * @param date
     * @return
     */
    public static String monthAdd1(String date) {
        String reStr;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // String -> Date
            Date dt = sdf.parse(date);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);

            rightNow.add(Calendar.MONTH, +1);
            Date dt1 = rightNow.getTime();
            // Date -> String
            reStr = sdf.format(dt1);
            return reStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据日期获取 减一个月的时间
     *
     * @param date
     * @return
     */
    public static String monthLess1(String date) {
        String reStr;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dt = sdf.parse(date);
            Calendar rightNow = Calendar.getInstance();
            rightNow.setTime(dt);

            rightNow.add(Calendar.MONTH, -1);
            Date dt1 = rightNow.getTime();
            reStr = sdf.format(dt1);
            return reStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据日期 截获 月份
     *
     * @param date yyyy-mm-dd
     * @return yyyy-mm
     */
    public static String getMonthByDate(String date) {
        String substring = date.substring(0, 7);
        return substring;
    }

    /**
     * 获取两个时间点之间的月份，包括首尾月份
     *
     * @param minDate
     * @param maxDate
     * @return
     * @throws ParseException
     */
    public static List<String> getMonthBetween(String minDate, String maxDate) {
        ArrayList<String> result;
        try {
            result = new ArrayList<String>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();

            // 保证包括首尾月份
            min.setTime(sdf.parse(minDate));
            // 最小日期设置为当月第一天
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

            max.setTime(sdf.parse(maxDate));
            // 最大日期设置为当月第二天
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

            Calendar curr = min;
            while (curr.before(max)) {
                result.add(sdf.format(curr.getTime()));
                curr.add(Calendar.MONTH, 1);
            }
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String format = df.format(new Date());
        return format;
    }
}
