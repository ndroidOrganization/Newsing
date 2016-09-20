package com.newsing.mian.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.newsing.basic.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/9/20 0020.
 *
 */
public class ViewPagerAdapyer extends FragmentStatePagerAdapter {
    private List<BaseFragment> pages = null;

    public ViewPagerAdapyer(FragmentManager fm, List<BaseFragment> datas) {
        super(fm);
        pages = datas;
    }

    @Override
    public Fragment getItem(int position) {
        return pages.get(position);
    }

    @Override
    public int getCount() {
        return pages.size();
    }
}
