package com.example.rockypzhang.buglydemo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.Toast;

import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.bugly.beta.tinker.TinkerManager;
import com.tencent.bugly.beta.upgrade.UpgradeStateListener;

import java.util.Locale;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁
        Beta.canAutoDownloadPatch = true;
        // 设置是否提示用户重启
        Beta.canNotifyUserRestart = true;
        // 设置是否自动合成补丁
        Beta.canAutoPatch = true;
        /**
         *  全量升级状态回调
         */
        Beta.upgradeStateListener = new UpgradeStateListener() {
            @Override
            public void onUpgradeFailed(boolean b) {

            }

            @Override
            public void onUpgradeSuccess(boolean b) {

            }

            @Override
            public void onUpgradeNoVersion(boolean b) {
//                Toast.makeText(getApplicationContext(), "最新版本", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onUpgrading(boolean b) {
//                Toast.makeText(getApplicationContext(), "onUpgrading", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadCompleted(boolean b) {

            }
        };

        /**
         * 补丁回调接口，可以监听补丁接收、下载、合成的回调
         */
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFileUrl) {
                Toast.makeText(getApplicationContext(), patchFileUrl, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(getApplicationContext(), String.format(Locale.getDefault(),
                        "%s %d%%",
                        Beta.strNotificationDownloading,
                        (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String patchFilePath) {
                Toast.makeText(getApplicationContext(), patchFilePath, Toast.LENGTH_SHORT).show();
//                Beta.applyDownloadedPatch();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplySuccess(String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {
                Toast.makeText(getApplicationContext(), "onPatchRollback", Toast.LENGTH_SHORT).show();
            }
        };

        TinkerManager.getInstance().setTinkerListener(new TinkerManager.TinkerListener() {
            @Override
            public void onDownloadSuccess(String s) {
                Toast.makeText(getApplicationContext(), "download success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String s) {

            }

            @Override
            public void onPatchStart() {

            }

            @Override
            public void onApplySuccess(String s) {

            }

            @Override
            public void onApplyFailure(String s) {

            }

            @Override
            public void onPatchRollback() {

            }
        });

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
