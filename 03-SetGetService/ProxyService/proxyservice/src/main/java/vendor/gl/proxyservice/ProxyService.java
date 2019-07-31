package vendor.gl.proxyservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ProxyService extends Service {
    private final String TAG = "ProxyService";

    private IBinder mBinder = null;
    private int mValue = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "onCreate()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "onStartCommand()");

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind()");
        return mBinder = new IProxyService.Stub() {
            @Override
            public void setValue(int intValue) {
                Log.i(TAG, "setValue(" + intValue + ")");
                mValue = intValue;
            }

            @Override
            public int getValue() {
                Log.i(TAG, "getValue() -> " + mValue);
                return mValue;
           }
        };
    }
}
