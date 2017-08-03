package com.newsing.activity.weather.today;

import android.view.View;
import android.widget.TextView;

import com.newsing.R;
import com.newsing.activity.weather.days.DayNowInfo;

/**
 * Created by Qzhu on 2017/8/3.
 */

public class NowPresenter {

    private View rootView;

    private TextView area,temp,updtime,weath;

    public NowPresenter(View rootView) {
        this.rootView = rootView;
        area = (TextView) rootView.findViewById(R.id.now_area);
        temp = (TextView) rootView.findViewById(R.id.now_tempera);
        updtime = (TextView) rootView.findViewById(R.id.now_time);
        weath = (TextView) rootView.findViewById(R.id.now_weather);
        rootView.setVisibility(View.INVISIBLE);
    }

    public void setWeather(DayNowInfo info){
        area.setText(info.getAqiDetail().getArea());
        temp.setText(info.getTemperature());
        updtime.setText(info.getTemperature_time());
        weath.setText(info.getWeather());
        rootView.setVisibility(View.VISIBLE);
    }

}
