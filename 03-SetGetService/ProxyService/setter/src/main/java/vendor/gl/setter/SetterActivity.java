package vendor.gl.setter;

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
import android.widget.EditText;
import vendor.gl.proxyservice.IProxyService;

public class SetterActivity extends Activity {

    private final String TAG = "SetterActivity";
    private Button buttonConnect;
    private Button buttonSet;
    private EditText textValue;
    private ServiceConnection serviceConnection;
    private Boolean serviceConnected;
    private IProxyService proxyService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");

        setContentView(R.layout.activity_setter);
        buttonConnect = (Button)findViewById(R.id.service_connect);
        buttonSet = (Button)findViewById(R.id.button_set);
        textValue = (EditText) findViewById(R.id.text_value);

        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onConnectClick()");
                bindToProxyService();
            }
        });

        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onSetClick()");
                int value = Integer.parseInt(textValue.getText().toString());

                setValue(value);
            }
        });

        serviceConnected = false;
        buttonConnect.setEnabled(true);
        buttonSet.setEnabled(false);
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
                buttonSet.setEnabled(true);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected()");
                serviceConnected = false;
                proxyService = null;
                buttonConnect.setEnabled(true);
                buttonSet.setEnabled(false);
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

    private void setValue(int value) {
        Log.i(TAG, "setValue(" + value + ")");
        try {
            proxyService.setValue(value);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
