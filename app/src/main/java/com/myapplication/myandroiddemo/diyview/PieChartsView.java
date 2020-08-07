package com.myapplication.myandroiddemo.diyview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

/**
 * Created by ${KZJ} on 2019/1/11.
 * 自定义饼状图
 */
public class PieChartsView extends View {
    private List<PieData> dataList;
    private Paint paint = new Paint();
    private int width, height;
    private int textSize = 20;

    public PieChartsView(Context context) {
        super(context);
    }

    public PieChartsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDataList(List<PieData> dataList) {
        this.dataList = dataList;
        init();
        invalidate();
    }

    private void init() {
        float valueSum = 0;
        if (dataList != null && dataList.size() > 0) {
            for (PieData pieData : dataList) {
                valueSum += pieData.getValue();
            }

            for (PieData pieData : dataList) {
                pieData.setPercent(pieData.getValue() / valueSum);
                pieData.setAngle(360 * pieData.getPercent());
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dataList != null) {
            float currentStartAngle = 0;
            float minLength = Math.min(width, height) / 2;
            float r = (float) (minLength * 0.8);
            canvas.translate(width / 2, height / 2);
            RectF rectF = new RectF(-r, -r, r, r);
            for (PieData pieData : dataList) {
                paint.setColor(pieData.getColor());
                paint.setAntiAlias(true);
                canvas.drawArc(rectF, currentStartAngle, pieData.getAngle(), true, paint);
                currentStartAngle += pieData.getAngle();
            }

            for (int i = 0; i < dataList.size(); i++) {
                paint.setColor(dataList.get(i).getColor());
                paint.setTextSize(textSize);
                canvas.drawText(dataList.get(i).getName(), -minLength, textSize - minLength + textSize * i, paint);
            }
        }
    }


}
