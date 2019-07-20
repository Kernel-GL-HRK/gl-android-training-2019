package com.bksnake.ledservice;

import android.os.RemoteException;
import android.util.Log;

public class LedControlServiceImpl extends ILedControllerInterface.Stub {
    private static final String LOG_TAG = LedControlServiceImpl.class.getSimpleName();

    public void setLedState(int led, int state) throws RemoteException {
        Log.d(LOG_TAG, String.format("-> setLedState(%d, %d)", led, state));
    }

    public int getLedState(int led) {
        Log.d(LOG_TAG, String.format("-> getLedState(%d, %d)", led));
        return 0;
    }
}
