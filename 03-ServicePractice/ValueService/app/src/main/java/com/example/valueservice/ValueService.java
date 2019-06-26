package com.example.valueservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ValueService extends Service {
    static final String LOG_TAG = "ValueService";
    private ValueServiceImpl binder = null;

    public ValueService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "-> onCreate()");
        binder = new ValueServiceImpl();
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "-> onDestroy()");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(LOG_TAG, "-> onStartCommand()");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "-> onBind()");
        return binder;
    }
}



