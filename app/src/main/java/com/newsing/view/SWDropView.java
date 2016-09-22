package com.newsing.view;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Angel on 2016/8/12.
 */
public class SWDropView extends View {

    private static final String TAG = "SWDropView";
    private Path path;
    private Paint paint;
    private float radius = 0;
    private float cx = 0;
    private float cy = 0;
    private float angle1 = 30;
    private float angle2 = 150;
    private float angle3 = 270;
    private ArrayList<Circle> positive = new ArrayList<>();
    private ArrayList<Circle> offSetpoint1 = new ArrayList<>();
    private ArrayList<Circle> offSetpoint2 = new ArrayList<>();
    private ArrayList<Circle> offSetpoint3 = new ArrayList<>();
    private float angle4 = 210;
    private float angle5 = 330;
    private float angle6 = 90;
    private ArrayList<Circle> inverse = new ArrayList<>();
    private ArrayList<Circle> offSetpoint4 = new ArrayList<>();
    private ArrayList<Circle> offSetpoint5 = new ArrayList<>();
    private ArrayList<Circle> offSetpoint6 = new ArrayList<>();
    private int speed = 0;
    private int count1 = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int rectf1 = 0;
    private int rotate = 0;
    private boolean IsRefresh = false;

    public SWDropView(Context context) {
        super(context);
        inital();
    }

