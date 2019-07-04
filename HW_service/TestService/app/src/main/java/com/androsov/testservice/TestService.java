package com.androsov.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class TestService extends Service {

    private static final String TAG = "TestService:Service";
    private TestServiceImpl mBinder = null;

    public TestService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "-> onCreate() " + this.getClass().getName());

        mBinder = new TestServiceImpl();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "-> onStartCmd()");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "-> onBind()");
        return mBinder;
    }
}
