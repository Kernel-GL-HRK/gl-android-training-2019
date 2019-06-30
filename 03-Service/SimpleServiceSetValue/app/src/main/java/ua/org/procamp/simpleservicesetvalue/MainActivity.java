package ua.org.procamp.simpleservicesetvalue;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ua.org.procamp.simpleservice.ISimpleService;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "SimpleServiceSetValue";
    private static final String SERVICE_PKG = "ua.org.procamp.simpleservice";
    private static final String SERVICE = "ua.org.procamp.simpleservice.SimpleService";
    private ISimpleService mService = null;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(LOG_TAG, "-> onServiceConnected()");
            mService = ISimpleService.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(LOG_TAG, "-> onServiceDisconnected()");
            mService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "-> onCreate()");
        setContentView(R.layout.activity_main);

        Intent intent = new Intent();
        intent.setAction(SERVICE);
        intent.setPackage(SERVICE_PKG);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        Button set = (Button)findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView text = (TextView)findViewById(R.id.text);
                String s = text.getText().toString();
                try {
                    mService.setValue(s);
                } catch (Exception e) {

                }
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "-> onDestroy()");

        unbindService(mConnection);
    }
}
