package com.newsing.fragment;

import android.support.annotation.StringRes;

import com.newsing.basic.BaseInterface;
import com.newsing.interfaces.IPresenterAction;
import com.newsing.interfaces.IPresenterCallback;
import com.newsing.utils.NetWorkUtils;

/**
 * Created by Administrator on 2017/7/23 0023.
 */

public class ActionPresenter implements IPresenterAction {
    private IPresenterCallback baseInterface = null;

    public ActionPresenter(IPresenterCallback baseInterface) {
        this.baseInterface = baseInterface;
    }

    public void async(String types){
        NetWorkUtils.getInstance().ALIGet_Sync(new BaseInterface<String>() {
            @Override
            public void onComplete(String result) {
                if(baseInterface != null)
                    baseInterface.onRequestComplete(result);
            }

            @Override
            public void onError(@StringRes int resId) {
//                if(baseInterface != null)
//                    baseInterface.onRequestError(result);
            }
        }, types);
    }
}
