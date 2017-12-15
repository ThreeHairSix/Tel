package com.nptel.tel.activity.BaseActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.nptel.tel.activity.TelApplication;

import cn.com.geartech.gcordsdk.GcordSDK;
import cn.com.geartech.gcordsdk.PhoneAPI;

public class BaseActivity extends Activity {
    protected Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        context = BaseActivity.this;
        GcordSDK.getInstance().getPhoneAPI().addPhoneEventListener(TelApplication.getPhoneListener());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GcordSDK.getInstance().getPhoneAPI().removePhoneEventListener(TelApplication.getPhoneListener());
    }

    protected void goOtherActivity(Class<?> cls, Intent intent) {
        if (cls == null) {
            return;
        }
        intent.setClass(getApplicationContext(), cls);
        startActivity(intent);
    }

    protected void goOtherActivity(Class<?> cls) {
        if (cls == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), cls);
        startActivity(intent);
    }

    protected void goOtherActivityForResult(Class<?> cls, int state) {
        if (cls == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), cls);
        startActivityForResult(intent, state);
    }

    protected void goOtherActivityForResult(Class<?> cls, int state, Intent intent) {
        intent.setClass(getApplicationContext(), cls);
        startActivityForResult(intent, state);

    }

    protected void showToast(String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
