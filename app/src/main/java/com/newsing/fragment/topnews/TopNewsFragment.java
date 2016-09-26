package com.newsing.fragment.topnews;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.newsing.R;
import com.newsing.basic.BaseFragment;
import com.newsing.fragment.topnews.viewpagerfragment.*;
import com.newsing.view.viewpager.SWViewPager;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

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
        titles.add("社会");
        titles.add("国内");
        titles.add("国际");
        titles.add("娱乐");
        titles.add("体育");
        titles.add("军事");
        titles.add("科技");
        titles.add("财经");
        titles.add("时尚");
        fragmentList.add(new NewsViewPagerFragment_());
        fragmentList.add(new SocietyViewPagerFragment_());
        fragmentList.add(new HomeViewPagerFragment_());
        fragmentList.add(new InternationalViewPagerFragment_());
        fragmentList.add(new AmuseViewPagerFragment_());
        fragmentList.add(new SportViewPagerFragment_());
        fragmentList.add(new MilitaryViewPagerFragment_());
        fragmentList.add(new TechnologyViewPagerFragment_());
        fragmentList.add(new FinanceViewPagerFragment_());
        fragmentList.add(new FashionViewPagerFragment_());
        tablayout.addTab(tablayout.newTab().setText(titles.get(0)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(1)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(2)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(3)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(4)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(5)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(6)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(7)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(8)));
        tablayout.addTab(tablayout.newTab().setText(titles.get(9)));
        MyPagerAdapter adapter = new MyPagerAdapter(getChildFragmentManager());
        viewpager.setOffscreenPageLimit(9);
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
