package com.newsing.view.jack_ma;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import com.newsing.R;
import com.newsing.utils.MD5Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by Angel on 2016/8/16.
 */
public class Jack_Ma {

    private static final String TAG = "Jack_Ma";
    // 图片缓存的核心对象
    private LruCache<String, Bitmap> lruCache;
    private Context context;
    //任务队列
    private LinkedList<Runnable> taskQueue = new LinkedList<Runnable>();
    //线程池
    private ExecutorService threadPool;
    private Semaphore semaphoreThreadPool;
    private Semaphore semaphorePoolThreadHandler = new Semaphore(0);
    //UI线程
    private Handler uiHandler;
    //后台线程
    private Thread backthread;
    private Handler backthreadhandler;
    //线程数量
    private static final int THREAD_POOL_COUNT = 3;
    private static Jack_Ma jack_ma;

    public static Jack_Ma with(Context context) {
        if (jack_ma == null) {
            synchronized (Jack_Ma.class) {
                if (jack_ma == null) {
                    jack_ma = new Jack_Ma(context);
                }
            }
        }
        return jack_ma;
    }


    public Jack_Ma(Context context) {
        this.context = context;
        setLruCache();
        BackGroundThread();
    }

    private void BackGroundThread() {
        // 后台轮询线程
        backthread = new Thread() {
            public void run() {
                Looper.prepare();
                backthreadhandler = new Handler() {
                    public void handleMessage(Message msg) {
                        // 线程池去取出一个任务进行执行
                        threadPool.execute(taskQueue.removeLast());
                        try {
                            semaphoreThreadPool.acquire();
                        } catch (InterruptedException e) {
                        }
                    }
                };
                semaphorePoolThreadHandler.release();
                Looper.loop();
            }
        };
        backthread.start();
    }

    private void setLruCache() {
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
        // 创建线程池
        threadPool = Executors.newFixedThreadPool(THREAD_POOL_COUNT);
        semaphoreThreadPool = new Semaphore(THREAD_POOL_COUNT);
    }

    public void loadImage(final String path, final ImageView imageView, boolean isFromNet, int angle) {
        imageView.setTag(path);
        imageView.setImageResource(R.drawable.ic_loading);
        if (uiHandler == null) {
            uiHandler = new Handler() {
                public void handleMessage(Message msg) {
                    myImageBean myImageBean = (myImageBean) msg.obj;
                    Bitmap bm = myImageBean.bitmap;
                    ImageView imageview = myImageBean.imageView;
                    String path = myImageBean.path;
                    if (imageview.getTag().toString().equals(path)) {
                        imageview.setImageBitmap(bm);
                    }
                }
            };
        }
        // 根据path在缓存中获取bitmap
        Bitmap bitmap = getBitmapFromLruCache(path);
        if (bitmap != null) {
            refreashBitmap(path, imageView, bitmap);
        } else {
            addTask(buildTask(path, imageView, isFromNet, angle));
        }
    }

    private void addTask(Runnable runnable) {
        taskQueue.add(runnable);
        try {
            if (backthreadhandler == null)
                semaphorePoolThreadHandler.acquire();
        } catch (InterruptedException e) {
        }
        backthreadhandler.sendEmptyMessage(0x250);
    }

    private Runnable buildTask(final String path, final ImageView imageView, final boolean isFromNet, final int angle) {
        return new Runnable() {
            public void run() {
                Bitmap bitmap = null;
                if (isFromNet) {
                    File file = getDiskCacheDir(context, MD5Utils.hashKeyFromUrl(path));
                    if (file.exists()) {
                        bitmap = loadImageFromLocal(file.getAbsolutePath(), imageView, angle);
                    } else {
                        boolean success = downloadImgByUrl(path, file);
                        if (success) {
                            bitmap = loadImageFromLocal(file.getAbsolutePath(), imageView, angle);
                        }
                    }
                } else {
                    bitmap = loadImageFromLocal(path, imageView, angle);
                }
                if (lruCache.get(path) == null) {
                    if (bitmap != null)
                        lruCache.put(path, bitmap);
                }
                addBitmapToLruCache(path, bitmap);
                refreashBitmap(path, imageView, bitmap);
                semaphoreThreadPool.release();
            }

        };
    }

    private void addBitmapToLruCache(String path, Bitmap bm) {
        if (getBitmapFromLruCache(path) == null) {
            if (bm != null) {
                lruCache.put(path, bm);
            }
        }
    }

    private Bitmap getBitmapFromLruCache(String key) {
        return lruCache.get(key);
    }

    private Bitmap loadImageFromLocal(String path, ImageView imageView, int angle) {
        Bitmap bm;
        bm = SampledBitmap(path, imageView.getWidth(), imageView.getHeight(), angle);
        return bm;
    }

    public static boolean downloadImgByUrl(String urlStr, File file) {
        boolean isok = false;
        FileOutputStream fos = null;
        InputStream is = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            is = conn.getInputStream();
            fos = new FileOutputStream(file);
            byte[] buf = new byte[1024 * 4];
            int len = 0;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            isok = true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
            }

            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
            }
        }
        return isok;
    }

    //压缩
    protected Bitmap SampledBitmap(String path, int width,
                                   int height, int angle) {
        // 获得图片的宽和高，并把图片加载到内存中
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        if (width < height) {
            options.inSampleSize = computeSampleSize(options,
                    width, height * width);
        } else {
            options.inSampleSize = computeSampleSize(options, height, width * height);
        }
//         使用获得到的InSampleSize再次解析图片
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        if (angle != 0) {
            int drawablewidth = bitmap.getWidth();
            int drawableheight = bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.setRotate(angle);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, drawablewidth, drawableheight,
                    matrix, true);
        }
        return bitmap;
    }

    public int computeSampleSize(BitmapFactory.Options options,
                                 int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private int computeInitialSampleSize(BitmapFactory.Options options,
                                         int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
                Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState())) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    private void refreashBitmap(String path, ImageView imageView, Bitmap bitmap) {
        Message message = Message.obtain();
        myImageBean holder = new myImageBean();
        holder.bitmap = bitmap;
        holder.path = path;
        holder.imageView = imageView;
        message.obj = holder;
        uiHandler.sendMessage(message);
    }

    public class myImageBean {
        Bitmap bitmap;
        ImageView imageView;
        String path;
    }

}
