package com.newsing.fragment.topnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsing.R;
import com.newsing.basic.BaseFragment;
import com.newsing.fragment.topnews.adapter.NewsAdapter;
import com.newsing.interfaces.OnScrollListener;
import com.newsing.view.SWDropView;
import com.newsing.view.SWLoadView;
import com.newsing.view.SWRecyclerViewLayout;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
@EFragment(R.layout.fragment_topnews)
public class TopNewsFragment extends BaseFragment implements OnScrollListener {

    @ViewById
    SWRecyclerViewLayout recyclerlayout;
    NewsAdapter adapter;
    @DimensionPixelSizeRes(R.dimen.dp100)
    int dp100;
    private static final String TAG = "RecyclerActivity";
    private View header;
    private View footer;
    private SWDropView drop;
    private SWLoadView load;

    @AfterViews
    void afterview() {
        adapter = new NewsAdapter(getContext());
        header = LayoutInflater.from(getContext()).inflate(R.layout.drop, null);
        footer = LayoutInflater.from(getContext()).inflate(R.layout.load, null);
        drop = (SWDropView) header.findViewById(R.id.drop);
        load = (SWLoadView) footer.findViewById(R.id.load);
        recyclerlayout.addHeaderView(header, dp100);
        recyclerlayout.addFooterView(footer, dp100);
        recyclerlayout.setMyRecyclerView(new LinearLayoutManager(getContext()), adapter);
        recyclerlayout.addScrollListener(this);
    }

    public void touchUp() {
        if (recyclerlayout.getTotal() >= dp100) {
            recyclerlayout.setScrollTo(recyclerlayout.getTotal(), dp100);
        } else if (-recyclerlayout.getTotal() >= dp100) {
            recyclerlayout.setScrollTo(recyclerlayout.getTotal(), -dp100);
        } else if (recyclerlayout.getTotal() < dp100 || -recyclerlayout.getTotal() < dp100) {
            recyclerlayout.setScrollTo(0, 0);
        }
    }

    //判断是否需要刷新和加载
    public void animStop(float y) {
        if (y > 0) {
            if (!recyclerlayout.isScrollRefresh()) {
                recyclerlayout.setIsScrollRefresh(true);
                drop.setRefresh(true);
            }
        } else if (y < 0) {
            if (!recyclerlayout.isScrollLoad()) {
                recyclerlayout.setIsScrollLoad(true);
                load.setLoad(true);
            }
        }
    }

    public String getTabName() {
        return "Tops";
    }
}
