package com.newsing.basic;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.newsing.fragment.ActionPresenter;
import com.newsing.fragment.topnews.ListAdapter;
import com.newsing.interfaces.IPresenterCallback;
import com.newsing.utils.ConstValue;

/**
 * Created by Administrator on 2016/9/20 0020.
 *
 */
public abstract class BaseFragment extends Fragment implements IPresenterCallback {

    private BaseInterface baseInterface;

    protected ActionPresenter actionPresenter;

    protected RecyclerView recyclerView;
    protected ListAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        baseInterface = (BaseInterface) context;
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
        if(null != baseInterface)
            baseInterface.onComplete(null);
    }

    protected void async(String types){
        Log.i("network","request type = "+types+"\t"+getTabName());
        if(actionPresenter != null)
            actionPresenter.async(types);
    }

    @Override
    public void onRequestComplete(String args) {

    }

    @Override
    public void onRequestError(Exception e) {

    }
}
