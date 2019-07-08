package com.example.simplegetter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.simpleservice.ISimpleService;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "GetterActivity";

    private ISimpleService mService = null;

    private Button mGetButton = null;
    private TextView mValueText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "##> onCreate()");

        startService();
        mGetButton = findViewById(R.id.getButton);
        mValueText = findViewById(R.id.valueText);

        mGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mService != null) {
                        mValueText.setText(String.valueOf(mService.getValue()));
                    } else {
                        Log.e(LOG_TAG, "##> Service isn't bound, restarting it");
                        startService();
                    }
                } catch (RemoteException exc) {
                    Log.e(LOG_TAG, "##> Remote exception on getting value from SimpleServer: " + exc.getMessage());
                }
            }
        });
    }

    private void startService() {
        Log.d(LOG_TAG, "##> startService()");
        String servicePackageName = ISimpleService.class.getPackage().getName();
        Intent intent = new Intent(servicePackageName + ".SimpleService");
        intent.setPackage(servicePackageName);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(LOG_TAG, "##> onServiceConnected");
            mService = ISimpleService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(LOG_TAG, "##> onServiceDisconnected");
            mService = null;
        }
    };
}
