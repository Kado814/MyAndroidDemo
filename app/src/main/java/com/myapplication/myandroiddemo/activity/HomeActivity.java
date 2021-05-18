package com.myapplication.myandroiddemo.activity;

import android.view.View;

import com.myapplication.kotlin.KotlinActivity;
import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.activity.ui.ShapeOfViewActivity;
import com.myapplication.myandroiddemo.activity.ui.WebviewActivity;
import com.myapplication.myandroiddemo.base.BaseActivity;

import butterknife.OnClick;

public class HomeActivity extends BaseActivity {
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
            R.id.btn_touch, R.id.btn_2048, R.id.btn_rxjava, R.id.constraint, R.id.socket,
            R.id.coordinator, R.id.bmobSDK, R.id.kotlin, R.id.timeline, R.id.initFragment, R.id.vectorDrawable,
            R.id.gravitySensor, R.id.lottie, R.id.webView, R.id.shapeOfView})
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
            case R.id.shapeOfView:
                startActivity(ShapeOfViewActivity.class);
                break;
        }

    }
}
