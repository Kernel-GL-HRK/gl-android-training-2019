package com.bksnake.ledservice;

import android.os.RemoteException;
import android.util.Log;

import vendor.bksnake.smartledcontroller.V1_0.ILedController;

public class LedControlServiceImpl extends ILedControllerInterface.Stub {
    private static final String LOG_TAG = LedControlServiceImpl.class.getSimpleName();
    private ILedController m_ILedControler = null;

    @Override
    public void setLedState(int led, int state) throws RemoteException {
        Log.d(LOG_TAG, String.format("-> setLedState(%d, %d)", led, state));
        try {
            if (m_ILedControler == null) {
                m_ILedControler = ILedController.getService(true);
                m_ILedControler.initilizeLedController();
            }
            m_ILedControler.setLedState((byte)led, (byte)state);
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception service HAL:" + e.getMessage());
        }
    }

    public int getLedState(int led) {
        Log.d(LOG_TAG, String.format("-> getLedState(%d, %d)", led));
        return 0;
    }

    public void terminate() {
        Log.d(LOG_TAG, "-> terminate()");
        try {
            if (m_ILedControler != null) {
                m_ILedControler.terminateLedController();
                m_ILedControler = null;
            }
        } catch (Exception e) {
            Log.e(LOG_TAG, "Exception service HAL:" + e.getMessage());
        }
    }
}
