package com.globallogic.procamp.ledcontrol;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.globallogic.procamp.ledcontrolservice.ILedControlService;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LedControl";
    private static final String SERVICE_PKG = "com.globallogic.procamp.ledcontrolservice";
    private static final String SERVICE = "com.globallogic.procamp.ledcontrolservice.LedControlService";
    private ILedControlService mService = null;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(LOG_TAG, "-> onServiceConnected()");
            mService = ILedControlService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(LOG_TAG, "-> onServiceDisconnected()");
            mService = null;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-> onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setAction(SERVICE);
        intent.setPackage(SERVICE_PKG);

        try {
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            Log.e(LOG_TAG, "bind service failed");
        }

        CheckBox ledControl1 = (CheckBox) findViewById(R.id.greenLed1);
        ledControl1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(LOG_TAG, "-> onCheckedChanged() LED 2");
                try {
                    if (b) {
                        mService.setLedState(ILedControlService.GREEN_LED_1, ILedControlService.STATE_ON);
                    } else {
                        mService.setLedState(ILedControlService.GREEN_LED_1, ILedControlService.STATE_OFF);
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "call service failed");
                }
            }
        });

        CheckBox ledControl2 = (CheckBox) findViewById(R.id.greenLed2);
        ledControl2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(LOG_TAG, "-> onCheckedChanged() LED 2");
                try {
                    if (b) {
                        mService.setLedState(ILedControlService.GREEN_LED_2, ILedControlService.STATE_ON);
                    } else {
                        mService.setLedState(ILedControlService.GREEN_LED_2, ILedControlService.STATE_OFF);
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "call service failed");
                }
            }
        });

        CheckBox ledControl3 = (CheckBox) findViewById(R.id.greenLed3);
        ledControl3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(LOG_TAG, "-> onCheckedChanged() LED 3");
                try {
                    if (b) {
                        mService.setLedState(ILedControlService.GREEN_LED_3, ILedControlService.STATE_ON);
                    } else {
                        mService.setLedState(ILedControlService.GREEN_LED_3, ILedControlService.STATE_OFF);
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "call service failed");
                }
            }
        });

        CheckBox ledControl4 = (CheckBox) findViewById(R.id.greenLed4);
        ledControl4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(LOG_TAG, "-> onCheckedChanged() LED 4");
                try {
                    if (b) {
                        mService.setLedState(ILedControlService.GREEN_LED_4, ILedControlService.STATE_ON);
                    } else {
                        mService.setLedState(ILedControlService.GREEN_LED_4, ILedControlService.STATE_OFF);
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "call service failed");
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "-> onDestroy()");

        unbindService(mConnection);
        super.onDestroy();
    }

}
