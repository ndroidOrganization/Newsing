package com.newsing.fragment.topnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.newsing.R;

/**
 * Created by Angel on 2016/9/22.
 */
public class NewsAdapter extends RecyclerView.Adapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] data = new String[100];

    public NewsAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        for (int i = 0; i < 100; i++) {
            data[i] = (i + 1) + "";
        }

    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        return new MyHolder(inflater.inflate(R.layout.viewpager_news, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((MyHolder) viewHolder).textView.setText(data[position]);
    }

    public int getItemCount() {
        return data.length;
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public MyHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.text);
        }
    }
}
