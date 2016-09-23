package com.newsing.fragment.beauty;

import com.newsing.basic.BaseInterface;
import com.newsing.fragment.beauty.net.NetJsonResponse;
import com.newsing.utils.ConstValue;
import com.newsing.utils.NetWorkUtils;
import com.newsing.fragment.beauty.net.NetItemModel;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by qzzhu on 16-9-23.
 */

public class BeautyFragmentPresenter {

    public static void requestPics(final int numbers,final BaseInterface<List<NetItemModel>> callBack){
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Response response = null;
                try {
                    response = NetWorkUtils.getInstance()
                            .Get_Sync(ConstValue.InterFace_Beauty.getBeautyUri(numbers),
                                    ConstValue.InterFace_Beauty.getHeaders());
                    subscriber.onNext(response.body().string());
                } catch (IOException e) {
                    subscriber.onNext(null);
                }
                subscriber.onCompleted();
            }
        }).map(new Func1<String, List<NetItemModel>>() {
            @Override
            public List<NetItemModel> call(String s) {
                if(s != null) {
                    return new NetJsonResponse().setUp(s,numbers).getParsedDats();
                }
                return null;
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<List<NetItemModel>>() {
                @Override
                public void call(List<NetItemModel> netItemModels) {
                    callBack.onComplete(netItemModels);
                }
            });
    }
}
