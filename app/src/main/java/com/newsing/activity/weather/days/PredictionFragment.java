package com.newsing.activity.weather.days;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.newsing.R;
import com.newsing.WeatherPredBinding;
import com.newsing.activity.weather.today.ITodayCallback;
import com.newsing.basic.BaseInterface;
import com.newsing.utils.ConstValue;
import com.newsing.utils.NetWorkUtils;

/**
 * Created by Qzhu on 2017/8/2.
 */

public class PredictionFragment extends Fragment implements BaseInterface<String> {
    private WeatherPredBinding binding;

    private ITodayCallback callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        callback = (ITodayCallback) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.weather_prediction,container,false);
        binding = WeatherPredBinding.bind(view);
        view.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void async(String area) {
        NetWorkUtils.getInstance().WeatherALIGet_Sync(this, ConstValue.ALIAPI.getDaysUri(area));
    }

    @Override
    public void onComplete(String result) {
        if(result == null) {
            onError(-1);
            return ;
        }

        DayWeatherBean bean = new Gson().fromJson(result,DayWeatherBean.class);
        DayInfo body = bean.getShowapi_res_body();
        if(body.getRet_code() == 0) {
            setItemText(body.getF1(),1);
            setItemText(body.getF2(),2);
            setItemText(body.getF3(),3);
            setItemText(body.getF4(),4);
            setItemText(body.getF5(),5);
            setItemText(body.getF6(),6);
            setItemText(body.getF7(),7);
            getView().setVisibility(View.VISIBLE);

            if(callback != null)
            {
                callback.setNowWeather(body.getNow());
            }
        }else if(body.getRet_code() == -1){
            Toast.makeText(getActivity(),"找不到地区",Toast.LENGTH_SHORT).show();
        }
    }

    private void setItemText(DayFInfo info,int position){
        switch (position){
            case 1:
                setText(binding.predictionDay1,info.getFormatDay(),
                        binding.predictionTempura1,info.getTempurature(),
                        binding.predictionWeather1,info.getWeather());
                break;
            case 2:
                setText(binding.predictionDay2,info.getFormatDay(),
                        binding.predictionTempura2,info.getTempurature(),
                        binding.predictionWeather2,info.getWeather());
                break;
            case 3:
                setText(binding.predictionDay3,info.getFormatDay(),
                        binding.predictionTempura3,info.getTempurature(),
                        binding.predictionWeather3,info.getWeather());
                break;
            case 4:
                setText(binding.predictionDay4,info.getFormatDay(),
                        binding.predictionTempura4,info.getTempurature(),
                        binding.predictionWeather4,info.getWeather());
                break;
            case 5:
                setText(binding.predictionDay5,info.getFormatDay(),
                        binding.predictionTempura5,info.getTempurature(),
                        binding.predictionWeather5,info.getWeather());
                break;
            case 6:
                setText(binding.predictionDay6,info.getFormatDay(),
                        binding.predictionTempura6,info.getTempurature(),
                        binding.predictionWeather6,info.getWeather());
                break;
            case 7:
                setText(binding.predictionDay7,info.getFormatDay(),
                        binding.predictionTempura7,info.getTempurature(),
                        binding.predictionWeather7,info.getWeather());
                break;
        }
    }

    private void setText(TextView day,String formatday,TextView temp,String tempvalue,TextView weath,String weathValue){
        day.setText(formatday);
        temp.setText(tempvalue);
        weath.setText(weathValue);
    }

    @Override
    public void onError(@StringRes int resId) {

    }
}
