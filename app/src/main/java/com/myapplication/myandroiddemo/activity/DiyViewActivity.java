package com.myapplication.myandroiddemo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.view.CounterView;
import com.myapplication.myandroiddemo.view.TaiJi;

public class DiyViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //        final TaiJi taiJi = new TaiJi(this);
        setContentView(R.layout.activity_diy);
        //        setContentView(taiJi);
        //        final Handler handler
        // = new Handler() {
        //            private float degrees = 0;
        //
        //            @Override
        //            public void handleMessage(Message msg) {
        //                super.handleMessage(msg);
        //                taiJi.setRotate(degrees += 5);
        //                this.sendEmptyMessageDelayed(0, 80);
        //            }
        //        };
        //
        //        handler.sendEmptyMessageDelayed(0, 20);
        //        setContentView(R.layout.activity_diyview);
    }

}
