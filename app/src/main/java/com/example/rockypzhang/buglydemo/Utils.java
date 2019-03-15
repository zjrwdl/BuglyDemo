package com.example.rockypzhang.buglydemo;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Looper;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    public static Map<String, String> getJavaStacksOfThreads(int maxStackSize, boolean ignoreSelf) {
        Map<String, String> allStacks = new HashMap<String, String>(12);
        Map<Thread, StackTraceElement[]> allFrames = Thread.getAllStackTraces();
        if (allFrames == null) {
            return null;
        }
        Thread mainThread = Looper.getMainLooper().getThread();
        if (!allFrames.containsKey(mainThread)) {
            allFrames.put(mainThread, mainThread.getStackTrace());
        }
        long currentThreadId = Thread.currentThread().getId();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Thread, StackTraceElement[]> entry : allFrames.entrySet()) {
            if (ignoreSelf && currentThreadId == entry.getKey().getId()) {
                continue;
            }

            sb.setLength(0);
            if (entry.getValue() == null || entry.getValue().length == 0) {
                continue;
            }

            for (StackTraceElement stackFrame : entry.getValue()) {
                if (maxStackSize > 0 && sb.length() >= maxStackSize) {
                    // 闄愬埗鍫嗘爤澶у皬
                    sb.append(
                            "\n[Stack over limit size :" + maxStackSize + " , has been cut!]");
                    break;
                }

                sb.append(stackFrame.toString()).append("\n");
            }

            allStacks.put(entry.getKey().getName() + "(" + entry.getKey().getId() + ")",
                    sb.toString());
        }
        return allStacks;
    }

}
