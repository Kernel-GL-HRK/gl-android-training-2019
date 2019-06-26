package com.example.valueservice;
import android.util.Log;

public class ValueServiceImpl extends com.example.valueservice.IValueService.Stub {
    private static final String LOG_TAG = "ValueServiceImpl";
    private int  value = 0;


    public void setValue(int newValue) {
        Log.d(LOG_TAG, "-> setValue(): newValue = " + newValue);
        value = newValue;
    }

    public int getValue() {
        Log.d(LOG_TAG, "-> getValue(): will return " + value);
        return value;
    }
}
