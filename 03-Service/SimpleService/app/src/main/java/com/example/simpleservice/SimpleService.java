package com.example.simpleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SimpleService extends Service {
    private static final String LOG_TAG = "SimpleService";
    private SimpleServiceImpl mBinder = null;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "##> onCreate()");
        mBinder = new SimpleServiceImpl();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "##> onStartCommand()");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "##> onBind()");
        return mBinder;
    }
}
