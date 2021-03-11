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

import com.myapplication.kotlin.KotlinActivity;
import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.activity.ui.WebviewActivity;
import com.myapplication.myandroiddemo.myutils.AppUtils;
import com.myapplication.myandroiddemo.myutils.FileUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import butterknife.OnClick;
import dalvik.system.DexClassLoader;

public class HomeActivity extends MyBaseActivity {
    /**
     * Called when the activity is first created.
     */

    @Override
    protected int getLayoutResId() {
        return R.layout.main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
    }


    @OnClick({R.id.btn_sqllite, R.id.btn_camera, R.id.btn_expandableTextView, R.id.btn_spannablestringbuilder,
            R.id.btn_nestedscrolling, R.id.btn_immersive, R.id.btn_diyview, R.id.btn_watch, R.id.btn_jsoup,
            R.id.btn_touch, R.id.btn_2048, R.id.btn_rxjava, R.id.btn_apk, R.id.constraint, R.id.socket,
            R.id.coordinator, R.id.bmobSDK, R.id.kotlin, R.id.timeline, R.id.initFragment, R.id.vectorDrawable,
            R.id.gravitySensor, R.id.lottie, R.id.webView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sqllite:
                startActivity(SqlLiteActivity.class);
                break;
            case R.id.btn_camera:
                startActivity(CameraActivity.class);
                break;
            case R.id.btn_expandableTextView:
                startActivity(ExpandableTextViewActivity.class);
                break;
            case R.id.btn_spannablestringbuilder:
                startActivity(SpannableStringBuilderActivity.class);
                break;
            case R.id.btn_nestedscrolling:
                startActivity(ScrollingActivity.class);
                //                startActivity(context, NestedScrollingActivity.class);
                break;
            case R.id.btn_immersive://沉浸式
                startActivity(ImmersiveActivity.class);
                break;
            case R.id.btn_diyview:
                startActivity(DiyViewActivity.class);
                break;
            case R.id.btn_watch:
                startActivity(WatchActivity.class);
                break;
            case R.id.btn_jsoup:
                startActivity(JsoupActivity.class);
                break;
            case R.id.btn_touch:
                startActivity(TouchEventActivity.class);
                break;
            case R.id.btn_2048:
                startActivity(MyGame2048Activity.class);
                break;
            case R.id.btn_rxjava:
                startActivity(RxJavaActivity.class);
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
                startActivity(ConstraintLayoutActivity.class);
                break;
            case R.id.socket:
                startActivity(SocketActivity.class);
                break;
            case R.id.coordinator:
                startActivity(CoordinatorLayoutActivity.class);
                break;
            case R.id.bmobSDK:
                startActivity(BmobActivity.class);
                break;
            case R.id.kotlin:
                startActivity(KotlinActivity.class);
                break;
            case R.id.timeline:
                startActivity(TimeLineActivity.class);
                break;
            case R.id.initFragment:
                startActivity(FragmentInitActivity.class);
                break;
            case R.id.vectorDrawable:
                startActivity(VectorDrawableActivity.class);
                break;
            case R.id.gravitySensor:
                startActivity(GravitySensorActivity.class);
                break;
            case R.id.lottie:
                startActivity(LottieActivity.class);
                break;
            case R.id.webView:
                startActivity(WebviewActivity.class);
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
