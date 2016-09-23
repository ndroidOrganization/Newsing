package com.newsing.fragment.topnews.viewpagerfragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.newsing.R;
import com.newsing.fragment.topnews.adapter.TopNewsAdapter;
import com.newsing.interfaces.OnScrollListener;
import com.newsing.view.SWDropView;
import com.newsing.view.SWRecyclerViewLayout;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

/**
 * Created by Angel on 2016/9/22.
 */
@EFragment(R.layout.viewpager_news)
public class NewsViewPagerFragment extends Fragment implements OnScrollListener {

    @ViewById
    SWRecyclerViewLayout recyclerlayout;
    @DimensionPixelSizeRes(R.dimen.dp100)
    int dp100;
    private SWDropView drop;
    private View header;
    private TopNewsAdapter topNewsAdapter;

    @AfterViews
    void afterview() {
        topNewsAdapter = new TopNewsAdapter(getContext());
        header = LayoutInflater.from(getContext()).inflate(R.layout.drop, null);
        drop = (SWDropView) header.findViewById(R.id.drop);
        recyclerlayout.addHeaderView(header, dp100);
        recyclerlayout.setMyRecyclerView(new LinearLayoutManager(getContext()), topNewsAdapter);
        recyclerlayout.addScrollListener(this);
    }

    public void touchUp() {
//        if (recyclerlayout.getTotal() >= dp100) {
//            recyclerlayout.setScrollTo(recyclerlayout.getTotal(), dp100);
//        } else if (recyclerlayout.getTotal() < dp100) {
//            recyclerlayout.setScrollTo(0, 0);
//        }
        recyclerlayout.setScrollTo(0, 0);
    }

    public void animStop(float y) {

    }
}
