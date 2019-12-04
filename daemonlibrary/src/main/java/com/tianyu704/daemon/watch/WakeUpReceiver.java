package com.tianyu704.daemon.watch;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.tianyu704.daemon.DaemonEnv;

public class WakeUpReceiver extends BroadcastReceiver {

    /**
     * 监听 8 种系统广播 :
     * CONNECTIVITY\_CHANGE, USER\_PRESENT, ACTION\_POWER\_CONNECTED, ACTION\_POWER\_DISCONNECTED,
     * BOOT\_COMPLETED, MEDIA\_MOUNTED, PACKAGE\_ADDED, PACKAGE\_REMOVED.
     * 在网络连接改变, 用户屏幕解锁, 电源连接 / 断开, 系统启动完成, 挂载 SD 卡, 安装 / 卸载软件包时拉起 Service.
     * Service 内部做了判断，若 Service 已在运行，不会重复启动.
     * 运行在:watch子进程中.
     */
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("tianyu704", "WakeUpReceiver receive " + intent.getAction());
        if (WatchProcessPrefHelper.getIsStartDaemon(context)) {
            DaemonEnv.startServiceSafely(context, WatchDogService.class);
        }
    }

    public static class WakeUpAutoStartReceiver extends BroadcastReceiver {

        @SuppressLint("UnsafeProtectedBroadcastReceiver")
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("tianyu704", "WakeUpAutoStartReceiver receive " + intent.getAction());
            if (WatchProcessPrefHelper.getIsStartDaemon(context)) {
                DaemonEnv.startServiceSafely(context, WatchDogService.class);
            }
        }
    }

    public static class StartWatchReceiver extends BroadcastReceiver {

        @SuppressLint("UnsafeProtectedBroadcastReceiver")
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("tianyu704", "StartWatchReceiver receive " + intent.getAction());
            WatchProcessPrefHelper.setIsStartSDaemon(context, true);
        }
    }
}
