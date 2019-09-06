package com.common.utils;

import java.text.SimpleDateFormat;

public class TimeUtils {

    /**
     * 时间字符串转换成日历对象
     * @param dateString 时间字符串
     * @return Calendar
     */
    public static String timeStamp2dateString(long dateString) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return simpleDateFormat.format(dateString);
    }
}
