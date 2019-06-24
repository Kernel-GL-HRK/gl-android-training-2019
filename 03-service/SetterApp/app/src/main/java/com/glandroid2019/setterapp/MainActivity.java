package com.glandroid2019.setterapp;

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
import android.widget.EditText;

import com.glandroid2019.myservice.IMyService;

public class MainActivity extends AppCompatActivity {

    final static String LOG_TAG = "xan_mySrvc_set";
    private static final String packageName = "com.glandroid2019.myservice";
    private static final String serviceName = packageName + "." + "Myservice";
    private IMyService mService = null;

    Button buttonSet;
    EditText payload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAG, "onCreate()");
        startSrv();
        buttonSet = findViewById(R.id.buttonSet);
        payload = findViewById(R.id.payload);

        View.OnClickListener setButton = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buffText = payload.getText().toString();
                int payloadInt;
                try {
                    payloadInt =  Integer.valueOf(buffText);
                } catch (NumberFormatException e) {
                    Log.d(LOG_TAG, "NumberFormatException. Use only digits as payload. Using default value 0");
                    payloadInt = 0;
                }
                try {
                    if(mService != null) {
                        mService.setValue(payloadInt);
                    } else {
                        Log.d(LOG_TAG, "error onSetValue(). Restarting service ");
                        startSrv();
                    }
                } catch (RemoteException e) {
                    Log.i(LOG_TAG, "onServiceConnected remote exception");
                }
            }
        };

        buttonSet.setOnClickListener(setButton);
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
