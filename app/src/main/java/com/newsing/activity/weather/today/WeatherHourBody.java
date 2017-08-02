package com.newsing.activity.weather.today;

/**
 * Created by Qzhu on 2017/8/2.
 */

public class WeatherHourBody {
//    "weather_code": "07",
//            "time": "201708021300",
//            "wind_direction": "东南偏南",
//            "wind_power": "5-6 清风  10.8~13.8m/s",
//            "weather": "小雨",
//            "temperature": "29"
    private String weather_code;
    private String time;
    private String wind_direction;
    private String wind_power;
    private String weather;
    private String temperature;

    public String getWeather_code() {
        return weather_code;
    }

    public void setWeather_code(String weather_code) {
        this.weather_code = weather_code;
    }

    public String getTime() {
        StringBuilder builder = new StringBuilder(time);
        builder.delete(0,4);
        builder.insert(6,':');
        builder.insert(4,'\n');
        builder.insert(4,'\n');
        builder.insert(2,'/');
        return builder.toString();
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_power() {
        return (wind_power.replaceFirst(" ","\n"));
    }

    public void setWind_power(String wind_power) {
        this.wind_power = wind_power;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature+"℃";
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
