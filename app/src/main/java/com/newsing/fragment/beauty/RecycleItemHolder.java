package com.newsing.fragment.beauty;

import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.newsing.BeautyItemBinding;
import com.newsing.NewingApplication;
import com.newsing.utils.FileUtils;

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

    void bindItem(ItemModel datasHolder,FileUtils cachedMap){
        if(datasHolder.getPicpath() != null){
            String filePath = datasHolder.getFilePath(NewingApplication.getInstance());

            Log.i("filepath",":"+filePath);
            Bitmap map = cachedMap.loadBitmap(width,filePath);

            if(map != null)
            {
                //set layout params
                ViewGroup.LayoutParams layoutParams = itemBinding.getRoot().getLayoutParams();
                layoutParams.height = map.getHeight();

                //set image
                itemBinding.beautyItem.setImageBitmap(map);

                //set click listener
                itemBinding.getRoot().setTag(datasHolder);
                itemBinding.getRoot().setOnClickListener(clickListener);
            }
            else{
                Log.d("map","decode = null");
            }

            //palette
            //ColorUtils.getBitmapColor(itemBinding.getRoot().getContext(),listener);
        }
    }

    private final OnClickListener clickListener= new OnClickListener(){

        @Override
        public void onClick(View view) {
            try{
                ItemModel datasHolder = (ItemModel) view.getTag();
                if(datasHolder != null)
                {
                    //do something
                }
            }catch(ClassCastException e)
            {
                Log.e("RecycleItemHolder","class case error");
            }
        }
    };

    private final Palette.PaletteAsyncListener listener = new Palette.PaletteAsyncListener() {
        @Override
        public void onGenerated(Palette palette) {
            Palette.Swatch vibrant =palette.getMutedSwatch();
            if(vibrant != null)
                itemBinding.beautyCard.setBackgroundColor(vibrant.getRgb());
        }
    };
}
