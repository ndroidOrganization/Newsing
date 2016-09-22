package com.newsing.view.viewpager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Angel on 2016/9/14.
 */
public class SWViewPager extends ViewPager {
    private SWFragmentViewPagerAdapter swFragmentViewPagerAdapter;
    private boolean isscroll = true;
    private float endX;

    public SWViewPager(Context context) {
        super(context);
    }

    public SWViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setSWFragmentViewPagerAdapter(FragmentManager fragmentManager, Fragment... fragment) {
        if (swFragmentViewPagerAdapter == null) {
            swFragmentViewPagerAdapter = new SWFragmentViewPagerAdapter(fragmentManager);
        }
        swFragmentViewPagerAdapter.setFragmentList(fragment);
        if (getAdapter() != null) {
            swFragmentViewPagerAdapter.notifyDataSetChanged();
        } else {
            setAdapter(swFragmentViewPagerAdapter);
        }
    }


    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {

        if (v instanceof ViewGroup) {
            final ViewGroup group = (ViewGroup) v;
            final int scrollX = v.getScrollX();
            final int scrollY = v.getScrollY();
            final int count = group.getChildCount();
            for (int i = count - 1; i >= 0; i--) {
                final View child = group.getChildAt(i);
                if (x + scrollX >= child.getLeft() && x + scrollX < child.getRight() &&
                        y + scrollY >= child.getTop() && y + scrollY < child.getBottom() &&
                        canScroll(child, true, dx, x + scrollX - child.getLeft(),
                                y + scrollY - child.getTop())) {
                    return true;
                }
            }
        }

        if (checkV) {
            if (v instanceof ViewPager) {
                return v.canScrollHorizontally(-dx);
            } else {
                return ViewCompat.canScrollHorizontally(v, -dx);
            }
        } else {
            return false;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        final float x = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);
                isscroll = true;
                endX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                if (isscroll) {
                    if (x - endX > 5 && getCurrentItem() == 0 || x - endX < -5 && getCurrentItem() == getAdapter().getCount() - 1) {
                        isscroll = false;
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}
