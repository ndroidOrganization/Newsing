package com.newsing.utils;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.newsing.NewingApplication;
import com.newsing.basic.BaseInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;

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

public class FileManager {
    private final static String PrivateDir_Beauty = "beauty";

    private LruCache<String, Bitmap> mMemoryCache = null;
    private static FileManager instance = null;
    private FileManager(Application context){
        //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        //给LruCache分配1/8 4M
        mMemoryCache = new LruCache<String, Bitmap>(mCacheSize){
            //必须重写此方法，来测量Bitmap的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight();
            }
        };
    }

    public static FileManager getInstance(Application context) {
        if (instance == null)
        {
            synchronized (FileManager.class)
            {
                if(instance == null)
                {
                    instance = new FileManager(context);
                }
            }
        }
        return instance;
    }

    //默认初始化检查一次是否存在该文件夹
    public static void checkFileDir(Context context){
        File beauty = getBeautyFileDir(context);
        if(!beauty.exists())
        {
            boolean success = beauty.mkdir();
            if(!success)
                Log.w("beauty dir create","failed");
        }
    }

    /**
     * 取网络路径的最后/后缀作为文件名
     * @param context context
     * @param fileName net path
     * @return File that has get rid of suffix
     */
    public static File GetBeautyFile(Context context,String fileName){
        String fname = fileName.substring(fileName.lastIndexOf("/"),fileName.length());
        return new File(getBeautyFileDir(context),fname);
    }

    private static File getBeautyFileDir(Context context){
//      new File(context.getFilesDir(),PrivateDir_Beauty);
        File file = new File(Environment.getExternalStorageDirectory(),PrivateDir_Beauty);
        if(!file.exists())
            file.mkdirs();
        return file;
    }

    /**
     * downLoad image and write to local file if file not exist
     * @param icUri request uri
     * @param context context
     * @param callback background callback
     */
    public void DownloadBitmap(final String icUri, final WeakReference<Application> context, final BaseInterface<File> callback){

        if(FileManager.GetBeautyFile(context.get(),icUri).exists())
        {
            //file already exist
            callback.onComplete(FileManager.GetBeautyFile(context.get(),icUri));
            return ;
        }

        if(!NetWorkUtils.isNetWorkAvailable(NewingApplication.getInstance()))
        {
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
//                    subscriber.onError(e);
                    //network unavailable
                    e.printStackTrace();
                }
            }

        }).map(new Func1<InputStream,Bitmap>() {//this decode bitmap
            @Override
            public Bitmap call(InputStream inputStream) {
                if(inputStream != null)
                    return BitmapFactory.decodeStream(inputStream);
                else
                    return null;
            }
        }).map(new Func1<Bitmap, File>() {//this write to private file dir
            @Override
            public File call(Bitmap bitmap) {
                File file = null;
                try {
                    if(context.get() == null)
                        return null;
                    if(bitmap != null) {
                        file = FileManager.GetBeautyFile(context.get(), icUri);
                        FileOutputStream os = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 50, os);
                        os.flush();
                        os.close();
//                        bitmap.recycle();
                        mMemoryCache.put(file.getName(),bitmap);
                    }
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
                        if(callback != null)
                            callback.onComplete(file);
                    }
                });
    }

    private Bitmap getImageFromCache(String filePath){
        if(filePath == null)
            return null;
        else
            return mMemoryCache.get(new File(filePath).getName());
    }

    /**
     * scale the image to fit the imageView
     * @param filePath path
     * @return the height that ImageView can take
     */
    private Bitmap loadImageFromFile(int width , String filePath){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap map = null;
        map = BitmapFactory.decodeFile(filePath,options);
        float imageWidth = options.outWidth * 1.0f;
        options.outHeight = (int) (width *options.outHeight/imageWidth);
        options.outWidth = width;
        options.inJustDecodeBounds = false;
        map = BitmapFactory.decodeFile(filePath,options);
        if(filePath != null && map != null) {
            mMemoryCache.put(new File(filePath).getName(), map);
        }
        return map;
    }

    public Bitmap loadBitmap(int width , String filePath){
        Bitmap map = getImageFromCache(filePath);
        if(map == null)
        {
            map= loadImageFromFile(width, filePath);
        }
        return map;
    }
}
