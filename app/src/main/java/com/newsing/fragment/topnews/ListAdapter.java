package com.newsing.fragment.topnews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.newsing.R;
import com.newsing.utils.ConstValue;

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
        holder.setItemData(itemdatas.get(position));
    }

    public void swapDatas(List<ConstValue.ALIAPIBEANDATAITEM> datas){
        if(itemdatas == null)
            itemdatas = new ArrayList<>();
        itemdatas.clear();
        itemdatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemdatas == null ? 0 : itemdatas.size();
    }

    public final static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,date;
        private ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_template_title);
            date = (TextView) itemView.findViewById(R.id.news_template_time);
            icon = (ImageView) itemView.findViewById(R.id.news_template_icon);
        }

        public void setItemData(ConstValue.ALIAPIBEANDATAITEM itemData){
            title.setText(itemData.getTitle());
            date.setText(itemData.getDate());
        }

    }
}
