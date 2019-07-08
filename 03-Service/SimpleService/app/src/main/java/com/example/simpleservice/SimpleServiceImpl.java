package com.example.simpleservice;

import android.util.Log;

import com.example.simpleservice.ISimpleService;

public class SimpleServiceImpl extends ISimpleService.Stub {
    private static final String LOG_TAG = "SimpleServiceImpl";
    private int mValue = 0;

    public void setValue(int value) {
        Log.d(LOG_TAG, "##> setValue()");
        mValue = value;
    }

    public int getValue() {
        Log.d(LOG_TAG, "##> getValue()");
        return mValue;
    }
}
