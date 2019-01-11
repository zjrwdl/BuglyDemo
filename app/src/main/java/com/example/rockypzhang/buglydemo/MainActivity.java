package com.example.rockypzhang.buglydemo;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tencent.bugly.crashreport.CrashReport;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public void crash(View view){
        int i = 10/0;
//        CrashReport.testJavaCrash();
    }

    public void anr(View view){
//        try {
//            Thread.sleep(30000);
//        }catch (Exception e){
//
//        }
        CrashReport.testANRCrash();
    }


    public void nativeCrash(View view){
        CrashReport.testNativeCrash();
    }

}
