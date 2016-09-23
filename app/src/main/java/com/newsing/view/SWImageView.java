package com.newsing.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;
import com.newsing.R;

public class SWImageView extends ImageView {


    private int type;
    private static final int TYPE_NORMAL = -1;
    private static final int TYPE_CIRCLE = 0;
    private static final int TYPE_ROUND = 1;
    private Paint paint;
    private Matrix matrix;
    private static final int BODER_RADIUS_DEFAULT = 10;
    private int borderRadius;
    private int width;
    private int radius;
    private BitmapShader bitmapShader;
    private RectF rectF;
    private Context context;

    public SWImageView(Context context) {
        super(context);
    }

    public SWImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        inital(context, attrs);
    }

    private void inital(Context context, AttributeSet attrs) {
        matrix = new Matrix();
        paint = new Paint();
        paint.setAntiAlias(true);
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.SWImageView);
        borderRadius = dp2px(BODER_RADIUS_DEFAULT);
        type = array.getInt(R.styleable.SWImageView_type, TYPE_NORMAL);
        array.recycle();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (type == TYPE_CIRCLE) {
            width = Math.min(getMeasuredWidth(), getMeasuredHeight());
            radius = width / 2;
            setMeasuredDimension(width, width);
        }
    }

    private void setBitmapShader() {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        if (bitmap == null) {
            return;
        }
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;
        int viewwidth = getWidth();
        int viewheight = getHeight();
        int drawablewidth = bitmap.getWidth();
        int drawableheight = bitmap.getHeight();
        float dx = 0, dy = 0;
        if (type == TYPE_CIRCLE) {
            int size = Math.min(bitmap.getWidth(), bitmap.getHeight());
            scale = width * 1.0f / size;
        } else if (type == TYPE_ROUND) {
            scale = Math.max(getWidth() * 1.0f / bitmap.getWidth(), getHeight()
                    * 1.0f / bitmap.getHeight());
        } else {
            return;
        }
        if (drawablewidth * viewheight > viewwidth * drawableheight) {
            dx = (viewwidth - drawablewidth * scale) * 0.5f;
        } else {
            dy = (viewheight - drawableheight * scale) * 0.5f;
        }
        matrix.setScale(scale, scale);
        matrix.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
    }

    protected void onDraw(Canvas canvas) {
        if (getDrawable() == null) {
            return;
        }
        setBitmapShader();
        if (type == TYPE_ROUND) {
            canvas.drawRoundRect(rectF, borderRadius, borderRadius,
                    paint);
        } else if (type == TYPE_CIRCLE) {
            canvas.drawCircle(radius, radius, radius, paint);
        } else {
            getDrawable().draw(canvas);
        }
    }

    public void setBorderRadius(int borderRadius) {
        int px = dp2px(borderRadius);
        if (this.borderRadius != px) {
            this.borderRadius = px;
            invalidate();
        }
    }

    public void setType(int type) {
        if (this.type != type) {
            this.type = type;
            if (this.type != TYPE_ROUND && this.type != TYPE_CIRCLE) {
                this.type = TYPE_CIRCLE;
            }
            requestLayout();
        }

    }

    public int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, getResources().getDisplayMetrics());
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (type == TYPE_ROUND)
            rectF = new RectF(0, 0, getWidth(), getHeight());
    }
}
