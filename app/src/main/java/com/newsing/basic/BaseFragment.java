package com.newsing.basic;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;

import com.newsing.fragment.topnews.ListAdapter;

/**
 * Created by Administrator on 2016/9/20 0020.
 *
 */
public abstract class BaseFragment extends Fragment {

    private BaseInterface baseInterface;

    protected RecyclerView recyclerView;
    protected ListAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseInterface = (BaseInterface) context;
    }

    /**
     * subClass must override this method to tell what tabs show display
     * @return
     */
    public String getTabName(){
        return "UNKNOWN";
    }

    public void refresh(){}

    protected void onRefreshComplete(){
        if(null != baseInterface)
            baseInterface.onComplete(null);
    }
}
