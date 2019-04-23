package com.example.rockypzhang.buglydemo;

import android.util.Log;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;

/**
 * Description:
 *
 * @author: rockypzhang
 * Time: 2019/4/4
 */
public class JavaCrashCaught implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Log.d("TEST","JavaCrashCaught");
        //CrashReport.postCatchedException(e);
        CrashReport.postException(0, this.getClass().getName(), "", ""+e.getStackTrace(), null);
    }
}
