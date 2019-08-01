package com.globallogic.procamp.ledcontrolservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class LedControlService extends Service {
    private static final String LOG_TAG = "LedControlService";
    private IBinder mBinder = null;

    public LedControlService() {
    }

    @Override
    public void onCreate() {
        Log.d(LOG_TAG, "-> onCreate()");
        super.onCreate();
        mBinder = (IBinder) new LedControlServiceImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "-> onBind()");
        return mBinder;
    }

}
