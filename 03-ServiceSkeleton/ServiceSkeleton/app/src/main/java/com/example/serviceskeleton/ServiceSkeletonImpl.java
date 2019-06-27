package com.example.serviceskeleton;

public class ServiceSkeletonImpl extends IServiceSkeleton.Stub {
    private int value  = 0;

    public void setValue(int newValue){
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
