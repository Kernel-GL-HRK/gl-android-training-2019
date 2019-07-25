package com.bksnake.ledservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LedControlService extends Service {
    private static final String LOG_TAG = LedControlService.class.getSimpleName();
    private IBinder mBinder = null;

    public LedControlService() {
    }

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "-> onCreate()");
        super.onCreate();
        mBinder = (IBinder) new LedControlServiceImpl();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "-> onStartCommand()");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "-> onBind()");
        return mBinder;
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "-> onDestroy()");
        if (mBinder != null) {
            ((LedControlServiceImpl)mBinder).terminate();
        }
        super.onDestroy();
    }
}
