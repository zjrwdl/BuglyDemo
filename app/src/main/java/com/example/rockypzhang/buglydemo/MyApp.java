package com.example.rockypzhang.buglydemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.beta.Beta;
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
//        CrashReport.initCrashReport(getApplicationContext(), "d562178d23", true);
        String processName = Utils.getCurProcessName(this);
        switch (processName){
            case "com.example.rockypzhang.buglydemo":
                Bugly.init(getApplicationContext(), "d562178d23", true);
                Log.d("TEST","app init with d562178d23");
                break;
            case "com.example.rockypzhang.buglydemo:new":
                Bugly.init(getApplicationContext(), "47fe0d9d73", true);
                Log.d("TEST","app init with 47fe0d9d73");
                break;
            default:
                break;
        }
        Log.d("TEST","current process name:"+Utils.getCurProcessName(this));
        Beta.checkUpgrade(false,false);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);
        // 安装tinker
        Beta.installTinker();
    }
}
