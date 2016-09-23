package com.newsing.fragment.topnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.newsing.R;

/**
 * Created by Angel on 2016/9/23.
 */
public class TopNewsAdapter extends RecyclerView.Adapter {

    private Context context;
    private LayoutInflater inflater;

    public TopNewsAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);

    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new MyHolder(inflater.inflate(R.layout.top_recycler, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
    }

    public int getItemCount() {
        return 10;
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        public MyHolder(View view) {
            super(view);
        }
    }
}
