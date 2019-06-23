package com.practice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class BgService extends Service {
    private static final String LOG_TAG = "BgService";
    private BgServiceImpl mBinder = null;

    public BgService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "-> onCreate()");
        mBinder = new BgServiceImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "-> onBind()");
        return mBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //super.onStartCommand(intent, flags, startId);
        Log.d(LOG_TAG, "-> onStartCommand()");
        return START_STICKY;
    }
}
