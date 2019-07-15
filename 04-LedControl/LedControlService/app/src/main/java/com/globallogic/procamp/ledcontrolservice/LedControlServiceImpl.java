package com.globallogic.procamp.ledcontrolservice;

import android.os.RemoteException;
import android.util.Log;

import vendor.gl.hardware.ledcontrol.V1_0.ILedControl;

public class LedControlServiceImpl extends ILedControlService.Stub {
    private static final String LOG_TAG = "LedControlServiceImpl";
    private ILedControl ledControl = null;

    @Override
    public void setLedState(int led, int state) throws RemoteException {
        Log.d(LOG_TAG, String.format("-> setLedState(%d, %d)", led, state));
        try {
            if (ledControl == null) {
                ledControl = ILedControl.getService(true);
            }
            ledControl.setLedState(led, state);
        } catch (Exception e) {
            Log.e(LOG_TAG, "call service failed");
        }
    }
}
