package com.newsing.activity.weather.today;

/**
 * Created by Qzhu on 2017/8/2.
 */

public class Weather24Bean {
    private int showapi_res_code;
    private String showapi_res_error;
    private Weather24Body showapi_res_body;

    public int getShowapi_res_code() {
        return showapi_res_code;
    }

    public void setShowapi_res_code(int showapi_res_code) {
        this.showapi_res_code = showapi_res_code;
    }

    public String getShowapi_res_error() {
        return showapi_res_error;
    }

    public void setShowapi_res_error(String showapi_res_error) {
        this.showapi_res_error = showapi_res_error;
    }

    public Weather24Body getShowapi_res_body() {
        return showapi_res_body;
    }

    public void setShowapi_res_body(Weather24Body showapi_res_body) {
        this.showapi_res_body = showapi_res_body;
    }
}
