package com.newsing.activity.trchat;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.newsing.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qzhu on 2017/7/28.
 */

public class TRRecyclerAdapter extends RecyclerView.Adapter<TRRecyclerAdapter.ViewHolder>{

    private List<TRRecyclerItemBean> datas = new ArrayList<>();
    private Context context;

    public TRRecyclerAdapter(Context context){
        datas.add(new TRRecyclerItemBean(false, context.getString(R.string.tr_prefix)));
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tuling_txt_template,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData(datas.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView content;

        public ViewHolder(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.tuling_txt_content);
        }

        public void setData(String content){
            this.content.setText(content);
        }
    }

    public void addData(boolean request, String content){
        datas.add(new TRRecyclerItemBean(request, content));
        notifyItemInserted(datas.size());
    }

    public int getTotal(){
        return datas.size();
    }
}
