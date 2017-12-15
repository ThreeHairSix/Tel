package com.nptel.tel.activity.BaseActivity;

import cn.com.geartech.gcordsdk.PhoneAPI;

/**
 * Created by 闹皮科技 on 2017/12/11.
 */

public class PhoneListener extends PhoneAPI.PhoneEventListener {
    @Override
    public void onPickUp(PhoneAPI.PICKUP_STATE pickup_state) {

    }

    @Override
    public void onInComingCall() {

    }

    @Override
    public void onRingEnd() {

    }

    @Override
    public void onPhoneNumberReceived(String s) {

    }

    @Override
    public void onSwitchPhoneState(PhoneAPI.PICKUP_STATE pickup_state) {

    }

    @Override
    public void onHangOff() {

    }
}
