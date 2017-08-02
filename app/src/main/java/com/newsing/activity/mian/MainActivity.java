package com.newsing.activity.mian;

import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.newsing.MainGroupBinding;
import com.newsing.R;
import com.newsing.activity.mian.model.MainModel;
import com.newsing.activity.mycollect.CollectionsActivity;
import com.newsing.activity.trchat.TRChatActivity;
import com.newsing.activity.weather.WeatherActivity;
import com.newsing.basic.BaseActivity;
import com.newsing.basic.BaseFragment;
import com.newsing.fragment.abroad.AbroadFragment;
import com.newsing.fragment.china.ChinaFragment;
import com.newsing.fragment.entertainment.ETFragment;
import com.newsing.fragment.fashion.FashionFragment;
import com.newsing.fragment.finance.FinanceFragment;
import com.newsing.fragment.military.MilitaryFragment;
import com.newsing.fragment.society.SocietyFragment;
import com.newsing.fragment.sports.SportsFragment;
import com.newsing.fragment.technology.TechFragment;
import com.newsing.fragment.topnews.TopNewsFragment;
import com.newsing.utils.ActivityTranslater;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private static boolean TEST = true;

    MainGroupBinding binding = null;
    MainModel model = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View groupView = findViewById(R.id.layout_group);
        binding = DataBindingUtil.bind(groupView);
        model = new MainModel<>();

        setUpToolBar();
        setUpDrawerBar();

        setUpFragments();
    }

    private void setUpFragments() {
        BaseFragment topnews = new TopNewsFragment();
        BaseFragment society = new SocietyFragment();
        BaseFragment china = new ChinaFragment();
        BaseFragment abroad = new AbroadFragment();
        BaseFragment entertainment = new ETFragment();
        BaseFragment sports = new SportsFragment();
        BaseFragment military = new MilitaryFragment();
        BaseFragment tech = new TechFragment();
        BaseFragment finence = new FinanceFragment();
        BaseFragment fashion = new FashionFragment();

        BaseFragment[] fragments = new BaseFragment[]{topnews, society, china, abroad,
                entertainment, sports, military, tech, finence, fashion};
        model.setAdapter(this, TEST ? new BaseFragment[]{fragments[0]} : fragments);

        String[] tabNames = new String[]{topnews.getTabName(), society.getTabName(), china.getTabName(),
                abroad.getTabName(), entertainment.getTabName(), sports.getTabName(), military.getTabName(),
                tech.getTabName(), finence.getTabName(), fashion.getTabName()};

        setUpTabsAndViewPager(TEST ? new String[]{tabNames[0]} : tabNames);
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

//    @Override
//    protected void setUpWindowScene() {
//        Window window = getWindow();
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            window.setReturnTransition(new Fade());
//            window.setExitTransition(new Fade());
//        }
//    }

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

        if (id == R.id.nav_mycollect) {
            // Handle the camera action
            ActivityTranslater.GoTo(this,CollectionsActivity.class,null);
        } else if (id == R.id.nav_myabout) {

        } else if (id == R.id.nav_android) {
            ActivityCompat.requestPermissions(this,new String[]{"android.permission.READ_PHONE_STATE"},1);
        } else if (id == R.id.nav_weather) {
            ActivityTranslater.GoTo(this,WeatherActivity.class,null);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            ActivityTranslater.GoTo(this,TRChatActivity.class,null);
        }else{
            //permission denied
        }
    }
}
