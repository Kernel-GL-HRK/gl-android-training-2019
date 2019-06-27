package com.example.servicesetter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.serviceskeleton.IServiceSkeleton;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "SetServiceApplication";
    private static final String PACKAGE = "com.example.serviceskeleton";
    private static final String SERVICE_NAME = PACKAGE + "." + "ServiceSkeleton";

    private Button m_BtnSetValue;
    private EditText m_Value;

    private IServiceSkeleton m_Service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d(TAG, "-> onCreate()");
        connectToService();
        m_BtnSetValue = findViewById(R.id.btnSet);
        m_Value = findViewById(R.id.set_data);

        m_BtnSetValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buffText = m_Value.getText().toString();
                int payloadInt;

                try {
                    payloadInt =  Integer.valueOf(buffText);
                } catch (NumberFormatException e) {
                    Log.d(TAG, "NumberException. Is not number");
                    payloadInt = 0;
                }

                try {
                    if(m_Service != null) {
                        m_Service.setValue(payloadInt);
                    } else {
                        Log.d(TAG, "ServiceException. Restarting service ");
                        connectToService();
                    }
                } catch (RemoteException e) {
                    Log.i(TAG, "Service exception");
                }
            }
        });
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
