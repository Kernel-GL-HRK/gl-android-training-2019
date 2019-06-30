package ua.org.procamp.simpleservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class SimpleService extends Service {
    private static final String LOG_TAG = "SimpleService";
    private IBinder mBinder = null;

    public SimpleService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(LOG_TAG, "-> onCreate()");
        mBinder = new SimpleServiceImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(LOG_TAG, "-> onBind()");
        return mBinder;
    }
}
