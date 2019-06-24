package com.example.serviceskeleton;

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

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAB = "MyApp";
    private IServiceSkeleton mService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(LOG_TAB, "-> onCreate()");

        Button startBtn = findViewById(R.id.start_service);
        startBtn.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(LOG_TAB, "-> onClick(startService)");
                startService();
            }
        });
    }

    private void startService() {
        Intent intent = new Intent(this, ServiceSkeleton.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Log.d(LOG_TAB, "-> onServiceConnected()");
            mService = IServiceSkeleton.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        	Log.d(LOG_TAB, "-> onServiceDisconnected()");
            mService = null;
        }
    };
}
