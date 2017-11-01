package com.myapplication.myandroiddemo.activity;

import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.myapplication.myandroiddemo.R;

public class HomeActivity extends MyBaseActivity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */
    private Button btn_sqllite;
    private Button btn_horizontallistView;
    private Button btn_camera;
    private Button recyclerview;
    private Button btn_expandableTextView;
    private Button btn_spannablestringbuilder;
    private Button btn_nestedscrolling;
    private Button btn_immersive;
    private Button btn_diyview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
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

    private void initView() {
        btn_sqllite = (Button) findViewById(R.id.btn_sqllite);
        btn_horizontallistView = (Button) findViewById(R.id.btn_horizontallistView);
        btn_camera = (Button) findViewById(R.id.btn_camera);
        recyclerview = (Button) findViewById(R.id.recyclerview);
        btn_expandableTextView = (Button) findViewById(R.id.btn_expandableTextView);
        btn_spannablestringbuilder = (Button) findViewById(R.id.btn_spannablestringbuilder);
        btn_nestedscrolling = (Button) findViewById(R.id.btn_nestedscrolling);
        btn_immersive = (Button) findViewById(R.id.btn_immersive);
        btn_diyview = (Button) findViewById(R.id.btn_diyview);

        btn_sqllite.setOnClickListener(this);
        btn_horizontallistView.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
        recyclerview.setOnClickListener(this);
        btn_expandableTextView.setOnClickListener(this);
        btn_spannablestringbuilder.setOnClickListener(this);
        btn_nestedscrolling.setOnClickListener(this);
        btn_immersive.setOnClickListener(this);
        btn_diyview.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sqllite:
                startActivity(context, SqlLiteActivity.class);
                break;
            case R.id.btn_horizontallistView:
                startActivity(context, HorizontalListViewActivity.class);
                break;
            case R.id.btn_camera:
                startActivity(context, CameraActivity.class);
                break;
            case R.id.recyclerview:
                startActivity(context, RecyclerViewActivity.class);
                break;
            case R.id.btn_expandableTextView:
                startActivity(context, ExpandableTextViewActivity.class);
                break;
            case R.id.btn_spannablestringbuilder:
                startActivity(context, SpannableStringBuilderActivity.class);
                break;
            case R.id.btn_nestedscrolling:
                startActivity(context, NavigationDrawerctivity.class);
//                startActivity(context, ScrollingActivity.class);
//                startActivity(context, NestedScrollingActivity.class);
                break;
            case R.id.btn_immersive://沉浸式
                startActivity(context, ImmersiveActivity.class);
                break;
            case R.id.btn_diyview:
                startActivity(context, DiyViewActivity.class);
                break;
        }

    }
}
