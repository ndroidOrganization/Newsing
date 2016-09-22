package com.newsing.fragment.beauty.activity;

import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.newsing.BeautyDetailBinding;
import com.newsing.R;
import com.newsing.basic.BaseActivity;

/**
 * Created by Administrator on 2016/9/21 0021.
 * this page is show by BeautyFragment item clicked
 */
public class BeautyDetailActivity extends BaseActivity {

    public final static String IMAGEPATH ="imagePath";

    BeautyDetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_beautydetail);
        setUpImage();
    }

    private void setUpImage(){
        String filePath = getIntent().getStringExtra(IMAGEPATH);
        if(filePath != null)
            binding.beautyDetailImage.setImageBitmap(BitmapFactory.decodeFile(filePath));
    }
}
