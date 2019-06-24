package com.androsov.testservice;

import android.os.IBinder;
import android.os.RemoteException;

public class TestServiceImpl extends com.androsov.testservice.ITestService.Stub {

    private int Value = 999;

    @Override
    public void setValue(int value) throws RemoteException {

        Value = value;
    }

    @Override
    public int GetValue() throws RemoteException {
        return Value;
    }
}
