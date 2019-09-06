package com.common.utils;

public class HtmlUtils {

    public static String addTitleAndTime(String content,String title,String timeLong){
        String replacecontent = content.replace("<img", "<img style=\"max-width:100%;height:auto\"");
        String titleString = "<h3 align=\"center\">"+title+"</h3>";
        String timeContent = "<p align =\"right\" magin-rigth = \"100\">"+timeLong+"</p></br></br>";
        return titleString+timeContent+replacecontent;
    }
}
