package com.myapplication.javademo;

/**
 * Created by ${KZJ} on 2018/11/15.
 */
public class Task implements Runnable {
    private int mTaskCount = 0;

    @Override
    synchronized public void run() {
//        super.run();
        mTaskCount++;
        System.out.println("当前线程" + Thread.currentThread().getName()+"执行第" + mTaskCount + "任务");
    }
}
