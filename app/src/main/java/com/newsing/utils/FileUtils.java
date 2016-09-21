package com.newsing.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.newsing.basic.BaseInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by qzzhu on 16-9-21.
 */

public class FileUtils {
    public final static String PrivateDir_Beauty = "beauty";

    public static void checkFileDir(Context context){
        File privateDir = context.getFilesDir();
        File beauty = new File(privateDir,PrivateDir_Beauty);
        if(!beauty.exists())
        {
            boolean success = beauty.mkdir();
            if(!success)
                Log.w("beauty dir create","failed");
        }
    }

    public static File GetBeautyFile(Context context,String fileName){
        String fname = fileName.substring(fileName.lastIndexOf("/"),fileName.length());
        return new File(getBeautyFileDir(context),fname);
    }

    private static File getBeautyFileDir(Context context){
//        return new File(context.getFilesDir(),PrivateDir_Beauty);
        File file = new File(Environment.getExternalStorageDirectory(),PrivateDir_Beauty);
        if(!file.exists())
            file.mkdirs();
        return file;
    }

    public static void DownloadBitmap(final String icUri, final Application context, final BaseInterface<File> callback){

        if(FileUtils.GetBeautyFile(context,icUri).exists())
        {
            //file already exist
            callback.onComplete(FileUtils.GetBeautyFile(context,icUri));
            return ;
        }
        //first request the bitmap inputStream
        Observable.create(new Observable.OnSubscribe<InputStream>() {
            @Override
            public void call(Subscriber<? super InputStream> subscriber) {
                try {
                    Response response = NetWorkUtils.getInstance().Get_Sync(icUri);
                    subscriber.onNext(response.body().byteStream());
                    subscriber.onCompleted();
                } catch (IOException e) {
                    subscriber.onError(e);
                    //callback.onError();
                }
            }
        }).map(new Func1<InputStream,Bitmap>() {//this decode bitmap
            @Override
            public Bitmap call(InputStream inputStream) {
                return BitmapFactory.decodeStream(inputStream);
            }
        }).map(new Func1<Bitmap, File>() {//this write to private file dir
            @Override
            public File call(Bitmap bitmap) {
                File file = null;
                try {
                    file = FileUtils.GetBeautyFile(context,icUri);
                    FileOutputStream os = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG,50,os);
                    os.close();
                    bitmap.recycle();
                } catch (Exception e) {
                    //file not found
                    //callback.onError();
                    e.printStackTrace();
                }
                return file;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<File>() {//this notify complete
                    @Override
                    public void call(File file) {
                        callback.onComplete(file);
                    }
                });
    }
}
