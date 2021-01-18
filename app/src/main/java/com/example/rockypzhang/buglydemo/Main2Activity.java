package com.example.rockypzhang.buglydemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.tencent.bugly.crashreport.CrashReport;

public class Main2Activity extends Activity {
    NativeCrashJni nativeCrashJni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nativeCrashJni = NativeCrashJni.getInstance();
    }

    public void crash(View view){
//        CrashReport.testNativeCrash();
        //nativeCrashJni.createNativeCrash();
        CrashReport.testNativeCrash();
    }

    public void javacrash(View view){
        CrashReport.testJavaCrash();
    }

    public void anr(View view){
        CrashReport.testANRCrash();
    }
}
