package com.newsing.fragment.beauty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsing.NewingApplication;
import com.newsing.R;
import com.newsing.basic.BaseFragment;
import com.newsing.basic.BaseInterface;
import com.newsing.utils.FileManager;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beauty,container,false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.beauty_recycle);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(COLUMNCOUNT,StaggeredGridLayoutManager.VERTICAL));
        adapter = new RecycleItemAdapter(datats,new WeakReference<>(getActivity()));
        recyclerView.setAdapter(adapter);

        requestPics();

        return view;
    }

    private void requestPics(){
        //test
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJ1fJWJ-IJFrFAAMdgtxozEgAAU-KQNnR7wAAx2a147.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJ1fJWKCIJlicAALAo_WW1kwAAU-KQNtNYoAAsC7997.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJlfJWKCINRzqAALQHHEm9jgAAU-KQNqZVYAAtA0983.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJlfJWJ-IATyqAAKLmDZwFfYAAU-KQNkvAwAAouw067.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJ1fJWJ-Ib1c5AAKQ7neu_rUAAU-KQNfbm0AApEG253.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJlfJWJqIWPg3AAL4D9wtdhwAAU-KQHvrNAAAvgn153.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJ1fJWJ-IJFrFAAMdgtxozEgAAU-KQNnR7wAAx2a147.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJ1fJWKCIJlicAALAo_WW1kwAAU-KQNtNYoAAsC7997.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJlfJWKCINRzqAALQHHEm9jgAAU-KQNqZVYAAtA0983.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJlfJWJ-IATyqAAKLmDZwFfYAAU-KQNkvAwAAouw067.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJ1fJWJ-Ib1c5AAKQ7neu_rUAAU-KQNfbm0AApEG253.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJlfJWJqIWPg3AAL4D9wtdhwAAU-KQHvrNAAAvgn153.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJ1fJWJ-IJFrFAAMdgtxozEgAAU-KQNnR7wAAx2a147.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJ1fJWKCIJlicAALAo_WW1kwAAU-KQNtNYoAAsC7997.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJlfJWKCINRzqAALQHHEm9jgAAU-KQNqZVYAAtA0983.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJlfJWJ-IATyqAAKLmDZwFfYAAU-KQNkvAwAAouw067.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJ1fJWJ-Ib1c5AAKQ7neu_rUAAU-KQNfbm0AApEG253.jpg");
        request("http://sjbz.fd.zol-img.com.cn/t_s320x510c/g5/M00/00/04/ChMkJlfJWJqIWPg3AAL4D9wtdhwAAU-KQHvrNAAAvgn153.jpg");
    }

    public String getTabName(){
        return "Beauty";
    }

    private void request(String uri){
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
