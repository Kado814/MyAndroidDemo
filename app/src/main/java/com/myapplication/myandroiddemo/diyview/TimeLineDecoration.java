package com.myapplication.myandroiddemo.diyview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ${KZJ} on 2019/1/18.
 * 时自定义间线分割线
 */
public class TimeLineDecoration extends RecyclerView.ItemDecoration {

    private ArrayList<HashMap<String, Object>> list;
    // 写右边字的画笔(具体信息)
    private Paint mPaint;

    // 写左边日期字的画笔( 时间 + 日期)
    private Paint mPaint1;
    private Paint mPaint2;

    // 左 上偏移长度
    private int itemView_leftinterval;
    private int itemView_topinterval;

    // 轴点半径
    private int circle_radius;

    // 图标
    private Bitmap mIcon;


    // 在构造函数里进行绘制的初始化，如画笔属性设置等
    public TimeLineDecoration(ArrayList<HashMap<String, Object>> listItem) {
        list = listItem;
        // 轴点画笔(红色)
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setAntiAlias(true);

        // 左边时间文本画笔(蓝色)
        // 此处设置了两只分别设置 时分 & 年月
        mPaint1 = new Paint();
        mPaint1.setColor(Color.BLUE);
        mPaint1.setTextSize(40);
        mPaint1.setAntiAlias(true);

        mPaint2 = new Paint();
        mPaint2.setColor(Color.BLUE);
        mPaint1.setTextSize(30);
        mPaint2.setAntiAlias(true);


        // 赋值ItemView的左偏移长度为200
        itemView_leftinterval = 200;

        // 赋值ItemView的上偏移长度为50
        itemView_topinterval = 50;

        // 赋值轴点圆的半径为10
        circle_radius = 10;

        // 获取图标资源
        // mIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo);

    }

    // 重写getItemOffsets（）方法
    // 作用：设置ItemView 左 & 上偏移长度
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        // 设置ItemView的左 & 上偏移长度分别为200 px & 50px,即此为onDraw()可绘制的区域
        outRect.set(itemView_leftinterval, itemView_topinterval, 0, 0);

    }

    // 重写onDraw（）
    // 作用:在间隔区域里绘制时光轴线 & 时间文本
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        // 获取RecyclerView的Child view的个数
        int childCount = parent.getChildCount();

        // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
        for (int i = 0; i < childCount; i++) {

            // 获取每个Item对象
            View child = parent.getChildAt(i);

            /**
             * 绘制轴点
             */
            // 轴点 = 圆 = 圆心(x,y)
            float centerx = child.getLeft() - itemView_leftinterval / 3;
            float centery = child.getTop() - itemView_topinterval + (itemView_topinterval + child.getHeight()) / 2;
            // 绘制轴点圆
            c.drawCircle(centerx, centery, circle_radius, mPaint);
            // 通过Canvas绘制角标
            // c.drawBitmap(mIcon,centerx - circle_radius ,centery - circle_radius,mPaint);

            /**
             * 绘制上半轴线
             */
            c.drawLine(centerx, child.getTop() - itemView_topinterval, centerx, centery - circle_radius, mPaint);

            /**
             * 绘制下半轴线
             */
            c.drawLine(centerx, centery + circle_radius, centerx, child.getBottom(), mPaint);

            /**
             * 绘制左边时间文本
             */
            c.drawText(String.valueOf(list.get(parent.getChildAdapterPosition(child)).get("time")), child.getLeft() - itemView_leftinterval * 5 / 6, centery - circle_radius, mPaint1);
            c.drawText(String.valueOf(list.get(parent.getChildAdapterPosition(child)).get("date")), child.getLeft() - itemView_leftinterval * 5 / 6 + 5, centery - circle_radius + 20, mPaint2);
        }
    }
}
