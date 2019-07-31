package com.example.setvalueapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.valueservice.IValueService;

public class MainActivity extends AppCompatActivity {
    static final String LOG_TAG = "SetValueApp";

    private IValueService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "-> onCreate() MainActivity");
        setContentView(R.layout.activity_main);

        Button buttonStartService = findViewById(R.id.StartServiceButton);
        buttonStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "-> onClick(StartService)");
                if (mService!=null) return;
                TextView editTextSetValue = findViewById(R.id.ResultTextView);
                if (bindSrv()) {
                    Log.d(LOG_TAG, "-> onClick(StartService): bindSrv() has returned TRUE!");
                    editTextSetValue.setText("In the process of bringing up the ValueService...");
                } else {
                    Log.e(LOG_TAG, "-> onClick(StartService): bindSrv() has returned FALSE! " +
                            "The system couldn't find the service or application doesn't have permission to bind to it!");
                    editTextSetValue.setText("bindSrv() ERROR: the system couldn't find the service " +
                            "or application doesn't have permission to bind to it!");
                }

            }
        });

        Button buttonSetValue = findViewById(R.id.SetValueButton);
        buttonSetValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "-> onClick(SetValue)");
                TextView textViewSetValue = findViewById(R.id.ResultTextView);
                if (mService==null) {
                    Log.e(LOG_TAG, "-> Can't setValue, service is not started (bound)!");
                    textViewSetValue.setText("Can't setValue, service is not started (bound)!");
                    return;
                }
                EditText editTextNewValue = findViewById(R.id.newValue);
                if (editTextNewValue.length() <= 0) {
                    Log.w(LOG_TAG, "-> onClick(SetValue): EditText 'newValue' if empty!");
                    textViewSetValue.setText("EditText 'newValue' is empty!");
                    return;
                }
                try {
                    int newValue = Integer.parseInt(editTextNewValue.getText().toString());
                    mService.setValue(newValue);
                    Log.d(LOG_TAG, "-> onClick(SetValue): SetValue has set the new value" + newValue);
                    textViewSetValue.setText("SetValue has set the new value " + newValue);
                    editTextNewValue.setText("");
                }
                catch (Exception e)
                {
                    Log.e(LOG_TAG, "-> onClick(SetValue): exception detected: " + e.getMessage());
                    textViewSetValue.setText("An exception detected: " + e.getMessage());
                    editTextNewValue.setText("");
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "-> onDestroy() MainActivity");
        unbindSrv();
        super.onDestroy();
    }


    private boolean bindSrv() {
        Log.d(LOG_TAG, "-> bindSrv()");
        Intent intent = new Intent("Let's start ValueService");
        intent.setPackage("com.example.valueservice");
        //startForegroundService(intent); //Leads to restart the service in ~10sec when it was started 1st time.
        return bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindSrv() {
        Log.d(LOG_TAG, "-> unbindSrv()");
        if (mConnection == null) return;
        Log.d(LOG_TAG, "-> unbindSrv() will call unbindService()");
        unbindService(mConnection);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(LOG_TAG, "-> onServiceConnected()");
            TextView editTextSetValue = findViewById(R.id.ResultTextView);
            editTextSetValue.setText("The ValueService is started (bound)!");
            mService = com.example.valueservice.IValueService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(LOG_TAG, "-> onServiceDisconnected()");
            mService = null;
        }
    };
}
