package com.newsing.fragment.beauty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsing.NewingApplication;
import com.newsing.R;
import com.newsing.basic.BaseFragment;
import com.newsing.basic.BaseInterface;
import com.newsing.fragment.beauty.net.NetItemModel;
import com.newsing.utils.ConstValue;
import com.newsing.utils.FileManager;
import com.newsing.utils.NetWorkUtils;
import com.newsing.view.LoadLayout;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class BeautyFragment extends BaseFragment implements BaseInterface<File>{

    public final static int COLUMNCOUNT = 2;

    List<ItemModel> datats = new ArrayList<>();
    RecycleItemAdapter adapter = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty,container,false);
        RecyclerView recyclerView = ((LoadLayout) view.findViewById(R.id.beauty_group)).getRecycleView();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(COLUMNCOUNT,StaggeredGridLayoutManager.VERTICAL));
        adapter = new RecycleItemAdapter(datats,new WeakReference<>(getActivity()));
        recyclerView.setAdapter(adapter);

        requestPics(10);

        return view;
    }

    private void requestPics(int i) {
        BeautyFragmentPresenter.requestPics(i,callback);
    }

    final BaseInterface<List<NetItemModel>> callback = new BaseInterface<List<NetItemModel>>() {
        @Override
        public void onComplete(List<NetItemModel> result) {
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
        return "Beauty";
    }


    private void downImage(String uri){
        FileManager.getInstance(NewingApplication.getInstance())
                .DownloadBitmap(uri,new WeakReference<>(getActivity().getApplication()),this);
    }

    @Override
    public void onComplete(final File result) {
        if(result != null){
            datats.add(new ItemModel(result.getPath()));
            adapter.notifyItemInserted(datats.size()-1);
        }
    }

    @Override
    public void onError(@StringRes int resId) {

    }
}
