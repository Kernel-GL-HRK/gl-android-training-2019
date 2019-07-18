package vendor.gl.getter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GetterActivity extends Activity {

    private final String TAG = "GetterActivity";
    private Button buttonConnect;
    private Button buttonGet;
    private TextView textValue;

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
                bindService();
            }
        });

        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onGetClick()");

                int value = serviceGetValue();
                textValue.setText(new Integer(value).toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    private void bindService() {
        Log.i(TAG, "bindService()");

    }

    private int serviceGetValue() {
        Log.i(TAG, "getValue()");
        return 3;
    }
}
