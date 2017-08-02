package com.newsing.activity.weather;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;

import com.newsing.R;
import com.newsing.activity.weather.days.PredictionFragment;
import com.newsing.activity.weather.today.WeatherTodayFragment;
import com.newsing.basic.BaseActivity;

/**
 * Created by Qzhu on 2017/8/2.
 */

public class WeatherActivity extends BaseActivity {
    private WeatherTodayFragment hourfragment;
    private PredictionFragment predictionFragment;
    private final static String area = "南京";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        setUpToolBar();
        setUpFragment();
    }

    private void setUpFragment() {
        hourfragment = (WeatherTodayFragment) getSupportFragmentManager().findFragmentById(R.id.weather_today_frag);
        predictionFragment = (PredictionFragment) getSupportFragmentManager().findFragmentById(R.id.weather_predi_frag);
    }

    private void setUpToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.weather_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initial();
    }

    public void initial(){
        if(null != hourfragment)
            hourfragment.async(area);
        if(null != predictionFragment)
        {
            predictionFragment.async(area);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather_bar_search, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView =
                (SearchView) menu.findItem(R.id.ab_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setQueryHint("地名...");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!TextUtils.isEmpty(query))
                    closeSearchView(searchView);
                if(null != hourfragment)
                    hourfragment.async(query);
                if(null != predictionFragment)
                {
                    predictionFragment.async(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    private void closeSearchView(SearchView searchView){
        searchView.setQuery("",false);
        searchView.setIconified(true);
    }
}
