package com.myapplication.myandroiddemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.myutils.MyLog;

/**
 * @Author: KKK
 * @CreateDate: 2020/8/20 5:16 PM
 */
public class ClassHourProgress extends View {
    private float percent = 0.5f;
    private int width;
    private int height;

    public ClassHourProgress(Context context) {
        this(context, null, 0);
    }

    public ClassHourProgress(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassHourProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClassHourProgress);
        percent = typedArray.getFloat(R.styleable.ClassHourProgress_persent, 0);
    }

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = widthMeasureSpec;
        height = heightMeasureSpec;
        MyLog.d("onMeasure-----width/height=" + widthMeasureSpec + "/" + heightMeasureSpec);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRect(canvas);
    }

    private void drawRect(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        MyLog.d("canvasWidth/canvasHeight=" + canvasWidth + "/" + canvasHeight);
        Paint p = new Paint();
        p.setColor(Color.parseColor("#F0ECFE"));
        p.setStyle(Paint.Style.FILL);//充满
        p.setAntiAlias(true);// 设置画笔的锯齿效果

        if (percent == 0) {
            p.setColor(Color.parseColor("#6A48F5"));
            Path path2 = new Path();
            path2.moveTo(0, 0);
            path2.lineTo(canvasWidth, 0);
            path2.lineTo(canvasWidth, canvasHeight);
            path2.lineTo(0, canvasHeight);
            path2.lineTo(0, 0);
            path2.close();
            canvas.drawPath(path2, p);
        } else if (percent == 1) {
            Path path1 = new Path();
            path1.moveTo(0, 0);
            path1.lineTo(canvasWidth, 0);
            path1.lineTo(canvasWidth, canvasHeight);
            path1.lineTo(0, canvasHeight);
            path1.lineTo(0, 0);
            path1.close();
            canvas.drawPath(path1, p);
        } else {
            Path path1 = new Path();
            path1.moveTo(0, 0);
            path1.lineTo(canvasWidth * percent, 0);
            path1.lineTo(canvasWidth * percent - canvasHeight / 2, canvasHeight);
            path1.lineTo(0, canvasHeight);
            path1.lineTo(0, 0);
            path1.close();
            canvas.drawPath(path1, p);

            p.setColor(Color.parseColor("#6A48F5"));
            Path path2 = new Path();
            path2.moveTo(canvasWidth * percent + 20, 0);
            path2.lineTo(canvasWidth, 0);
            path2.lineTo(canvasWidth, canvasHeight);
            path2.lineTo(canvasWidth * percent - canvasHeight / 2 + 20, canvasHeight);
            path2.lineTo(canvasWidth * percent + 20, 0);
            path2.close();
            canvas.drawPath(path2, p);
        }

    }
}
