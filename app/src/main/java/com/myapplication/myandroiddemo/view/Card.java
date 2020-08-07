package com.myapplication.myandroiddemo.view;

import android.content.Context;
import android.graphics.Color;
import androidx.annotation.NonNull;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by ${KZJ} on 2018/8/27.
 */

public class Card extends FrameLayout {
    private TextView lable;
    private int num;

    public Card(@NonNull Context context) {
        super(context);

        setBackgroundColor(Color.parseColor("#BBADA4"));
        lable = new TextView(getContext());
        lable.setBackgroundColor(Color.parseColor("#CFBDB5"));
        lable.setGravity(Gravity.CENTER);
        lable.setTextSize(32);
        LayoutParams params = new LayoutParams(-1, -1);
        params.setMargins(10,10,0,0);
        addView(lable, params);
        setNum(0);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        lable.setText(String.valueOf(num));
    }

    public boolean equals(Card card) {
        return getNum() == card.getNum();
    }
}
