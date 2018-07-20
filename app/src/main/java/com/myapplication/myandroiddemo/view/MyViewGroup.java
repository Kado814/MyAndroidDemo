package com.myapplication.myandroiddemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ${KZJ} on 2018/7/20.
 */

public class MyViewGroup extends ViewGroup {
    public MyViewGroup(Context context) {
        super(context);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //将所有的子view进行测量，会触发每个子view的onMeasure()函数
        //注意要与measureChild区分，measureChild是对单个子view进行测量
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int childCount = getChildCount();
        if (childCount == 0) {
            setMeasuredDimension(0, 0);
        } else {
            if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {//高宽都是包裹内容
                int width = getMaxChildWidth(childCount);
                int height = getTotalHeight(childCount);
                setMeasuredDimension(width, height);
            } else if (heightMode == MeasureSpec.AT_MOST) {//如果只有高度是包裹内容
                //宽度设置为ViewGrouo自己测量宽度，高度设置为所有子view的总和
                setMeasuredDimension(widthSize, getTotalHeight(childCount));

            } else if (widthMode == MeasureSpec.AT_MOST) {//如果只有宽度是包裹内容
                //宽度设置为子view中宽度最大值，高度设置为ViewGroup自己测量值
                setMeasuredDimension(getMaxChildWidth(childCount), heightSize);
            }
        }
    }

    /**
     * 将所有子view的高度相加
     *
     * @return
     */
    private int getTotalHeight(int childCount) {
        int height = 0;
        for (int i = 0; i < childCount; i++) {
            height += getChildAt(i).getMeasuredHeight();
        }
        return height;
    }

    private int getMaxChildWidth(int childCount) {
        int maxWidth = 0;
        for (int i = 0; i < childCount; i++) {
            if (getChildAt(i).getMeasuredWidth() > maxWidth) {
                maxWidth = getChildAt(i).getMeasuredWidth();
            }
        }
        return maxWidth;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        //记录当前的高度位置
        int curHeight = 0;
        //将view逐个摆放
        for (int k = 0; k < count; k++) {
            View child = getChildAt(k);
            int height = child.getMeasuredHeight();
            int width = child.getMeasuredWidth();
            //摆放子view,参数分别是子view矩形区域的左，上，右，下
            child.layout(l, curHeight, l + width, curHeight + height);
            curHeight += height;
        }
    }
}
