package com.example.rockypzhang.buglydemo;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.tinker.TinkerUtils;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    NativeCrashJni nativeCrashJni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toast.makeText(this,NativeCrashJni.getInstance().stringFromJNI(),Toast.LENGTH_LONG).show();
        ThreadWatchDog.getInstance().addThread(new Handler());
        ThreadWatchDog.getInstance().start();
    }


    public void crash(View view){
//        int i = 10/0;
//        CrashReport.testJavaCrash();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Log.d("TEST","xxxxxxxxxxxxxxxx");
//                    URL url = new URL("https://www.sina.com");
//                    HttpURLConnection conn = null;
//                    CrashReport.setHttpProxy("10.16.85.43",8888);
//                    if(CrashReport.getHttpProxy() != null) {
//                        conn = (HttpURLConnection) url.openConnection(CrashReport.getHttpProxy());
//                    }else {
//                        conn = (HttpURLConnection) url.openConnection();
//                    }
//                    conn.setRequestMethod("GET");
//                    conn.setConnectTimeout(5*1000);
//                    conn.setDoInput(true);
//                    conn.connect();
//                    System.out.println("response code:"+conn.getResponseCode());
//                    Log.d("TEST","xx:"+conn.getResponseCode());
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    public void anr(View view){
        CrashReport.testANRCrash();
    }


    public void nativeCrash(View view){
//        CrashReport.testNativeCrash();
        nativeCrashJni = NativeCrashJni.getInstance();
        nativeCrashJni.createNativeCrash();
    }

    public void upgrade(View view){
        Beta.checkUpgrade(true,false);
    }

    public void hotfix(View view){
        Toast.makeText(this,"patch success 1.2.1_q_33patch",Toast.LENGTH_LONG).show();
    }

    public void newApp(View view){
        Intent intent = new Intent();
        intent.setClassName(this,Main2Activity.class.getName());
        startActivity(intent);
    }
}
