package vendor.gl.getter;

import android.app.Activity;
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

import vendor.gl.proxyservice.IProxyService;

public class GetterActivity extends Activity {

    private final String TAG = "GetterActivity";
    private Button buttonConnect;
    private Button buttonGet;
    private TextView textValue;
    private ServiceConnection serviceConnection;
    private Boolean serviceConnected;
    private IProxyService proxyService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");

        setContentView(R.layout.activity_getter);
        buttonConnect = (Button)findViewById(R.id.service_connect);
        buttonGet = (Button)findViewById(R.id.button_get);
        textValue = (TextView) findViewById(R.id.text_value);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onConnectClick()");
                bindToProxyService();
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onGetClick()");

                int value = getValue();
                textValue.setText(new Integer(value).toString());
            }
        });

        serviceConnected = false;
        buttonConnect.setEnabled(true);
        buttonGet.setEnabled(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
        unbindFromProxyService();
    }

    private void bindToProxyService() {
        Log.i(TAG, "bindService()");
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.i(TAG, "onServiceConnected()");
                serviceConnected = true;
                proxyService = IProxyService.Stub.asInterface(service);
                buttonConnect.setEnabled(false);
                buttonGet.setEnabled(true);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected()");
                serviceConnected = false;
                proxyService = null;
                buttonConnect.setEnabled(true);
                buttonGet.setEnabled(false);
            }
        };
        Intent psi = new Intent("vendor.gl.proxyservice.ProxyService");
        psi.setPackage("vendor.gl.proxyservice");
        bindService(psi, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindFromProxyService() {
        if (serviceConnected == true) {
            unbindService(serviceConnection);
            serviceConnection = null;
            serviceConnected = false;
        }
    }

    private int getValue() {
        int value = 0;
        try {
            value = proxyService.getValue();
            Log.i(TAG, "getValue() -> " + value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return value;
    }
}
