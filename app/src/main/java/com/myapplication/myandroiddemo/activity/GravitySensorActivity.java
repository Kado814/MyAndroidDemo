package com.myapplication.myandroiddemo.activity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.diyview.GravityBallView;
import com.myapplication.myandroiddemo.myutils.MyLog;

public class GravitySensorActivity extends AppCompatActivity{

    private ImageView iv_image;
    private SensorManager sensorManager;
    private Sensor sensor;
    private float x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GravityBallView gravityBallView=new GravityBallView(this);
        setContentView(gravityBallView);
//        setContentView(R.layout.activity_gravity_sensor);
//        iv_image = findViewById(R.id.iv_image);
//
//        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        sensorManager.unregisterListener(this);
    }


//    /**
//     * 磁阻传感器方向改变时
//     *
//     * @param event
//     */
//    @Override
//    public void onSensorChanged(SensorEvent event) {
//        x = event.values[SensorManager.DATA_X];
//        y = event.values[SensorManager.DATA_Y];
//        z = event.values[SensorManager.DATA_Z];
//        MyLog.d("x=" + x + "---y=" + y + "---z=" + z);
//        setLayoutX(iv_image, (int) x);
//    }
//
//    /**
//     * 传感器的精度发生变化时
//     *
//     * @param sensor
//     * @param accuracy
//     */
//    @Override
//    public void onAccuracyChanged(Sensor sensor, int accuracy) {
//
//    }

    /*
     * 设置控件所在的位置X，并且不改变宽高，
     * X为绝对位置，此时Y可能归0
     */
    public static void setLayoutX(View view, int x) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, margin.topMargin, x + margin.width, margin.bottomMargin);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }

    /*
     * 设置控件所在的位置YY，并且不改变宽高，
     * XY为绝对位置
     */
    public static void setLayout(View view, int x, int y) {
        ViewGroup.MarginLayoutParams margin = new ViewGroup.MarginLayoutParams(view.getLayoutParams());
        margin.setMargins(x, y, x + margin.width, y + margin.height);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
        view.setLayoutParams(layoutParams);
    }
}