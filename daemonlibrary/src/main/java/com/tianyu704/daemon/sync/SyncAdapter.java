package com.tianyu704.daemon.sync;

/**
 * Created by shihu.wang on 2017/4/10.
 * Email shihu.wang@bodyplus.cc
 */

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.Context;
import android.content.SyncResult;
import android.os.Bundle;

import com.tianyu704.daemon.DaemonEnv;
import com.tianyu704.daemon.watch.WatchDogService;


public class SyncAdapter extends AbstractThreadedSyncAdapter {


    private Context mContext;

    public SyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        this.mContext = context;
    }

    @Override
    public void onPerformSync(Account account, Bundle extras, String authority, ContentProviderClient provider, SyncResult syncResult) {
        DaemonEnv.startServiceSafely(mContext,WatchDogService.class);
    }
}