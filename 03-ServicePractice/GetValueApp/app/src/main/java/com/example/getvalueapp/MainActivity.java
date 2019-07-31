package com.example.getvalueapp;

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
import android.widget.TextView;

import com.example.valueservice.IValueService;

public class MainActivity extends AppCompatActivity {
    static final String LOG_TAG = "GetValueApp";

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
                TextView editTextGetValue = findViewById(R.id.ResultTextView);
                if (bindSrv()) {
                    Log.d(LOG_TAG, "-> onClick(StartService): bindSrv() has returned TRUE!");
                    editTextGetValue.setText("In the process of bringing up the ValueService...");
                } else {
                    Log.e(LOG_TAG, "-> onClick(StartService): bindSrv() has returned FALSE! " +
                            "The system couldn't find the service or application doesn't have permission to bind to it!");
                    editTextGetValue.setText("bindSrv() ERROR: the system couldn't find the service " +
                            "or application doesn't have permission to bind to it!");
                }

            }
        });

        Button buttonGetValue = findViewById(R.id.GetValueButton);
        buttonGetValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "-> onClick(GetValue)");
                TextView editTextGetValue = findViewById(R.id.ResultTextView);
                if (mService==null) {
                    Log.e(LOG_TAG, "-> Can't getValue, service is not started (bound)!");
                    editTextGetValue.setText("Can't getValue, service is not started (bound)!");
                    return;
                }
                try {
                    int res = mService.getValue();
                    Log.d(LOG_TAG, "-> onClick(GetValue): res = " + res);
                    editTextGetValue.setText("GetValue result = "+res);
                }
                catch (Exception e)
                {
                    Log.e(LOG_TAG, "-> onClick(GetValue): exception detected: " + e.getMessage());
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
            TextView editTextGetValue = findViewById(R.id.ResultTextView);
            editTextGetValue.setText("The ValueService is started (bound)!");
            mService = com.example.valueservice.IValueService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(LOG_TAG, "-> onServiceDisconnected()");
            mService = null;
        }
    };

}
