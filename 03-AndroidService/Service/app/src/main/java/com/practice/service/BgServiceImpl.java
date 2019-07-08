package com.practice.service;

import android.util.Log;

public class BgServiceImpl extends IBgService.Stub {
    private static final String LOG_TAG = "BgServiceImpl";
    private static int mValue = 0;

    public void setValue(int newValue) {
        Log.d(LOG_TAG, "-> setValue(" + newValue + ")");
        mValue = newValue;
    }

    public int getValue() {
        Log.d(LOG_TAG, "-> getValue()");
        return mValue;
    }
}
