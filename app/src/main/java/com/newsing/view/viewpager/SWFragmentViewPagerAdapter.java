package com.newsing.view.viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Angel on 2016/9/14.
 */
public class SWFragmentViewPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    public SWFragmentViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void setFragmentList(Fragment... fragment) {
        fragmentList.clear();
        for (int i = 0; i < fragment.length; i++) {
            fragmentList.add(fragment[i]);
        }
    }
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    public Fragment getItem(int index) {
        return fragmentList.get(index);
    }

    public int getCount() {
        return fragmentList.size();
    }
}