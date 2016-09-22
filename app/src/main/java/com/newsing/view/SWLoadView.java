package com.newsing.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Angel on 2016/9/5.
 */
public class SWLoadView extends View {

    private Paint whitePaint;
    private Paint blackPaint;
    private float angle = 0;
    private int width;
    private int height;
    private int bitmapwidth;
    private int bitmapheight;
    private boolean load;

    public SWLoadView(Context context) {
        super(context);
        inital();
    }

    public SWLoadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inital();
    }

    private void inital() {
        whitePaint = new Paint();
        whitePaint.setAntiAlias(true);
        whitePaint.setColor(Color.WHITE);
        blackPaint = new Paint();
        blackPaint.setAntiAlias(true);
        blackPaint.setColor(Color.BLACK);
    }

    public void setLoad(boolean load) {
        this.load = load;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Point centerPoint = new Point(width / 2, height / 2);
        canvas.translate(centerPoint.x, centerPoint.y);
        canvas.rotate(angle);
        //绘制两个半圆
        int radius = Math.min(bitmapwidth, bitmapheight) / 2;
        RectF rect = new RectF(-radius, -radius, radius, radius);   //绘制区域
        canvas.drawArc(rect, 90, 180, true, blackPaint);            //绘制黑色半圆
        canvas.drawArc(rect, -90, 180, true, whitePaint);           //绘制白色半圆
        //绘制两个小圆
        int smallRadius = radius / 2;
        canvas.drawCircle(0, -smallRadius, smallRadius, blackPaint);
        canvas.drawCircle(0, smallRadius, smallRadius, whitePaint);
        //绘制鱼眼
        canvas.drawCircle(0, -smallRadius, smallRadius / 4, whitePaint);
        canvas.drawCircle(0, smallRadius, smallRadius / 4, blackPaint);
        if (load) {
            angle += 10;
            if (angle >= 360) {
                angle = 0;
            }
        }
        invalidate();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = widthMeasureSpec - MeasureSpec.getMode(widthMeasureSpec);
        height = heightMeasureSpec - MeasureSpec.getMode(heightMeasureSpec);
        setMeasuredDimension(width, height);
        bitmapheight = height / 2;
        bitmapwidth = bitmapheight;
    }
}