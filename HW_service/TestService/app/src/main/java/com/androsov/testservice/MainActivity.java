package com.androsov.testservice;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "TestService:Activity";
    Button btnStartSrv;
    private com.androsov.testservice.ITestService mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "-> onServiceConnected()");
            mService = com.androsov.testservice.ITestService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "-> onServiceDisconnected");
            mService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "-> onCreate()");

        btnStartSrv = findViewById(R.id.btnStartSrv);
        btnStartSrv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "-> onClick");

        switch (v.getId()) {
            case R.id.btnStartSrv:
                startService();
                break;

            default:
        }
    }

    private void startService() {
        Intent intent = new Intent(this, TestService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
}
