package com.newsing.activity.weather.days;

/**
 * Created by Qzhu on 2017/8/2.
 */

public class DayInfo {
    private int ret_code; //-1 no area 0 ok
    private DayNowInfo now;
    private DayFInfo f1,f2,f3,f4,f5,f6,f7;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public DayNowInfo getNow() {
        return now;
    }

    public void setNow(DayNowInfo now) {
        this.now = now;
    }

    public DayFInfo getF1() {
        return f1;
    }

    public void setF1(DayFInfo f1) {
        this.f1 = f1;
    }

    public DayFInfo getF2() {
        return f2;
    }

    public void setF2(DayFInfo f2) {
        this.f2 = f2;
    }

    public DayFInfo getF3() {
        return f3;
    }

    public void setF3(DayFInfo f3) {
        this.f3 = f3;
    }

    public DayFInfo getF4() {
        return f4;
    }

    public void setF4(DayFInfo f4) {
        this.f4 = f4;
    }

    public DayFInfo getF5() {
        return f5;
    }

    public void setF5(DayFInfo f5) {
        this.f5 = f5;
    }

    public DayFInfo getF6() {
        return f6;
    }

    public void setF6(DayFInfo f6) {
        this.f6 = f6;
    }

    public DayFInfo getF7() {
        return f7;
    }

    public void setF7(DayFInfo f7) {
        this.f7 = f7;
    }
}
