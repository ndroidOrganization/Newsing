package com.newsing.activity.weather.days;

/**
 * Created by Qzhu on 2017/8/2.
 */

public class DayFInfo {
    private String day_weather; //早上
    private String night_weather; //晚上
    private String air_press; //气压
    private String ziwaixian;
    private String day; //日期

    private String day_air_temperature; //早上温度
    private String night_air_temperature; //晚上温度


    public String getTempurature(){
        return "白天："+day_air_temperature+"℃\n"+"夜晚："+night_air_temperature+"℃";
    }

    public String getWeather(){
        return "白天："+day_weather+"\n"+"夜晚："+night_weather;
    }

    public String getFormatDay(){
        StringBuilder builder = new StringBuilder(day);
        builder.delete(0,4);
        builder.insert(2,'/');
        return builder.toString();
    }
}
