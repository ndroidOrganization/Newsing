package com.newsing.fragment.topnews.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.newsing.R;
import com.newsing.fragment.topnews.adapter.OtherNewsAdapter;
import com.newsing.fragment.topnews.http.OtherBean;
import com.newsing.fragment.topnews.http.OtherParams;
import com.newsing.fragment.topnews.http.OtherReturn;
import com.newsing.interfaces.OnScrollListener;
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
public class InternationalViewPagerFragment extends Fragment implements OnScrollListener {

    @ViewById
    SWRecyclerViewLayout recyclerlayout;
    @DimensionPixelSizeRes(R.dimen.dp100)
    int dp100;
    private SWDropView drop;
    private View header;
    private OtherNewsAdapter otherNewsAdapter;
    @Bean
    JsonUtils jsonUtils;
    @Bean
    HttpUtils httpUtils;
    @StringRes
    String apikey;
    @StringRes
    String url;
    private JSONObject json;
    private ArrayList<OtherBean> list = new ArrayList<>();


    @AfterViews
    void afterview() {
        otherNewsAdapter = new OtherNewsAdapter(getContext());
        header = LayoutInflater.from(getContext()).inflate(R.layout.drop, null);
        drop = (SWDropView) header.findViewById(R.id.drop);
        recyclerlayout.addHeaderView(header, dp100);
        recyclerlayout.setMyRecyclerView(new LinearLayoutManager(getContext()), otherNewsAdapter);
        recyclerlayout.addScrollListener(this);
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

    public void animStop(float y) {
    }


    @Background
    void background() {
        OtherParams otherParams = new OtherParams(apikey, getParams());
        String params = otherParams.getOtherParams();
        json = httpUtils.getResponseForGet(url, params);
        thread();
    }

    @UiThread
    void thread() {
        OtherReturn otherReturn = new OtherReturn();
        otherReturn.setOtherBean(jsonUtils.getObject(jsonUtils.getJson(json), OtherBean.class));
        OtherBean bean = otherReturn.getOtherBean();
        if (recyclerlayout.isScrollRefresh()) {
            list.clear();
        }
        list.add(bean);
        otherNewsAdapter.setList(list);
        recyclerlayout.setIsScrollRefresh(false);
        drop.setRefresh(false);
        recyclerlayout.setScrollTo(dp100, 0);
        otherNewsAdapter.notifyDataSetChanged();
    }

    private String getParams() {
        return "guoji";
    }
}
