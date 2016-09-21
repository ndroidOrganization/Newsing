package com.newsing.fragment.beauty;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsing.R;

import java.util.List;

/**
 * Created by qzzhu on 16-9-21.
 *
 */
class RecycleItemAdapter extends RecyclerView.Adapter<RecycleItemHolder>{
    private List<ItemModel> datats = null;

    RecycleItemAdapter(List<ItemModel> datats) {
        this.datats = datats;
    }

    @Override
    public RecycleItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_beauty_item,parent,false);
        return new RecycleItemHolder(item);
    }

    @Override
    public void onBindViewHolder(RecycleItemHolder holder, int position) {
        holder.bindItem(datats.get(position));
    }

    @Override
    public int getItemCount() {
        return datats.size();
    }
}
