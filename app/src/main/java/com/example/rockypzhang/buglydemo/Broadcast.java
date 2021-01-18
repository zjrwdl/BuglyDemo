package com.example.rockypzhang.buglydemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.github.anrwatchdog.ANRError;
import com.github.anrwatchdog.ANRWatchDog;

/**
 * Description:
 *
 * @author: rockypzhang
 * Time: 2020/8/31
 */
public class Broadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            Log.d("anr","test anr");
            //Thread.sleep(100000);
            //NativeCrashJni nativeCrashJni = NativeCrashJni.getInstance();
            //nativeCrashJni.createNativeCrash();
        } catch (Exception e) {

        }
    }
}
