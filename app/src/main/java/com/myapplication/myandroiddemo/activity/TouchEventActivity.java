package com.myapplication.myandroiddemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.myutils.MyLog;

/*
* Touch事件传递机制demo
* */
public class TouchEventActivity extends AppCompatActivity {

    private static final String TAG = "KKK";
    ViewGroup myLayout;
    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

        myLayout = findViewById(R.id.myLayout);
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);


        myLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                return false;
                return true;

            }
        });
        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLog.d("点击了ViewGroup");
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLog.d("点击了btn1");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLog.d("点击了btn2");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
