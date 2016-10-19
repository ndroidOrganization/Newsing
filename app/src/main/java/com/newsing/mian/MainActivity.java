package com.newsing.mian;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.StringRes;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.newsing.MainGroupBinding;
import com.newsing.R;
import com.newsing.basic.BaseActivity;
import com.newsing.basic.BaseFragment;
import com.newsing.basic.BaseInterface;
import com.newsing.fragment.beauty.BeautyFragment;
import com.newsing.fragment.topnews.TopNewsFragment;
import com.newsing.fragment.topnews.TopNewsFragment_;
import com.newsing.fragment.topnews.viewpagerfragment.AmuseViewPagerFragment_;
import com.newsing.fragment.topnews.viewpagerfragment.FashionViewPagerFragment_;
import com.newsing.fragment.topnews.viewpagerfragment.FinanceViewPagerFragment_;
import com.newsing.fragment.topnews.viewpagerfragment.HomeViewPagerFragment_;
import com.newsing.fragment.topnews.viewpagerfragment.InternationalViewPagerFragment_;
import com.newsing.fragment.topnews.viewpagerfragment.MilitaryViewPagerFragment_;
import com.newsing.fragment.topnews.viewpagerfragment.NewsViewPagerFragment_;
import com.newsing.fragment.topnews.viewpagerfragment.SocietyViewPagerFragment_;
import com.newsing.fragment.topnews.viewpagerfragment.SportViewPagerFragment_;
import com.newsing.fragment.topnews.viewpagerfragment.TechnologyViewPagerFragment_;
import com.newsing.mian.model.MainModel;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseInterface<String> {

    MainGroupBinding binding = null;
    MainModel model = null;

    BaseFragment beautyFragment = null;
    //BaseFragment topNewsFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View groupView = findViewById(R.id.layout_group);
        binding = DataBindingUtil.bind(groupView);
        model = new MainModel<>(this);

        setUpToolBar();
        setUpDrawerBar();

        setUpFragments();
    }

    private void setUpFragments() {
        beautyFragment = new BeautyFragment();
        //topNewsFragment = new TopNewsFragment_();
        NewsViewPagerFragment_ news = new NewsViewPagerFragment_();
        SocietyViewPagerFragment_ social = new SocietyViewPagerFragment_();
        HomeViewPagerFragment_ home = new HomeViewPagerFragment_();
        InternationalViewPagerFragment_ internation = new InternationalViewPagerFragment_();
        AmuseViewPagerFragment_ assume = new AmuseViewPagerFragment_();
        SportViewPagerFragment_ suport = new SportViewPagerFragment_();
        MilitaryViewPagerFragment_ mutiview = new MilitaryViewPagerFragment_();
        TechnologyViewPagerFragment_ technology = new TechnologyViewPagerFragment_();
        FinanceViewPagerFragment_ finance = new FinanceViewPagerFragment_();
        FashionViewPagerFragment_ fashion = new FashionViewPagerFragment_();
        model.setAdapter(this,beautyFragment,
                news,
                social,
                home,
                internation,
                assume,
                suport,
                mutiview,
                technology,
                finance,
                fashion);
        setUpTabsAndViewPager(beautyFragment.getTabName(),
                news.getTabName(),
                social.getTabName(),
                home.getTabName(),
                internation.getTabName(),
                assume.getTabName(),
                suport.getTabName(),
                mutiview.getTabName(),
                technology.getTabName(),
                finance.getTabName(),
                fashion.getTabName());
    }

    private void setUpTabsAndViewPager(String... tabName){
        binding.mainpagertab.setTabMode(TabLayout.MODE_SCROLLABLE);
        binding.mianviewpager.setAdapter(model.getAdapter());

        //this func will remove all tabs that has exist and new tab
        //see open source
        binding.mainpagertab.setupWithViewPager(binding.mianviewpager);

        //only this way can modify tabs
        int length = tabName.length;
        int tabcount = binding.mainpagertab.getTabCount();
        for(int i =0;i<length && i<tabcount;i++)
        {
            binding.mainpagertab.getTabAt(i).setText(tabName[i]);
        }
    }



    private void setUpToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setUpDrawerBar(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, binding.toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
//        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public void onComplete(String result) {

    }

    @Override
    public void onError(@StringRes int resId) {

    }
}
