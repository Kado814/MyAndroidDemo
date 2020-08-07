package com.myapplication.myandroiddemo.activity;

import android.graphics.Color;
import android.os.Build;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.myapplication.myandroiddemo.R;

public class ImmersiveActivity extends AppCompatActivity {

    private boolean immersive = false;//是否沉浸

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immersive);
        if (!immersive) {
            //透明导航栏，状态栏---APP使用率高
            if (Build.VERSION.SDK_INT >= 21) {
                View decorView = getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//让应用的主体内容占用系统导航栏的空间
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                getWindow().setNavigationBarColor(Color.TRANSPARENT);//将导航栏设置成透明色
                getWindow().setStatusBarColor(Color.TRANSPARENT);//将状态栏设置成透明色
            }
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (immersive) {
            //沉浸式--只需要这几句代码
            if (hasFocus && Build.VERSION.SDK_INT >= 19) {
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }
}
