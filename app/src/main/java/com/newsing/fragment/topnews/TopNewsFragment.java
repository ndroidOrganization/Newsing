package com.newsing.fragment.topnews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsing.R;
import com.newsing.basic.BaseFragment;
import com.newsing.fragment.topnews.viewpagerfragment.NewsViewPagerFragment_;
import com.newsing.interfaces.OnScrollListener;
import com.newsing.view.SWDropView;
import com.newsing.view.SWLoadView;
import com.newsing.view.SWRecyclerViewLayout;
import com.newsing.view.viewpager.SWViewPager;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.DimensionPixelSizeRes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
@EFragment(R.layout.fragment_topnews)
public class TopNewsFragment extends BaseFragment {

    @ViewById
    TabLayout tablayout;
    @ViewById
    SWViewPager viewpager;
    private List<String> titles = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @AfterViews
    void afterviews() {
        titles.add("头条");
        titles.add("资讯");
        titles.add("体育");
        titles.add("博客");
        titles.add("杂志");
        tablayout.setTabMode(TabLayout.MODE_FIXED);
        fragmentList.add(new NewsViewPagerFragment_());
        fragmentList.add(new NewsViewPagerFragment_());
        fragmentList.add(new NewsViewPagerFragment_());
        fragmentList.add(new NewsViewPagerFragment_());
        fragmentList.add(new NewsViewPagerFragment_());
        tablayout.addTab(tablayout.newTab().setText(titles.get(0)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(1)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(2)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(3)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(4)));
        MyPagerAdapter adapter = new MyPagerAdapter(getFragmentManager());
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        tablayout.setTabsFromPagerAdapter(adapter);
    }

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    public String getTabName() {
        return "Tops";
    }
}
