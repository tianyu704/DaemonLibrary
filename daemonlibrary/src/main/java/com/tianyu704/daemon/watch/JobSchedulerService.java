package com.tianyu704.daemon.watch;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.util.Log;

import com.tianyu704.daemon.DaemonEnv;

/**
 * Android 5.0+ 使用的 JobScheduler.
 * 运行在 :watch 子进程中.
 *
 */
@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobSchedulerService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d("tianyu704", "JobSchedulerService  onStartJob 启动。。。。");
        DaemonEnv.startServiceSafely(JobSchedulerService.this,
                WatchDogService.class);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d("tianyu704", "JobSchedulerService  onStopJob 停止。。。。");
        return false;
    }
}
