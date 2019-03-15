package com.example.rockypzhang.buglydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tencent.bugly.crashreport.CrashReport;

public class Main2Activity extends AppCompatActivity {
    NativeCrashJni nativeCrashJni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        nativeCrashJni = NativeCrashJni.getInstance();
    }

    public void crash(View view){
//        CrashReport.testNativeCrash();
        nativeCrashJni.createNativeCrash();
    }

    public void javacrash(View view){
        CrashReport.testJavaCrash();
    }
}
