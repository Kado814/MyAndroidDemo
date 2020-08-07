package com.myapplication.myandroiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ${KZJ} on 2018/7/20.
 * 正方形的形式显示，即要宽高相等，并且默认的宽高值为100像素
 */

public class MyView extends View {
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSize = getMySize(100, widthMeasureSpec);
        int heightSize = getMySize(100, heightMeasureSpec);

        if (widthSize > heightSize) {
            widthSize = heightSize;
        } else {
            heightSize = widthSize;
        }

        setMeasuredDimension(widthSize, heightSize
        );
    }


    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.UNSPECIFIED://如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST://如果测量模式是最大值为size
                mySize = size;
                break;
            case MeasureSpec.EXACTLY://如果是固定的大小，就不再改变
                mySize = size;
                break;
        }
        return mySize;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int radius = getMeasuredWidth() / 2;//获取圆的半径
        int x = getLeft() + radius;
        int y = getTop() + radius;

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(false);                       //设置画笔为无锯齿
        canvas.drawCircle(radius, radius, radius, paint);
    }
}
