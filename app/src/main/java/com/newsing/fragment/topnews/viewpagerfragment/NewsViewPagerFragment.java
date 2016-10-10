package com.newsing.fragment.topnews.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.newsing.R;
import com.newsing.fragment.topnews.adapter.TopNewsAdapter;
import com.newsing.fragment.topnews.http.top.TopBean;
import com.newsing.fragment.topnews.http.top.TopParams;
import com.newsing.fragment.topnews.http.top.TopReturn;
import com.newsing.interfaces.OnTouchUpListener;
import com.newsing.utils.http.HttpUtils;
import com.newsing.utils.http.JsonUtils;
import com.newsing.view.SWDropView;
import com.newsing.view.SWRecyclerViewLayout;
import org.androidannotations.annotations.*;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;
import org.androidannotations.annotations.res.StringRes;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Angel on 2016/9/22.
 */
@EFragment(R.layout.viewpager_news)
public class NewsViewPagerFragment extends Fragment implements OnTouchUpListener {

    @ViewById
    SWRecyclerViewLayout recyclerlayout;
    @DimensionPixelSizeRes(R.dimen.dp100)
    int dp100;
    private SWDropView drop;
    private View header;
    private TopNewsAdapter topNewsAdapter;
    @Bean
    JsonUtils jsonUtils;
    @Bean
    HttpUtils httpUtils;
    @StringRes
    String apikey;
    @StringRes
    String url;
    private JSONObject json;
    private ArrayList<TopBean> list = new ArrayList<>();


    @AfterViews
    void afterview() {
        topNewsAdapter = new TopNewsAdapter(getContext());
        header = LayoutInflater.from(getContext()).inflate(R.layout.drop, null);
        drop = (SWDropView) header.findViewById(R.id.drop);
        recyclerlayout.addHeaderView(header, dp100);
        recyclerlayout.setMyRecyclerView(new LinearLayoutManager(getContext()), topNewsAdapter);
        recyclerlayout.addOnTouchUpListener(this);
        if (list.size() == 0) {
            recyclerlayout.setScrollTo(recyclerlayout.getTotal(), dp100);
            if (!recyclerlayout.isScrollRefresh()) {
                recyclerlayout.setIsScrollRefresh(true);
                drop.setRefresh(true);
                background();
            }
        }
    }

    public void touchUp() {
        if (recyclerlayout.getTotal() >= dp100) {
            recyclerlayout.setScrollTo(recyclerlayout.getTotal(), dp100);
            if (!recyclerlayout.isScrollRefresh()) {
                recyclerlayout.setIsScrollRefresh(true);
                drop.setRefresh(true);
                background();
            }

        } else {
            recyclerlayout.setScrollTo(0, 0);
        }
    }


    @Background
    void background() {
        TopParams topparams = new TopParams(apikey, getParams());
        String params = topparams.getTopParams();
        json = httpUtils.getResponseForGet(url, params);
        thread();
    }

    @UiThread
    void thread() {
        TopReturn topReturn = new TopReturn();
        topReturn.setTopBean(jsonUtils.getObject(jsonUtils.getJson(json), TopBean.class));
        TopBean bean = topReturn.getTopBean();
        if (recyclerlayout.isScrollRefresh()) {
            list.clear();
        }
        list.add(bean);
        topNewsAdapter.setList(list);
        recyclerlayout.setIsScrollRefresh(false);
        drop.setRefresh(false);
        recyclerlayout.setScrollTo(dp100, 0);
        topNewsAdapter.notifyDataSetChanged();
    }

    private String getParams() {
        return "top";
    }
}
