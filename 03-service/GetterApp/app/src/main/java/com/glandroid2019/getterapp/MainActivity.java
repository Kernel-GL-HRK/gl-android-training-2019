package com.glandroid2019.getterapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.glandroid2019.myservice.IMyService;

public class MainActivity extends AppCompatActivity {

    final static String LOG_TAG = "xan_mySrvc_get";
    private static final String packageName = "com.glandroid2019.myservice";
    private static final String serviceName = packageName + "." + "Myservice";
    private IMyService mService = null;

    Button buttonGet;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "onCreate()");

        startSrv();
        buttonGet = findViewById(R.id.buttonGet);
        result = findViewById(R.id.result);

        View.OnClickListener setButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(mService != null) {
                        result.setText(String.valueOf(mService.getValue()));
                    } else {
                        Log.d(LOG_TAG, "error onGetValue(). Restarting service ");
                        startSrv();
                    }
                } catch (RemoteException e) {
                    Log.i(LOG_TAG, "onServiceConnected remote exception");
                }
            }
        };

        buttonGet.setOnClickListener(setButton);
    }

    private void startSrv()  {
        Log.d(LOG_TAG, "onStartSrv");
        Intent intent = new Intent(serviceName);
        intent.setPackage(packageName);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(LOG_TAG, "onServiceConnected");
            mService = IMyService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(LOG_TAG, "onServiceDisonnected");
            mService = null;
        }
    };
}
