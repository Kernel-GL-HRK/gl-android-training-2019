package com.bksnake.ledcontroller;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Switch;
import android.widget.CompoundButton;

import com.bksnake.ledservice.ILedControllerInterface;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LedControl::" + MainActivity.class.getSimpleName();

    private static final String PACKAGE = "com.bksnake.ledservice";
    private static final String SERVICE_NAME = PACKAGE + ".LedControlService";

    private class LedState {
      public static final int TURN_OFF = 0;
      public static final int TURN_ON = 1;
    }

    private class Leds {
        public static final int LED_GREEN_1 = 1;
        public static final int LED_GREEN_2 = 2;
        public static final int LED_GREEN_3 = 3;
        public static final int LED_GREEN_4 = 4;
    }


    private ILedControllerInterface mService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-> onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        connectToService();

        // switch led 1
        Switch ledControl1 = (Switch) findViewById(R.id.led1);
        ledControl1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            private final String LOG_TAG_LISTENER = LOG_TAG + " LED 1 Listener";

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged()");
                try {
                    if (b) {
                        Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn On");
                        mService.setLedState(Leds.LED_GREEN_1, LedState.TURN_ON);
                    } else {
                        Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn Off");
                        mService.setLedState(Leds.LED_GREEN_1, LedState.TURN_OFF);
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Call service failed" + e.getMessage());
                }
            }
        });

        // switch led 2
        Switch ledControl2 = (Switch) findViewById(R.id.led2);
        ledControl2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            private final String LOG_TAG_LISTENER = LOG_TAG + " LED 2 Listener";

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged()");
                try {
                    if (b) {
                        Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn On");
                        mService.setLedState(Leds.LED_GREEN_2, LedState.TURN_ON);
                    } else {
                        Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn Off");
                        mService.setLedState(Leds.LED_GREEN_2, LedState.TURN_OFF);
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Call service failed" + e.getMessage());
                }
            }
        });

        // switch led 3
        Switch ledControl3 = (Switch) findViewById(R.id.led3);
        ledControl3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            private final String LOG_TAG_LISTENER = LOG_TAG + " LED 3 Listener";

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged()");
                try {
                    if (b) {
                        Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn On");
                        mService.setLedState(Leds.LED_GREEN_3, LedState.TURN_ON);
                    } else {
                        Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn Off");
                        mService.setLedState(Leds.LED_GREEN_3, LedState.TURN_OFF);
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Call service failed" + e.getMessage());
                }
            }
        });

        // switch led 4
        Switch ledControl4 = (Switch) findViewById(R.id.led4);
        ledControl4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            private final String LOG_TAG_LISTENER = LOG_TAG + " LED 4 Listener";

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged()");
                try {
                    if (b) {
                        Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn On");
                        mService.setLedState(Leds.LED_GREEN_4, LedState.TURN_ON);
                    } else {
                        Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn Off");
                        mService.setLedState(Leds.LED_GREEN_4, LedState.TURN_OFF);
                    }
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Call service failed" + e.getMessage());
                }
            }
        });
    }

    private void connectToService(){
        Log.d(LOG_TAG, "-> connectToService()");
        Intent intent = new Intent(SERVICE_NAME);
        intent.setPackage(PACKAGE);
        try {
            bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
            Log.e(LOG_TAG, " Bind service failed");
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(LOG_TAG, " -> onServiceConnected()");
            mService = ILedControllerInterface.Stub.asInterface(service);
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.d(LOG_TAG, " -> onServiceDisconnected()");
            mService = null;
        }
    };
}
