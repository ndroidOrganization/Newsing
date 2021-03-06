package com.newsing.fragment.beauty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.newsing.NewingApplication;
import com.newsing.R;
import com.newsing.basic.BaseFragment;
import com.newsing.basic.BaseInterface;
import com.newsing.fragment.beauty.net.NetItemModel;
import com.newsing.utils.FileManager;
import com.newsing.view.LoadLayout;


import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class BeautyFragment extends BaseFragment implements BaseInterface<File>{

    public final static int COLUMNCOUNT = 2;

    List<ItemModel> datats = new ArrayList<>();
    RecycleItemAdapter adapter = null;
    LoadLayout layout = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_beauty,container,false);

        layout = ((LoadLayout) view.findViewById(R.id.beauty_group));
        setUpHeadAndFooter(layout);
        layout.setPrecessChangeListener(precessChangeListener);


        RecyclerView recyclerView = layout.getRecycleView();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(COLUMNCOUNT,StaggeredGridLayoutManager.VERTICAL));
        adapter = new RecycleItemAdapter(datats,new WeakReference<>(getActivity()));
        recyclerView.setAdapter(adapter);

        //requestPics(10);

        return view;
    }

    private void setUpHeadAndFooter(LoadLayout layout){
        View textView = getLayoutInflater(null).inflate(R.layout.beauty_head_footer,null);
        layout.setHeadView(textView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,120));

        View textView2 = getLayoutInflater(null).inflate(R.layout.beauty_head_footer,null);
        layout.setFooterView(textView2,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,120));
    }

    private LoadLayout.onPrecessChangeListener precessChangeListener = new LoadLayout.onPrecessChangeListener() {
        @Override
        public void onLoad(View footer, int process) {
            //Log.d("footer",String.valueOf(process));
            TextView textView = ((TextView)footer.findViewById(R.id.beauty_decor_text));
            if(process < -95)
            {
                textView.setText(R.string.beauty_footer_text2);
            }else{
                textView.setText(R.string.beauty_footer_text1);
            }
        }

        @Override
        public void onRefresh(View header, int process) {
            //Log.d("header",String.valueOf(process));
            TextView textView = ((TextView)header.findViewById(R.id.beauty_decor_text));
            if(process > 95)
            {
                textView.setText(R.string.beauty_header_text2);
            }else{
                textView.setText(R.string.beauty_header_text1);
            }
        }

        @Override
        public void Loading() {
            requestPics(10);
        }

        @Override
        public void Refreshing() {

        }
    };

    private void requestPics(int i) {
        BeautyFragmentPresenter.requestPics(i,callback);
    }

    final BaseInterface<List<NetItemModel>> callback = new BaseInterface<List<NetItemModel>>() {
        @Override
        public void onComplete(List<NetItemModel> result) {
            layout.onComplete();
            for(NetItemModel item:result)
            {
                downImage(item.getUri());
            }
        }

        @Override
        public void onError(@StringRes int resId) {

        }
    };

    @Override
    public String getTabName(){
        return "美女";
    }

    @Override
    public void refresh() {

    }


    private void downImage(String uri){
//        FileManager.getInstance(NewingApplication.getInstance())
//                .DownloadBitmap(uri,new WeakReference<>(getActivity().getApplication()),this);
    }

    @Override
    public void onComplete(final File result) {
        if(result != null){
            datats.add(new ItemModel(result.getPath()));
            adapter.notifyItemInserted(datats.size()-1);
        }
        if(datats.size() > 15)
        {
            layout.onComplete();
        }
    }

    @Override
    public void onError(@StringRes int resId) {

    }
}
