package ua.org.procamp.simpleservice;

import android.os.RemoteException;
import android.util.Log;

public class SimpleServiceImpl extends ISimpleService.Stub {
    private static final String LOG_TAG = "SimpleServiceImp";
    private String mValue = "";

    @Override
    public void setValue(String value) throws RemoteException {
        Log.d(LOG_TAG, "-> setValue()");
        mValue = value;
    }

    @Override
    public String getValue() throws RemoteException {
        Log.d(LOG_TAG, "-> getValue()");
        return mValue;
    }
}
