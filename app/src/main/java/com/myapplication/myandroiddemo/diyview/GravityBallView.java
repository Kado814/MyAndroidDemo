package com.myapplication.myandroiddemo.diyview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.myapplication.myandroiddemo.R;

import static android.content.Context.SENSOR_SERVICE;

/**
 * @Author: KKK
 * @CreateDate: 2020/10/16 5:30 PM
 */

public class GravityBallView extends SurfaceView implements SurfaceHolder.Callback, Runnable, SensorEventListener {
    SensorManager mSensorManager;
    Sensor mSensor_of_orientation;
    SensorEventListener mSensorEventListener;
    float GX = 0;
    float GY = 0;
    float GZ;
    /**
     * 每30帧刷新一次屏幕
     **/
    public static final int TIME_IN_FRAME = 30;
    Paint mPaint = null;
    Paint mLinePaint = null;
    /**
     * 小球资源文件
     **/
    private Bitmap mbitmapBg;
    private Bitmap ball;
    private SurfaceHolder mSurfaceHolder = null;
    /**
     * 控制游戏更新循环
     **/
    boolean mRunning = false; // 子线程标志位
    private Canvas mCanvas = null;// 用于绘图的Canvas
    /**
     * 手机屏幕宽高
     **/
    int mphoneScreenWidth = 0;
    int mphoneScreenHeight = 0;
    /**
     * 小球的坐标位置
     **/
    private float mPosX = 0;
    private float mPosY = 0;
    boolean is_chujie = false;


    public void initView(Context context) {//自定义的初始化方法
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        mSurfaceHolder = this.getHolder();
        mSurfaceHolder.addCallback(this);
        mCanvas = new Canvas();
        mCanvas.drawColor(Color.YELLOW);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mLinePaint = new Paint();
        mLinePaint.setColor(Color.WHITE);
        mLinePaint.setTextSize(30);
        ball = BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_back);
        mbitmapBg = BitmapFactory.decodeResource(this.getResources(), R.drawable.bg_work_green);

        mSensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);//获取到 SensorManager 的实例
        mSensor_of_orientation = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);//获取重力传感器
        mSensorManager.registerListener(this, mSensor_of_orientation, SensorManager.SENSOR_DELAY_NORMAL);//注册传感器
    }

    private void Draw() {
        /**绘制游戏背景**/
        mCanvas.drawBitmap(mbitmapBg,0,0, mPaint);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mCanvas.drawPaint(paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));

        mCanvas.drawBitmap(ball, mPosX, mPosY, mPaint);
    }


    public GravityBallView(Context context) {
        super(context);
        initView(context);
    }

    public GravityBallView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public GravityBallView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mRunning = true;
        mphoneScreenWidth = this.getWidth();
        mphoneScreenHeight = this.getHeight();

        new Thread(this).start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mRunning = false;
        mSensorManager.unregisterListener(mSensorEventListener);//要调用 unregisterListener ()方法将使用的资源释放掉
    }

    @Override
    public void run() {
        while (mRunning) {
            /** 取得更新游戏之前的时间 **/
            long startTime = System.currentTimeMillis();
            /** 在这里加上线程安全锁 **/
            synchronized (mSurfaceHolder) {
                /** 拿到当前画布 然后锁定 **/
                mCanvas = mSurfaceHolder.lockCanvas();
                Draw();
                /** 绘制结束后解锁显示在屏幕上 **/
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
            /** 取得更新游戏结束的时间 **/
            long endTime = System.currentTimeMillis();
            /** 计算出游戏一次更新的毫秒数 **/
            int diffTime = (int) (endTime - startTime);
            /** 确保每次更新时间为30帧 **/
            while (diffTime <= TIME_IN_FRAME) {
                diffTime = (int) (System.currentTimeMillis() - startTime);
                /** 线程等待 **/
                Thread.yield();
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        GZ = event.values[SensorManager.DATA_Z];
        GX = event.values[SensorManager.DATA_X];
        GY = event.values[SensorManager.DATA_Y];

        mPosX -= GX * 50;
        mPosY += GY * 50;

        //检测小球是否超出边界
        if (mPosX < 0) {
            is_chujie = true;
            mPosX = 0;
        } else if (mPosX > mphoneScreenWidth - ball.getWidth()) {
            is_chujie = true;
            mPosX = mphoneScreenWidth - ball.getWidth();
        }
        if (mPosY <= 0) {
            mPosY = 0;
            is_chujie = true;
        } else if (mPosY > mphoneScreenHeight - ball.getHeight()) {
            mPosY = mphoneScreenHeight - ball.getHeight();
            is_chujie = true;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

