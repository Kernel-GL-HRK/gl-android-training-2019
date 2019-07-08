package com.practice.setterapp;

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
import android.widget.EditText;
import android.widget.Toast;

import com.practice.service.IBgService;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "SetterApp";
    private static final String SRV_PKG = "com.practice.service";
    private static final String SRV_NAME = "BgService";

    private ServiceConnection mSrvConn = null;
    private IBgService mService = null;
    private boolean mBound = false;
    EditText mTextEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "-> onCreate()");
        connectToService();
        mTextEdit = findViewById(R.id.enterVal);
        Button button = findViewById(R.id.setButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAG, "-> onClick()");
                String inputStr = String.valueOf(mTextEdit.getText());
                if (!inputStr.isEmpty()) {
                    try {
                        int val = Integer.parseInt(inputStr);
                        setValue(val);
                    } catch (NumberFormatException e) {
                        showMsg("Error: invalid value");
                    }
                } else {
                    showMsg("Error: no value");
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

    private void setValue(int val) {
        Log.d(LOG_TAG, "-> setText(" + val + ")");
        try {
            mService.setValue(val);
            showMsg("Value Changed");
        } catch (RemoteException e) {
            showMsg("Error: value wasn't changed");
        }
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

    private void showMsg(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
