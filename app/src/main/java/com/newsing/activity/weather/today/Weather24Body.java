package com.newsing.activity.weather.today;

import java.util.List;

/**
 * Created by Qzhu on 2017/8/2.
 */

public class Weather24Body {
    private int ret_code;
    private String area;
    private String areaid;
    private List<WeatherHourBody> hourList;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }

    public List<WeatherHourBody> getHourList() {
        return hourList;
    }

    public void setHourList(List<WeatherHourBody> hourList) {
        this.hourList = hourList;
    }
}
