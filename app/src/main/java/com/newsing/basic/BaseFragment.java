package com.newsing.basic;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.newsing.fragment.ActionPresenter;
import com.newsing.interfaces.IPresenterAction;
import com.newsing.activity.mian.adapter.ListAdapter;
import com.newsing.interfaces.IPresenterCallback;

/**
 * Created by Administrator on 2016/9/20 0020.
 *
 */
public abstract class BaseFragment extends Fragment implements IPresenterCallback,SwipeRefreshLayout.OnRefreshListener {

//    private BaseInterface baseInterface;

    protected IPresenterAction actionPresenter;

    protected RecyclerView recyclerView;
    protected SwipeRefreshLayout swipeRefreshLayout;
    protected ListAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        actionPresenter = new ActionPresenter(this);
    }

    /**
     * subClass must override this method to tell what tabs show display
     * @return
     */
    public String getTabName(){
        return "UNKNOWN";
    }

    public abstract void refresh();

    protected void onRefreshComplete(){
        if(null != swipeRefreshLayout)
            swipeRefreshLayout.setRefreshing(false);
    }

    protected void async(String types){
        Log.i("network","request type = "+types+"\t"+getTabName());
        if(actionPresenter != null)
            actionPresenter.async(types);
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    @Override
    public void onRequestComplete(String args) {

    }

    @Override
    public void onRequestError(Exception e) {
        if(null != swipeRefreshLayout)
            swipeRefreshLayout.setRefreshing(false);
    }
}
