package com.newsing.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v7.widget.*;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.newsing.interfaces.OnScrollListener;

/**
 * Created by Angel on 2016/7/27.
 */
public class SWRecyclerViewLayout extends LinearLayout implements NestedScrollingParent {

    private Context context = null;
    private static final String TAG = "SWRecyclerViewLayout";
    private NestedScrollingParentHelper helper = null;
    private boolean IsRefresh = true;
    private boolean IsLoad = true;
    //滑动的总距离
    private int totalY = 0;
    private LinearLayout headerLayout = null;
    private MyRecyclerView myRecyclerView = null;
    private LinearLayout footerLayout = null;
    private OnScrollListener onScrollListener = null;
    private boolean isfling = false;

    public SWRecyclerViewLayout(Context context) {
        super(context);
        this.context = context;
        inital();
    }

    public SWRecyclerViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inital();
    }

    private void inital() {
        helper = new NestedScrollingParentHelper(this);
        headerLayout = new LinearLayout(context);
        myRecyclerView = new MyRecyclerView(context);
        footerLayout = new LinearLayout(context);
        setOrientation(VERTICAL);
        headerLayout.setOrientation(VERTICAL);
        footerLayout.setOrientation(VERTICAL);
        addView(this.headerLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        addView(this.myRecyclerView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(this.footerLayout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    }

    public void setMyRecyclerView(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        myRecyclerView.setMyLayoutManager(layoutManager);
        myRecyclerView.setAdapter(adapter);
    }

    public void setMyRecyclerView(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter, boolean fixed) {
        myRecyclerView.setMyLayoutManager(layoutManager);
        myRecyclerView.setAdapter(adapter);
        myRecyclerView.setHasFixedSize(fixed);
    }

    public void addHeaderView(View headerView, int headerHeight) {
        this.headerLayout.removeAllViews();
        this.headerLayout.addView(headerView);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, headerHeight);
        layoutParams.topMargin = -headerHeight;
        this.headerLayout.setLayoutParams(layoutParams);
    }

    public void addFooterView(View footerView, int footerHeight) {
        this.footerLayout.removeAllViews();
        this.footerLayout.addView(footerView);
        this.footerLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, footerHeight));
    }

    public void setScrollTo(int fromY, int toY) {
        smoothScrollTo((float) fromY, (float) toY);
    }

    public void setItemDivider(RecyclerView.ItemDecoration itemDecoration) {
        myRecyclerView.addItemDecoration(itemDecoration);
    }

    public int getTotal() {
        return -totalY / 2;
    }


    public void setIsScrollLoad(boolean isScrollLoad) {
        myRecyclerView.isScrollLoad = isScrollLoad;
    }

    public boolean isScrollLoad() {
        return myRecyclerView.isScrollLoad;
    }

    public void setIsScrollRefresh(boolean isScrollRefresh) {
        myRecyclerView.isScrollRefresh = isScrollRefresh;
    }

    public boolean isScrollRefresh() {
        return myRecyclerView.isScrollRefresh;
    }

    public void setRecyclerViewScrollToPosition(int position) {
        myRecyclerView.scrollToPosition(position);
    }

    public void addScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    public void onNestedScrollAccepted(View child, View target, int axes) {
        helper.onNestedScrollAccepted(child, target, axes);
    }

    //父view拦截子view的滚动
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (totalY < 0 && myRecyclerView.isOrientation(0) || totalY > 0 && myRecyclerView.isOrientation(1)) {
            isfling = true;
        }
        if (IsRefresh) {
            if (dy > 0) {
                if (myRecyclerView.isOrientation(0)) {
                    totalY += dy;
                    if ((totalY / 2) <= 0) {
                        scrollTo(0, totalY / 2);
                        consumed[1] = dy;
                    } else {
                        scrollTo(0, 0);
                        consumed[1] = 0;
                    }
                }
                return;
            }
        }
        if (IsLoad) {
            if (dy < 0) {
                if (myRecyclerView.isOrientation(1)) {
                    totalY += dy;
                    if ((totalY / 2) >= 0) {
                        scrollTo(0, totalY / 2);
                        consumed[1] = dy;
                    } else {
                        scrollTo(0, 0);
                        consumed[1] = 0;
                    }
                }
                return;
            }
        }
    }

    //子view滑动结束调用
    //dyUnconsumed < 0 向下滚
    //dyUnconsumed > 0 向上滚
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        if (dyUnconsumed != 0) {
            totalY += dyUnconsumed;
            scrollTo(0, totalY / 2);
        }
    }

    public void onStopNestedScroll(View child) {
        helper.onStopNestedScroll(child);
        if (onScrollListener != null) {
            isfling = false;
            onScrollListener.touchUp();
        }
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return isfling;
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return isfling;
    }

    public int getNestedScrollAxes() {
        return helper.getNestedScrollAxes();
    }

    private void smoothScrollTo(float fromY, float toY) {
        final float distance = fromY - toY;
        ValueAnimator animator = ValueAnimator.ofFloat(new float[]{fromY, toY});
        if (fromY == toY) {
            animator.setDuration(0);
        } else {
            animator.setDuration(300);
        }
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                int to = (int) (-((Float) animation.getAnimatedValue()).floatValue());
                scrollTo(0, to);
                totalY = to * 2;
            }
        });
        animator.addListener(new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                if (onScrollListener != null) {
                    onScrollListener.animStop(distance);
                }

            }

            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }
        });
        animator.start();
    }

    private class MyRecyclerView extends RecyclerView {
        private StaggeredGridLayoutManager staggeredGridLayoutManager = null;
        private LinearLayoutManager linearLayoutManager = null;
        private GridLayoutManager gridLayoutManager = null;
        private boolean isScrollLoad = false;
        private boolean isScrollRefresh = false;

        public MyRecyclerView(Context context) {
            super(context);
            setVerticalFadingEdgeEnabled(false);
            setHorizontalFadingEdgeEnabled(false);
            setVerticalScrollBarEnabled(false);
            setHorizontalScrollBarEnabled(false);
            setOverScrollMode(OVER_SCROLL_NEVER);
            setItemAnimator(new DefaultItemAnimator());
        }

        private void setMyLayoutManager(LayoutManager layoutManager) {
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                staggeredGridLayoutManager = (StaggeredGridLayoutManager) layoutManager;
            } else if (layoutManager instanceof GridLayoutManager) {
                gridLayoutManager = (GridLayoutManager) layoutManager;
            } else if (layoutManager instanceof LinearLayoutManager) {
                linearLayoutManager = (LinearLayoutManager) layoutManager;
            }
            setLayoutManager(layoutManager);
            if (!isVertical()) {
                throw new NullPointerException("vertical!");
            }
        }

        private boolean isOrientation(int orientation) {//orientation,0代表向下，1代表向上
            if (orientation == 0)
                return isCanPullDown();
            else if (orientation == 1)
                return isCanPullUp();
            return false;
        }

        private boolean isCanPullDown() {
            return !canScrollVertically(-1);
        }

        private boolean isCanPullUp() {
            return !canScrollVertically(1);
        }

        private int scrollLoad() {
            int lastItem = 0;
            int itemCount = 0;
            int spanCount = 1;
            if (staggeredGridLayoutManager != null) {
                lastItem = staggeredGridLayoutManager.findLastVisibleItemPositions(null)[0];
                itemCount = staggeredGridLayoutManager.getItemCount();
                spanCount = staggeredGridLayoutManager.getSpanCount();
            } else if (linearLayoutManager != null) {
                lastItem = linearLayoutManager.findLastVisibleItemPosition();
                itemCount = linearLayoutManager.getItemCount();
                spanCount = 1;
            } else if (gridLayoutManager != null) {
                lastItem = gridLayoutManager.findLastVisibleItemPosition();
                itemCount = gridLayoutManager.getItemCount();
                spanCount = gridLayoutManager.getSpanCount();
            }
            return ((itemCount - 1) / spanCount + 1) - (lastItem / spanCount + 1);
        }

        private boolean isVertical() {
            if (staggeredGridLayoutManager != null)
                return staggeredGridLayoutManager.getOrientation() == StaggeredGridLayoutManager.VERTICAL;
            else if (linearLayoutManager != null)
                return linearLayoutManager.getOrientation() == LinearLayoutManager.VERTICAL;
            else if (gridLayoutManager != null)
                return gridLayoutManager.getOrientation() == GridLayoutManager.VERTICAL;
            return false;
        }

//        public void onScrolled(int dx, int dy) {
//            if (dy > 0 && !isScrollLoad) {
//                if (onScrollListener != null) {
//                    onScrollListener.scrollLoad(scrollLoad());//传递滚动到倒数第几行
//                }
//            }
//        }
    }

}