    public SWDropView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inital();
    }

    public void setRefresh(boolean refresh) {
        this.IsRefresh = refresh;
        if (!refresh) {
            count1 = 0;
            count2 = 0;
            count3 = 0;
            rectf1 = 0;
            rotate = 0;
            invalidate();
        }
    }

    private void inital() {
        path = new Path();
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.RED);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        DrawTriangle(canvas);
        invalidate();
    }

    private void DrawTriangle(Canvas canvas) {
        path.reset();
        if (Math.pow(offSetpoint1.get(count1).circleX - cx, 2) + Math.pow(offSetpoint1.get(count1).circleY - cy, 2) > radius * radius) {
            offSetpoint1.get(count1).circleX = positive.get(1).circleX;
        }
        if (Math.pow(offSetpoint4.get(count1).circleX - cx, 2) + Math.pow(offSetpoint4.get(count1).circleY - cy, 2) > radius * radius) {
            offSetpoint4.get(count1).circleX = inverse.get(1).circleX;
        }
        if (Math.pow(offSetpoint2.get(count2).circleX - cx, 2) + Math.pow(offSetpoint2.get(count2).circleY - cy, 2) > radius * radius) {
            offSetpoint2.get(count2).circleX = positive.get(2).circleX;
        }
        if (Math.pow(offSetpoint5.get(count2).circleX - cx, 2) + Math.pow(offSetpoint5.get(count2).circleY - cy, 2) > radius * radius) {
            offSetpoint5.get(count2).circleX = inverse.get(2).circleX;
        }
        if (Math.pow(offSetpoint3.get(count3).circleX - cx, 2) + Math.pow(offSetpoint3.get(count3).circleY - cy, 2) > radius * radius) {
            offSetpoint3.get(count3).circleX = positive.get(0).circleX;
        }
        if (Math.pow(offSetpoint6.get(count3).circleX - cx, 2) + Math.pow(offSetpoint6.get(count3).circleY - cy, 2) > radius * radius) {
            offSetpoint6.get(count3).circleX = inverse.get(0).circleX;
        }
        if (IsRefresh) {
            if (rotate >= 360) {
                rotate = 0;
            }
            canvas.drawLine(cos(angle1 + rotate), sin(angle1 + rotate), cos(angle2 + rotate), sin(angle2 + rotate), paint);
            canvas.drawLine(cos(angle2 + rotate), sin(angle2 + rotate), cos(angle3 + rotate), sin(angle3 + rotate), paint);
            canvas.drawLine(cos(angle3 + rotate), sin(angle3 + rotate), cos(angle1 + rotate), sin(angle1 + rotate), paint);
            canvas.drawLine(cos(angle4 + rotate), sin(angle4 + rotate), cos(angle5 + rotate), sin(angle5 + rotate), paint);
            canvas.drawLine(cos(angle5 + rotate), sin(angle5 + rotate), cos(angle6 + rotate), sin(angle6 + rotate), paint);
            canvas.drawLine(cos(angle6 + rotate), sin(angle6 + rotate), cos(angle4 + rotate), sin(angle4 + rotate), paint);
            canvas.drawCircle(cx, cy, radius, paint);
            rotate += 5;
        } else if (offSetpoint1.get(count1).circleX != positive.get(1).circleX) {
            canvas.drawLine(positive.get(0).circleX, positive.get(0).circleY, offSetpoint1.get(count1).circleX, offSetpoint1.get(count1).circleY, paint);
            canvas.drawLine(inverse.get(0).circleX, inverse.get(0).circleY, offSetpoint4.get(count1).circleX, offSetpoint4.get(count1).circleY, paint);
            count1++;
        } else if (offSetpoint1.get(count1).circleX == positive.get(1).circleX && offSetpoint2.get(count2).circleX != positive.get(2).circleX) {
            canvas.drawLine(positive.get(0).circleX, positive.get(0).circleY, positive.get(1).circleX, positive.get(1).circleY, paint);
            canvas.drawLine(positive.get(1).circleX, positive.get(1).circleY, offSetpoint2.get(count2).circleX, offSetpoint2.get(count2).circleY, paint);
            canvas.drawLine(inverse.get(0).circleX, inverse.get(0).circleY, inverse.get(1).circleX, inverse.get(1).circleY, paint);
            canvas.drawLine(inverse.get(1).circleX, inverse.get(1).circleY, offSetpoint5.get(count2).circleX, offSetpoint5.get(count2).circleY, paint);
            count2++;
        } else if (offSetpoint2.get(count2).circleX == positive.get(2).circleX && offSetpoint3.get(count3).circleX != positive.get(0).circleX) {
            canvas.drawLine(positive.get(0).circleX, positive.get(0).circleY, positive.get(1).circleX, positive.get(1).circleY, paint);
            canvas.drawLine(positive.get(1).circleX, positive.get(1).circleY, positive.get(2).circleX, positive.get(2).circleY, paint);
            canvas.drawLine(positive.get(2).circleX, positive.get(2).circleY, offSetpoint3.get(count3).circleX, offSetpoint3.get(count3).circleY, paint);
            canvas.drawLine(inverse.get(0).circleX, inverse.get(0).circleY, inverse.get(1).circleX, inverse.get(1).circleY, paint);
            canvas.drawLine(inverse.get(1).circleX, inverse.get(1).circleY, inverse.get(2).circleX, inverse.get(2).circleY, paint);
            canvas.drawLine(inverse.get(2).circleX, inverse.get(2).circleY, offSetpoint6.get(count3).circleX, offSetpoint6.get(count3).circleY, paint);
            count3++;
        } else if (rectf1 < 180) {
            canvas.drawLine(positive.get(0).circleX, positive.get(0).circleY, positive.get(1).circleX, positive.get(1).circleY, paint);
            canvas.drawLine(positive.get(1).circleX, positive.get(1).circleY, positive.get(2).circleX, positive.get(2).circleY, paint);
            canvas.drawLine(positive.get(2).circleX, positive.get(2).circleY, positive.get(0).circleX, positive.get(0).circleY, paint);
            canvas.drawLine(inverse.get(0).circleX, inverse.get(0).circleY, inverse.get(1).circleX, inverse.get(1).circleY, paint);
            canvas.drawLine(inverse.get(1).circleX, inverse.get(1).circleY, inverse.get(2).circleX, inverse.get(2).circleY, paint);
            canvas.drawLine(inverse.get(2).circleX, inverse.get(2).circleY, inverse.get(0).circleX, inverse.get(0).circleY, paint);
            RectF rectf = new RectF(cx - radius, cy - radius, cx + radius, cy + radius);
            canvas.drawArc(rectf, 30, rectf1, false, paint);
            canvas.drawArc(rectf, 210, rectf1, false, paint);
            rectf1++;
        } else {
            canvas.drawLine(cos(angle1), sin(angle1), cos(angle2), sin(angle2), paint);
            canvas.drawLine(cos(angle2), sin(angle2), cos(angle3), sin(angle3), paint);
            canvas.drawLine(cos(angle3), sin(angle3), cos(angle1), sin(angle1), paint);
            canvas.drawLine(cos(angle4), sin(angle4), cos(angle5), sin(angle5), paint);
            canvas.drawLine(cos(angle5), sin(angle5), cos(angle6), sin(angle6), paint);
            canvas.drawLine(cos(angle6), sin(angle6), cos(angle4), sin(angle4), paint);
            canvas.drawCircle(cx, cy, radius, paint);
        }
    }

    private float cos(float angle) {
        return (float) (cx + radius * Math.cos(angle * Math.PI / 180));
    }

    private float sin(float angle) {
        return (float) (cy + radius * Math.sin(angle * Math.PI / 180));
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = widthMeasureSpec - MeasureSpec.getMode(widthMeasureSpec);
        int height = heightMeasureSpec - MeasureSpec.getMode(heightMeasureSpec);
        setMeasuredDimension(width, height);
        cx = width / 2;
        cy = height / 2;
        radius = height / 4; //放入正三角形圆心
        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        Circle circle3 = new Circle();
        circle1.circleX = cos(angle1);
        circle1.circleY = sin(angle1);
        circle2.circleX = cos(angle2);
        circle2.circleY = sin(angle2);
        circle3.circleX = cos(angle3);
        circle3.circleY = sin(angle3);
        positive.add(circle1);
        positive.add(circle2);
        positive.add(circle3);
        //放入逆三角形圆心
        Circle circle4 = new Circle();
        Circle circle5 = new Circle();
        Circle circle6 = new Circle();
        circle4.circleX = cos(angle4);
        circle4.circleY = sin(angle4);
        circle5.circleX = cos(angle5);
        circle5.circleY = sin(angle5);
        circle6.circleX = cos(angle6);
        circle6.circleY = sin(angle6);
        inverse.add(circle4);
        inverse.add(circle5);
        inverse.add(circle6);
        //计算绘制速度
        speed = (int) Math.ceil(positive.get(0).circleX - positive.get(2).circleX);
//        speed = speed / 5;
        //计算三角形所有点的坐标
        for (int i = 0; i < speed; i++) {
            Circle circle = new Circle();
            circle.circleX = positive.get(0).circleX * (speed - i - 1) / speed + positive.get(1).circleX * (i + 1) / speed;
            circle.circleY = positive.get(0).circleY * (speed - i - 1) / speed + positive.get(1).circleY * (i + 1) / speed;
            offSetpoint1.add(circle);
        }
        for (int i = 0; i < speed; i++) {
            Circle circle = new Circle();
            circle.circleX = positive.get(1).circleX * (speed - i - 1) / speed + positive.get(2).circleX * (i + 1) / speed;
            circle.circleY = positive.get(1).circleY * (speed - i - 1) / speed + positive.get(2).circleY * (i + 1) / speed;
            offSetpoint2.add(circle);
        }
        for (int i = 0; i < speed; i++) {
            Circle circle = new Circle();
            circle.circleX = positive.get(2).circleX * (speed - i - 1) / speed + positive.get(0).circleX * (i + 1) / speed;
            circle.circleY = positive.get(2).circleY * (speed - i - 1) / speed + positive.get(0).circleY * (i + 1) / speed;
            offSetpoint3.add(circle);
        }
        for (int i = 0; i < speed; i++) {
            Circle circle = new Circle();
            circle.circleX = inverse.get(0).circleX * (speed - i - 1) / speed + inverse.get(1).circleX * (i + 1) / speed;
            circle.circleY = inverse.get(0).circleY * (speed - i - 1) / speed + inverse.get(1).circleY * (i + 1) / speed;
            offSetpoint4.add(circle);
        }
        for (int i = 0; i < speed; i++) {
            Circle circle = new Circle();
            circle.circleX = inverse.get(1).circleX * (speed - i - 1) / speed + inverse.get(2).circleX * (i + 1) / speed;
            circle.circleY = inverse.get(1).circleY * (speed - i - 1) / speed + inverse.get(2).circleY * (i + 1) / speed;
            offSetpoint5.add(circle);
        }
        for (int i = 0; i < speed; i++) {
            Circle circle = new Circle();
            circle.circleX = inverse.get(2).circleX * (speed - i - 1) / speed + inverse.get(0).circleX * (i + 1) / speed;
            circle.circleY = inverse.get(2).circleY * (speed - i - 1) / speed + inverse.get(0).circleY * (i + 1) / speed;
            offSetpoint6.add(circle);
        }
    }

    private class Circle {
        float circleX;
        float circleY;
    }
}
