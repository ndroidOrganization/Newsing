package com.newsing.fragment.beauty;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.newsing.BeautyItemBinding;
import com.newsing.NewingApplication;
import com.newsing.utils.FileManager;

/**
 * Created by qzzhu on 16-9-21.
 *
 */
class RecycleItemHolder extends RecyclerView.ViewHolder {
    private BeautyItemBinding itemBinding = null;
    private int width = 0;

    RecycleItemHolder(View itemView) {
        super(itemView);
        itemBinding = DataBindingUtil.bind(itemView);
        width = itemView.getResources().getDisplayMetrics().widthPixels/ BeautyFragment.COLUMNCOUNT;
    }

    void bindItem(ItemModel datasHolder, OnClickListener clickListener){
        if(datasHolder.getPicpath() != null){
            String filePath = datasHolder.getFilePath(NewingApplication.getInstance());

            Bitmap map = FileManager.getInstance(NewingApplication.getInstance()).loadBitmap(width,filePath);

            if(map != null)
            {
                //set layout params
                ViewGroup.LayoutParams layoutParams = itemBinding.getRoot().getLayoutParams();
                layoutParams.height = calculateItemViewRightHeight(width,map.getWidth(),map.getHeight());//map.getHeight();

                //set image
                itemBinding.beautyItem.setImageBitmap(map);

                //set click listener
                itemBinding.getRoot().setTag(datasHolder);
                itemBinding.getRoot().setOnClickListener(clickListener);
            }
            else{
                Log.d("map","decode = null");
            }
        }
    }

    private int calculateItemViewRightHeight(int viewWidth, int picWidth, int picHeight){
        return (int) (picHeight*viewWidth*1.0f/picWidth);
    }
}
