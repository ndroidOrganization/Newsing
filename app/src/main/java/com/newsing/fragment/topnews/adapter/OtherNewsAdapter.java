package com.newsing.fragment.topnews.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.newsing.R;
import com.newsing.fragment.topnews.http.OtherBean;
import com.newsing.fragment.topnews.http.top.TopBean;
import com.newsing.view.SWImageView;
import com.newsing.view.jack_ma.Jack_Ma;

import java.util.ArrayList;

/**
 * Created by Angel on 2016/9/23.
 */
public class OtherNewsAdapter extends RecyclerView.Adapter {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<OtherBean> list = new ArrayList<>();
    private Jack_Ma jack_ma;

    public OtherNewsAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    public void setList(ArrayList<OtherBean> list) {
        this.list = list;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new MyHolder(inflater.inflate(R.layout.other_recycler, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MyHolder holder=(MyHolder)viewHolder;
        if (list.size() != 0) {
            holder.title.setText(list.get(0).getResult().getData().get(position).getTitle());
            holder.author_name.setText(list.get(0).getResult().getData().get(position).getAuthor_name());
            holder.date.setText(list.get(0).getResult().getData().get(position).getDate());
            String path1=list.get(0).getResult().getData().get(position).getThumbnail_pic_s();
            String path2=list.get(0).getResult().getData().get(position).getThumbnail_pic_s02();
            String path3=list.get(0).getResult().getData().get(position).getThumbnail_pic_s03();
            if(path1!=null){
                jack_ma.with(context).loadImage(path1,holder.imageView,true,0);
            }
            if(path1==null&&path2!=null){
                jack_ma.with(context).loadImage(path2,holder.imageView,true,0);
            }
            if(path1==null&&path2==null&&path3!=null){
                jack_ma.with(context).loadImage(path3,holder.imageView,true,0);
            }
        }
    }

    public int getItemCount() {
        if(list.size()==0) {
            return list.size();
        }else {
            return list.get(0).getResult().getData().size();
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private SWImageView imageView;
        private TextView title;
        private TextView author_name;
        private TextView date;
        private CardView layout;

        public MyHolder(View view) {
            super(view);
            imageView = (SWImageView) view.findViewById(R.id.swimage);
            title = (TextView) view.findViewById(R.id.title);
            author_name = (TextView) view.findViewById(R.id.author_name);
            date = (TextView) view.findViewById(R.id.date);
            layout = (CardView) view.findViewById(R.id.layout);
        }
    }
}