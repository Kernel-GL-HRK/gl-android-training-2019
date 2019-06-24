package com.glandroid2019.myservice;
import android.util.Log;
import com.glandroid2019.myservice.IMyService;

public class MyServiceImpl extends IMyService.Stub{
    private static int mValue = 0;
    private static final String LOG_TAG = "xan_mySrvc";

    public void setValue (int newValue) {
        Log.i(LOG_TAG, "onSetValue() " + newValue);
        mValue = newValue;
    }

    public int getValue () {
        Log.i(LOG_TAG, "onGetValue() " + mValue);
        return mValue;
    }

}
