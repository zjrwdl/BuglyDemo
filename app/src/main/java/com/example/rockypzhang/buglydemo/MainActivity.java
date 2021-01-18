package com.example.rockypzhang.buglydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.tencent.bugly.crashreport.CrashReport;


public class MainActivity extends Activity {
    NativeCrashJni nativeCrashJni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CrashReport.UserStrategy userStrategy = new CrashReport.UserStrategy(this);
        userStrategy.setEnableCatchAnrTrace(true);
        //CrashReport.initCrashReport(this,"d562178d23",true);
        CrashReport.initCrashReport(this,"d562178d23",true,userStrategy);
        /*new ANRWatchDog(5000).start();
        new ANRWatchDog().setANRListener(new ANRWatchDog.ANRListener() {
            @Override
            public void onAppNotResponding(ANRError error) {
                // Handle the error. For example, log it to HockeyApp:
                Log.d("crashreport","onAppNotResponding");
            }
        }).start();*/
        //UMConfigure.init(this, "5f618418b473963242a023e6", "Umeng", UMConfigure.DEVICE_TYPE_PHONE, "");
    }

    public void pthread(View view){
        NativeCrashJni.getInstance().pthead();
    }

    public void crash(View view){
        CrashReport.testJavaCrash();
    }

    public void anr(View view){
        try {
            int trySleep = 0;
            while (trySleep++ < 30) {
                Thread.sleep(5000);
            }
        } catch (Throwable thr) {
        }
    }

    public void nativeAnr(View view){
        nativeCrashJni = NativeCrashJni.getInstance();
        nativeCrashJni.createNativeCrash();
    }


    public void nativeCrash(View view){
        nativeCrashJni = NativeCrashJni.getInstance();
        nativeCrashJni.createNativeCrash();
    }

    public void upgrade(View view){
    }

    public void hotfix(View view){
        Toast.makeText(this,"patch success 1.2.1_q_33patch",Toast.LENGTH_LONG).show();
    }

    public void newApp(View view){
        Intent intent = new Intent();
        intent.setClassName(this,Main2Activity.class.getName());
        startActivity(intent);
    }

    public void nativeMallocOomCrash(View view) {
        Log.d("crashreport","nativeMallocOomCrash");
        new Thread(new Runnable() {
            @Override
            public void run() {
                NativeCrashJni.getInstance().createNativeOomMallocCrash();
            }
        }).start();
    }

    public void nativeMmapOomCrash(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                NativeCrashJni.getInstance().createNativeOomMmapCrash();
            }
        }).start();
    }


}
