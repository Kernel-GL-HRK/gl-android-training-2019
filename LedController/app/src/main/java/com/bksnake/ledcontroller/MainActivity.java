package com.bksnake.ledcontroller;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.os.Bundle;
import android.widget.Switch;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "LedControl::" + MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "-> onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // switch led 1
        Switch ledControl1 = (Switch) findViewById(R.id.led1);
        ledControl1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            private final String LOG_TAG_LISTENER = LOG_TAG + " LED 1 Listener";

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged()");
                if (b) {
                    Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn On");
                } else {

                    Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn Off");
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
                if (b) {
                    Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn On");
                } else {

                    Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn Off");
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
                if (b) {
                    Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn On");
                } else {

                    Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn Off");
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
                if (b) {
                    Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn On");
                } else {

                    Log.d(LOG_TAG_LISTENER, "-> onCheckedChanged() Turn Off");
                }
            }
        });
    }
}
