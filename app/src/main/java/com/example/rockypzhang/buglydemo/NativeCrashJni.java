package com.example.rockypzhang.buglydemo;

import android.util.Log;


public class NativeCrashJni {
    static NativeCrashJni nativeCrashJni;
    static {
        Log.d("TEST","loadLibrary");
        System.loadLibrary("native-lib");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String pthead();
    public native void createNativeCrash();
    public native void createNativeAnr();
    public native void createNativeOomMallocCrash();
    public native void createNativeOomMmapCrash();

    public static NativeCrashJni getInstance(){
        if (nativeCrashJni==null){
            nativeCrashJni = new NativeCrashJni();
            Log.d("TEST","nativeCrashJni null");
        }
        Log.d("TEST","nativeCrashJni has been initialed");
        return nativeCrashJni;
    }
}
