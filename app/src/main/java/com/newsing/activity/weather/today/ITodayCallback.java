package com.newsing.activity.weather.today;

import com.newsing.activity.weather.days.DayNowInfo;

/**
 * Created by Qzhu on 2017/8/3.
 */

public interface ITodayCallback {
    void setNowWeather(DayNowInfo info);
}
