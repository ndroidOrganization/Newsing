package com.newsing.activity.weather.days;

import com.newsing.activity.weather.today.AqiDetails;

/**
 * Created by Qzhu on 2017/8/2.
 */

public class DayNowInfo {
    private String temperature_time; //更新时间
    private String wind_direction; //风向
    private String wind_power; //风力
    private String weather; //现在天气
    private String temperature; //现在温度
    private String weather_pic; //icon
    private String sd; //湿度

    private AqiDetails aqiDetail;

    public String getTemperature_time() {
        return "更新时间:"+temperature_time;
    }

    public void setTemperature_time(String temperature_time) {
        this.temperature_time = temperature_time;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_power() {
        return wind_power;
    }

    public void setWind_power(String wind_power) {
        this.wind_power = wind_power;
    }

    public String getWeather() {
        return weather+"℃";
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public AqiDetails getAqiDetail() {
        return aqiDetail;
    }

    public void setAqiDetail(AqiDetails aqiDetail) {
        this.aqiDetail = aqiDetail;
    }
}
