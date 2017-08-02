package com.newsing.activity.weather.today;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.newsing.R;
import com.newsing.basic.BaseInterface;
import com.newsing.utils.ConstValue;
import com.newsing.utils.NetWorkUtils;

/**
 * Created by Qzhu on 2017/8/2.
 */
public class WeatherTodayFragment extends Fragment implements BaseInterface<String> {

    private RecyclerView recyclerView;
    private TodayAdapter adapter;

    private TextView today_area;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.weather_today,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.today_recycler);
        today_area = (TextView) view.findViewById(R.id.today_area);
        view.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter((adapter = new TodayAdapter(getActivity())));
    }

    public void async(String area) {
        NetWorkUtils.getInstance().WeatherALIGet_Sync(this, ConstValue.ALIAPI.WEATHER24_Uri+area);
    }

    @Override
    public void onComplete(String result) {
        if(result == null) {
            onError(-1);
            return ;
        }
        Weather24Bean bean = new Gson().fromJson(result,Weather24Bean.class);
        Weather24Body body = bean.getShowapi_res_body();
        if(body.getRet_code() == 0) {
            adapter.swapData(body.getHourList());
            today_area.setText(body.getArea());
            getView().setVisibility(View.VISIBLE);
        }else if(body.getRet_code() == -1){
            Toast.makeText(getActivity(),"找不到地区",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(@StringRes int resId) {

    }
}
