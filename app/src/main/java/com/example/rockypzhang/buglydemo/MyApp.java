package com.example.rockypzhang.buglydemo;

import android.app.Application;
import android.util.Log;

import com.tencent.bugly.crashreport.CrashReport;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        /* Bugly SDK初始化
         * 参数1：上下文对象
         * 参数2：APPID，平台注册时得到,注意替换成你的appId
         * 参数3：是否开启调试模式，调试模式下会输出'CrashReport'tag的日志
         */
        CrashReport.initCrashReport(getApplicationContext(), "d562178d23", true);
        Log.d("TEST","app init");
    }
}
