package vendor.gl.setter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetterActivity extends Activity {

    private final String TAG = "SetterActivity";
    private Button buttonConnect;
    private Button buttonSet;
    private EditText textValue;

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
                bindService();
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy()");
    }

    private void bindService() {
        Log.i(TAG, "bindService()");

    }

    private void setValue(int value) {
        Log.i(TAG, "setValue(" + new Integer(value).toString() + ")");

    }
}
