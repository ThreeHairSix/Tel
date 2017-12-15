package com.nptel.tel.activity;

import android.app.Application;

import com.nptel.tel.activity.BaseActivity.PhoneListener;

import cn.com.geartech.gcordsdk.GcordSDK;

public class TelApplication extends Application {
    private static TelApplication instance;
    private static PhoneListener listener;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        GcordSDK.getInstance().initSDK(instance, "41531356@qq.com", "68A899E6-AA6E-40DA-91A3-BD8CBB39D013");
    }

    public static PhoneListener getPhoneListener() {
        if (listener == null) {
            listener = new PhoneListener();
        }
        return listener;
    }

    public static TelApplication getInstance() {
        return instance;
    }
}
