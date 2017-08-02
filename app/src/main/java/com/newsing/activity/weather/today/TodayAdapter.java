package com.newsing.activity.weather.today;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newsing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qzhu on 2017/8/2.
 */
public class TodayAdapter extends RecyclerView.Adapter<TodayAdapter.ViewHolder>{
    private Context context;
    private List<WeatherHourBody> hourList;

    public TodayAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_today_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(hourList.get(position));
    }

    public void swapData(List<WeatherHourBody> raws){
        if(hourList == null)
            hourList = new ArrayList<>();
        hourList.clear();
        hourList.addAll(raws);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return hourList == null ? 0 :hourList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView temp,weather,date,power;

        public ViewHolder(View itemView) {
            super(itemView);
            temp = (TextView) itemView.findViewById(R.id.today_item_temp);
            weather = (TextView) itemView.findViewById(R.id.today_item_weather);
            date = (TextView) itemView.findViewById(R.id.today_item_time);
            power = (TextView) itemView.findViewById(R.id.today_item_windpower);
        }

        public void setData(WeatherHourBody body){
            temp.setText(body.getTemperature());
            weather.setText(body.getWeather());
            date.setText(body.getTime());
            power.setText(body.getWind_power().split("  ")[0]);
        }
    }
}
