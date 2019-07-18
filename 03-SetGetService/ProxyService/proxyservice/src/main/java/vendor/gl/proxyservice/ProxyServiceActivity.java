package vendor.gl.proxyservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ProxyServiceActivity extends Activity {
    private final String TAG = "ProxyServiceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proxy_service);

        Log.i(TAG, "Starting service");

        startService(new Intent(this, ProxyService.class));

        finish();
    }
}
