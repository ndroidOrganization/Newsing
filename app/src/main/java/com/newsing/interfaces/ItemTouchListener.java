package com.newsing.interfaces;

/**
 * Created by qzzhu on 16-11-7.
 */

public interface ItemTouchListener {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
