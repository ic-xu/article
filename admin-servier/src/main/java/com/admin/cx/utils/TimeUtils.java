package com.admin.cx.utils;

import java.text.SimpleDateFormat;

/**
 * Created by ubuntu
 * On 19-7-10 下午6:30
 */

public class TimeUtils {


    private static  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm;ss");

    public static String getCuurentTime(){

        return simpleDateFormat.format(System.currentTimeMillis());
    }
}
