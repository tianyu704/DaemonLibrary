package com.tianyu704.daemonlibrary;

import android.app.Application;
import android.util.Log;

import com.tianyu704.daemon.ForegroundNotificationUtils;
import com.tianyu704.daemon.watch.WatchProcessPrefHelper;

/**
 * Created by tianyu704 ON 2018/12/13.
 * Email shihu.wang@bodyplus.cc 451082005@qq.com
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //需要在 Application 的 onCreate() 中调用一次 DaemonEnv.initialize()
        // 每一次创建进程的时候都需要对Daemon环境进行初始化，所以这里没有判断进程

        Log.d("tianyu704", "启动了Application");
        String processName = ApkHelper.getProcessName(this.getApplicationContext());
        if ("com.tianyu704.daemonlibrary".equals(processName)) {
            // 主进程 进行一些其他的操作
            Log.d("tianyu704", "启动主进程");

        } else if ("com.tianyu704.daemonlibrary:work".equals(processName)) {
            Log.d("tianyu704", "启动了工作进程");
        } else if ("com.tianyu704.daemonlibrary:watch".equals(processName)) {
            // 这里要设置下看护进程所启动的主进程信息
            WatchProcessPrefHelper.mWorkServiceClass = MainWorkService.class;
            // 设置通知栏的UI
            ForegroundNotificationUtils.setResId(R.drawable.ic_launcher);
            ForegroundNotificationUtils.setNotifyTitle("Misstory陪你走过每一天");
            ForegroundNotificationUtils.setNotifyContent("点击查看");
            ForegroundNotificationUtils.setPackgeName(BuildConfig.APPLICATION_ID);
            Log.d("tianyu704", "启动了看门狗进程");
        }


    }
}
