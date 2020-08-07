package com.myapplication.myandroiddemo.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.myapplication.kotlin.KotlinActivity;
import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.myutils.AppUtils;
import com.myapplication.myandroiddemo.myutils.FileUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class HomeActivity extends MyBaseActivity implements View.OnClickListener {
    /**
     * Called when the activity is first created.
     */

    private Button btn_sqllite;
    private Button btn_horizontallistView;
    private Button btn_camera;
    private Button btn_expandableTextView;
    private Button btn_spannablestringbuilder;
    private Button btn_nestedscrolling;
    private Button btn_immersive;
    private Button btn_diyview;
    private Button btn_watch;
    private Button btn_jsoup;
    private Button btn_touch;
    private Button btn_2048;
    private Button btn_rxjava;
    private Button btn_apk;
    private Button constraint;
    private Button socket;
    private Button coordinator;
    private Button bmobSDK;
    private Button kotlin;
    private Button timeline;
    private Button initFragment;

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
        btn_sqllite = findViewById(R.id.btn_sqllite);
        btn_horizontallistView = findViewById(R.id.btn_horizontallistView);
        btn_camera = findViewById(R.id.btn_camera);
        btn_expandableTextView = findViewById(R.id.btn_expandableTextView);
        btn_spannablestringbuilder = findViewById(R.id.btn_spannablestringbuilder);
        btn_nestedscrolling = findViewById(R.id.btn_nestedscrolling);
        btn_immersive = findViewById(R.id.btn_immersive);
        btn_diyview = findViewById(R.id.btn_diyview);
        btn_watch = findViewById(R.id.btn_watch);
        btn_jsoup = findViewById(R.id.btn_jsoup);
        btn_touch = findViewById(R.id.btn_touch);
        btn_2048 = findViewById(R.id.btn_2048);
        btn_rxjava = findViewById(R.id.btn_rxjava);
        btn_apk = findViewById(R.id.btn_apk);
        constraint = findViewById(R.id.btn_apk);
        socket = findViewById(R.id.socket);
        coordinator = findViewById(R.id.coordinator);
        bmobSDK = findViewById(R.id.bmobSDK);
        kotlin = findViewById(R.id.kotlin);
        timeline = findViewById(R.id.timeline);
        initFragment = findViewById(R.id.initFragment);

        btn_sqllite.setOnClickListener(this);
        btn_horizontallistView.setOnClickListener(this);
        btn_camera.setOnClickListener(this);
        btn_expandableTextView.setOnClickListener(this);
        btn_spannablestringbuilder.setOnClickListener(this);
        btn_nestedscrolling.setOnClickListener(this);
        btn_immersive.setOnClickListener(this);
        btn_diyview.setOnClickListener(this);
        btn_watch.setOnClickListener(this);
        btn_jsoup.setOnClickListener(this);
        btn_touch.setOnClickListener(this);
        btn_2048.setOnClickListener(this);
        btn_rxjava.setOnClickListener(this);
        btn_apk.setOnClickListener(this);
        constraint.setOnClickListener(this);
        socket.setOnClickListener(this);
        coordinator.setOnClickListener(this);
        bmobSDK.setOnClickListener(this);
        kotlin.setOnClickListener(this);
        timeline.setOnClickListener(this);
        initFragment.setOnClickListener(this);
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
            case R.id.btn_watch:
                startActivity(context, WatchActivity.class);
                break;
            case R.id.btn_jsoup:
                startActivity(context, JsoupActivity.class);
                break;
            case R.id.btn_touch:
                startActivity(context, TouchEventActivity.class);
                break;
            case R.id.btn_2048:
                startActivity(context, MyGame2048Activity.class);
                break;
            case R.id.btn_rxjava:
                startActivity(context, RxJavaActivity.class);
                break;
            case R.id.btn_apk:

                FileUtils.getInstance(HomeActivity.this).copyAssetsToSD("apks", "app/apks").
                        setFileOperateCallback(new FileUtils.FileOperateCallback() {
                            @Override
                            public void onSuccess() {
                                Log.e("kkk", "文件复制成功");
                                String payAppPackage = "com.algor.payhelper";
                                if (AppUtils.isPkgInstalled(HomeActivity.this, payAppPackage)) {
                                    Log.e("kkk", "已安装支付程序");
                                    Intent intent = new Intent();
                                    ComponentName componentName = new ComponentName(payAppPackage, "com.example.android.trivialdrivesample.MainActivity");
                                    intent.setComponent(componentName);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("data", "kkkkkkkkkk");
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                } else {
                                    //                                    AppUtils.silienceInstall(HomeActivity.this,Environment.getExternalStorageDirectory().getAbsolutePath()+"/app/apks/pay.apk");
                                    AppUtils.install(HomeActivity.this, Environment.getExternalStorageDirectory().getAbsolutePath() + "/app/apks/pay.apk");
                                }
                            }

                            @Override
                            public void onFailed(String error) {
                                Log.e("kkk", "文件复制失败");

                            }
                        });

                break;
            case R.id.constraint:
                startActivity(context, ConstraintLayoutActivity.class);
                break;
            case R.id.socket:
                startActivity(context, SocketActivity.class);
                break;
            case R.id.coordinator:
                startActivity(context, CoordinatorLayoutActivity.class);
                break;
            case R.id.bmobSDK:
                startActivity(context, BmobActivity.class);
                break;
            case R.id.kotlin:
                startActivity(context, KotlinActivity.class);
                break;
            case R.id.timeline:
                startActivity(context, TimeLineActivity.class);
                break;
            case R.id.initFragment:
                startActivity(context, FragmentInitActivity.class);
                break;
        }

    }

    public void LoadAPK(Bundle paramBundle, String dexpath, String dexoutputpath) {
        ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
        DexClassLoader localDexClassLoader = new DexClassLoader(dexpath,
                dexoutputpath, null, localClassLoader);
        try {
            PackageInfo plocalObject = getPackageManager()
                    .getPackageArchiveInfo(dexpath, 1);

            if ((plocalObject.activities != null)
                    && (plocalObject.activities.length > 0)) {
                String activityname = plocalObject.activities[0].name;
                Log.e("kkk", "activityname = " + activityname);

                Class localClass = localDexClassLoader.loadClass(activityname);
                Constructor localConstructor = localClass
                        .getConstructor(new Class[]{});
                Object instance = localConstructor.newInstance(new Object[]{});
                Log.e("kkk", "instance = " + instance);

                Method localMethodSetActivity = localClass.getDeclaredMethod(
                        "setActivity", new Class[]{Activity.class});
                localMethodSetActivity.setAccessible(true);
                localMethodSetActivity.invoke(instance, new Object[]{this});

                Method methodonCreate = localClass.getDeclaredMethod(
                        "onCreate", new Class[]{Bundle.class});
                methodonCreate.setAccessible(true);
                methodonCreate.invoke(instance, new Object[]{paramBundle});
            }
            return;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
