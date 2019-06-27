package com.example.servicegetter;

import android.content.ComponentName;
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

import com.example.serviceskeleton.IServiceSkeleton;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "GetServiceApplication";
    private static final String PACKAGE = "com.example.serviceskeleton";
    private static final String SERVICE_NAME = PACKAGE + "." + "ServiceSkeleton";

    private Button m_BtnGetValue;
    private TextView m_Value;

    private IServiceSkeleton m_Service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "-> onCreate()");

        connectToService();

        m_BtnGetValue = findViewById(R.id.get_value);
        m_BtnGetValue.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d(TAG, "-> onClick(startService)");

                int value = 0;

                try {
                    value = m_Service.getValue();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                m_Value.setText(String.format("%d", value));
            }
        });

        m_Value = findViewById(R.id.data_out);
    }

    private void connectToService(){
        Log.d(TAG, "-> connectToService()");
        Intent intent = new Intent(SERVICE_NAME);
        intent.setPackage(PACKAGE);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, " -> onServiceConnected()");
            m_Service = IServiceSkeleton.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG, " -> onServiceDisconnected()");
            m_Service = null;
        }
    };
}
