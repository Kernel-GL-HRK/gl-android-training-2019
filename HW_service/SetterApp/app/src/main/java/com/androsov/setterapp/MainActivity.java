package com.androsov.setterapp;

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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "SetApplication";

    Button btnSetValue;
    EditText txtValue;

    com.androsov.testservice.ITestService RemoteService;
    private boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "-> onCreate()");

        btnSetValue = findViewById(R.id.btnSetValue);
        btnSetValue.setOnClickListener(this);

        txtValue = findViewById(R.id.txtValue);

        try {
            BindToService();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "-> onClick()");

        switch (v.getId()) {
            case R.id.btnSetValue:
                int Value = 0;

                try {
                    Value = Integer.parseInt(txtValue.getText().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                   RemoteService.setValue(Value);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            default:
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "-> onServiceConnected()");
            RemoteService = com.androsov.testservice.ITestService.Stub.asInterface(service);
            isBound = true;
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, "-> onServiceDisconnected()");
            RemoteService = null;
            isBound = false;
        }
    };

    private void BindToService() throws ClassNotFoundException {
        Intent intent = new Intent("com.androsov.testservice.TestService");
        intent.setPackage("com.androsov.testservice");
        intent.setAction("com.androsov.testservice.TestService");

        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }
}
