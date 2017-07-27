package com.newsing.db;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.newsing.activity.webpage.WebActivity;

import org.litepal.crud.DataSupport;

/**
 * Created by Qzhu on 2017/7/27.
 */
public class AliApiDataItem extends DataSupport implements Parcelable{

    public final static String WHERE = "date = ? and author_name = ? and url = ?";

    private String uniquekey;
    private String title;
    private String date;
    //        private String category;
    private String author_name;
    private String url;
    private String thumbnail_pic_s;
    private String thumbnail_pic_s02;
    private String thumbnail_pic_s03;

    public AliApiDataItem(){}

    private AliApiDataItem(Parcel in)
    {
        uniquekey = in.readString();
        title = in.readString();
        date = in.readString();
        author_name = in.readString();
        url = in.readString();
        thumbnail_pic_s = in.readString();
        thumbnail_pic_s02 = in.readString();
        thumbnail_pic_s03 = in.readString();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(uniquekey);
        dest.writeString(title);
        dest.writeString(date);
        dest.writeString(author_name);
        dest.writeString(url);
        dest.writeString(thumbnail_pic_s);
        dest.writeString(thumbnail_pic_s02);
        dest.writeString(thumbnail_pic_s03);
    }

    public String getUniquekey() {
        return uniquekey;
    }

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail_pic_s() {
        return thumbnail_pic_s;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s02() {
        return thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s03() {
        return thumbnail_pic_s03;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public void logStringValue(){
        Log.i("item","uri = "+url);
        Log.i("item","thumbnail_pic_s = "+thumbnail_pic_s);
        Log.i("item","thumbnail_pic_s02 = "+thumbnail_pic_s02);
        Log.i("item","thumbnail_pic_s03 = "+thumbnail_pic_s03);
    }

    public Bundle getBundle(){
        Bundle bundle = new Bundle();
        bundle.putParcelable(WebActivity.KEY_OF_TARGET_URI,this);
        return bundle;
    }

    public static final Parcelable.Creator<AliApiDataItem> CREATOR = new Parcelable.Creator<AliApiDataItem>()
    {
        public AliApiDataItem createFromParcel(Parcel in)
        {
            return new AliApiDataItem(in);
        }

        public AliApiDataItem[] newArray(int size)
        {
            return new AliApiDataItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }
}
