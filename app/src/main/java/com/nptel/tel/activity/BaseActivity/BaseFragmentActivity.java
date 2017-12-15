package com.nptel.tel.activity.BaseActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.nptel.tel.activity.TelApplication;

import cn.com.geartech.gcordsdk.GcordSDK;

public class BaseFragmentActivity extends FragmentActivity {
    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        context = BaseFragmentActivity.this;
        GcordSDK.getInstance().getPhoneAPI().addPhoneEventListener(TelApplication.getPhoneListener());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GcordSDK.getInstance().getPhoneAPI().removePhoneEventListener(TelApplication.getPhoneListener());
    }

    public void goOtherActivity(Class<?> cls, Intent intent) {
        intent.setClass(getApplicationContext(), cls);
        startActivity(intent);
    }

    public void goOtherActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), cls);
        startActivity(intent);
    }

    public void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
