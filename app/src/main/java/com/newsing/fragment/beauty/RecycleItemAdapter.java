package com.newsing.fragment.beauty;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newsing.R;
import com.newsing.fragment.beauty.activity.BeautyDetailActivity;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by qzzhu on 16-9-21.
 *
 */
class RecycleItemAdapter extends RecyclerView.Adapter<RecycleItemHolder>{
    private List<ItemModel> datats = null;
    private WeakReference<FragmentActivity> activity;

    RecycleItemAdapter(List<ItemModel> datats, WeakReference<FragmentActivity> activity) {
        this.datats = datats;
        this.activity = activity;
    }

    @Override
    public RecycleItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.frag_beauty_item,parent,false);
        return new RecycleItemHolder(item);
    }

    @Override
    public void onBindViewHolder(RecycleItemHolder holder, int position) {
        holder.bindItem(datats.get(position),clickListener);
    }

    private final View.OnClickListener clickListener= new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            try{
                ItemModel datasHolder = (ItemModel) view.getTag();
                if(datasHolder != null)
                {
                    //do something
                    startBeautyActivity(datasHolder.getPicpath(),view.findViewById(R.id.beauty_item));
                }
            }catch(ClassCastException e)
            {
                Log.e("RecycleItemHolder","class case error");
            }
        }
    };

    private void startBeautyActivity(String filepath,View... view){
        FragmentActivity content = activity.get();
        if(content != null)
        {
            String transName = content.getString(R.string.beauty_item_shared);
            Intent i = new Intent(activity.get(),BeautyDetailActivity.class);
            i.putExtra(BeautyDetailActivity.IMAGEPATH,filepath);
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(content,view[0],transName);
            ActivityCompat.startActivity(content,i,optionsCompat.toBundle());
        }

    }

    @Override
    public int getItemCount() {
        return datats.size();
    }
}
