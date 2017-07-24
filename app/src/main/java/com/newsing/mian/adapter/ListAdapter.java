package com.newsing.mian.adapter;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newsing.NewingApplication;
import com.newsing.R;
import com.newsing.basic.BaseInterface;
import com.newsing.utils.Base64Util;
import com.newsing.utils.ConstValue;
import com.newsing.utils.FileManager;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/22 0022.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    private List<ConstValue.ALIAPIBEANDATAITEM> itemdatas = null;

    private Context context;

    public ListAdapter(ContextThemeWrapper contextThemeWrapper) {
        context = contextThemeWrapper;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(context).inflate(R.layout.news_list_template,parent,false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setItemData(itemdatas.get(position),clickListener);
    }

    public void swapDatas(List<ConstValue.ALIAPIBEANDATAITEM> datas){
        if(itemdatas == null)
            itemdatas = new ArrayList<>();
        itemdatas.clear();
        itemdatas.addAll(datas);
        notifyDataSetChanged();
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ConstValue.ALIAPIBEANDATAITEM data = (ConstValue.ALIAPIBEANDATAITEM) v.getTag();
            data.logStringValue();
        }
    };

    @Override
    public int getItemCount() {
        return itemdatas == null ? 0 : itemdatas.size();
    }

    public final static class ViewHolder extends RecyclerView.ViewHolder implements BaseInterface<File>{
        private View content;
        private TextView title,date;
        private ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_template_title);
            date = (TextView) itemView.findViewById(R.id.news_template_time);
            icon = (ImageView) itemView.findViewById(R.id.news_template_icon);
            content = itemView.findViewById(R.id.news_template_item);
        }

        public void setItemData(ConstValue.ALIAPIBEANDATAITEM itemData,View.OnClickListener clickListener){
            title.setText(itemData.getTitle());
            date.setText(itemData.getDate());
            final String imguri0 = itemData.getThumbnail_pic_s();
            String imgName = Base64Util.encode(imguri0);
            Bitmap thumb = FileManager.getInstance(NewingApplication.getInstance()).loadBitmap(-1,imgName);
            if(thumb!=null)
                icon.setImageBitmap(thumb);
            else{
                FileManager.getInstance(NewingApplication.getInstance())
                        .DownloadBitmap(imguri0,imgName,null,new WeakReference<>(NewingApplication.getInstance())
                            ,this,false,false);
            }
            content.setOnClickListener(clickListener);
            content.setTag(itemData);
        }

        @Override
        public void onComplete(File result) {
            Bitmap thumb = FileManager.getInstance(NewingApplication.getInstance()).loadBitmap(-1,result.getName());
            if(thumb!=null)
                icon.setImageBitmap(thumb);
        }

        @Override
        public void onError(@StringRes int resId) {

        }
    }
}
