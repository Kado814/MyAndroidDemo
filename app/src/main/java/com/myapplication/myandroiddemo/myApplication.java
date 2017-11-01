package com.myapplication.myandroiddemo;

import android.app.Application;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;

/**
 * Created by Administrator on 2016/11/15.
 */
public class myApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogConfiguration config = new LogConfiguration.Builder()
                .tag("kkk")                                             // 指定 TAG，默认为 "X-LOG"
                .t()                                                   // 允许打印线程信息，默认禁止
                .st(2)                                                 // 允许打印深度为2的调用栈信息，默认禁止
                .b()                                                   // 允许打印日志边框，默认禁止
                .build();
        XLog.init(LogLevel.ALL, config);

        String jsonString = "{\"name\": \"Elvis\", \"age\": 18}";
        XLog.e("kkkkkkkkkkkkkkkkkkk");
        XLog.e(jsonString);
        XLog.json(jsonString);
    }
}
