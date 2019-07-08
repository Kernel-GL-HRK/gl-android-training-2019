package com.practice.getterapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.practice.service.IBgService;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "GetterApp";
    private static final String SRV_PKG = "com.practice.service";
    private static final String SRV_NAME = "BgService";

    private ServiceConnection mSrvConn = null;
    private IBgService mService = null;
    private TextView mTextView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "-> onCreate()");
        connectToService();
        mTextView = findViewById(R.id.textView);
        Button button = findViewById(R.id.getButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "-> onClick()");
                String txt = getValue();
                if (txt.isEmpty()) {
                    mTextView.setText("unknown");
                    mTextView.setTextColor(Color.RED);
                } else {
                    mTextView.setText(txt);
                    mTextView.setTextColor(Color.GREEN);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "-> onDestroy()");
        if (mSrvConn != null) {
            Log.d(LOG_TAG, "-> unbindService()");
            unbindService(mSrvConn);
        }
        super.onDestroy();
    }

    private String getValue(){
        Log.d(LOG_TAG, "-> setText()");
        String txt = "";
        try {
            int val = mService.getValue();
            txt = Integer.toString(val);
        } catch (RemoteException e) {
            Toast.makeText(getApplicationContext(), "Service connection error", Toast.LENGTH_SHORT).show();
        }
        return txt;
    }

    private void connectToService(){
        Log.d(LOG_TAG, "-> connectToService()");
        mSrvConn =  new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                Log.d(LOG_TAG, "-> onServiceConnected");
                mService = IBgService.Stub.asInterface(binder);
            }

            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "-> onServiceDisconnected");
            }
        };
        Intent intent = new Intent(SRV_PKG + "." + SRV_NAME);
        intent.setPackage(SRV_PKG);
        startForegroundService(intent);
        bindService(intent, mSrvConn, BIND_AUTO_CREATE);
    }
}
