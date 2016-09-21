package com.newsing.fragment.beauty;

import android.databinding.DataBindingUtil;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.newsing.BeautyItemBinding;
import com.newsing.R;
import com.newsing.utils.ColorUtils;

/**
 * Created by qzzhu on 16-9-21.
 *
 */
class RecycleItemHolder extends RecyclerView.ViewHolder {
    private BeautyItemBinding itemBinding = null;

    RecycleItemHolder(View itemView) {
        super(itemView);
        itemBinding = DataBindingUtil.bind(itemView);
    }

    void bindItem(ItemModel datasHolder){
        if(datasHolder.getPic_path() == null){
            ViewGroup.LayoutParams layoutParams = itemBinding.getRoot().getLayoutParams();
            layoutParams.height = (int) (200+Math.random()*200);
            itemBinding.beautyItem.setImageResource(R.drawable.icon_test);
            itemBinding.getRoot().setTag(datasHolder);
            itemBinding.getRoot().setOnClickListener(clickListener);
            ColorUtils.getBitmapColor(itemBinding.getRoot().getContext(),listener);
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
                itemBinding.beautyCard.setCardBackgroundColor(vibrant.getRgb());
        }
    };
}
