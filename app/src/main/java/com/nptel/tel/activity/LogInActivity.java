package com.nptel.tel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nptel.tel.R;
import com.nptel.tel.activity.BaseActivity.BaseActivity;
import com.nptel.tel.util.Tools;

/**
 * 登录
 */
public class LogInActivity extends BaseActivity {
    private EditText userNameEdit;
    private EditText passWordEdit;
    private Button okBtn;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userNameEdit = (EditText) findViewById(R.id.login_phone_edit);
        passWordEdit = (EditText) findViewById(R.id.login_password_edit);
        okBtn = (Button) findViewById(R.id.login_button);

        okBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                userName = userNameEdit.getText().toString();
                String password = passWordEdit.getText().toString();
                if (TextUtils.isEmpty(userName)) {
                    showToast("请输入帐号");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    showToast("请输入密码");
                    return;
                }
                Tools.setSharedPreferences("userName", userName);
                okBtn.setEnabled(false);
            }
        });
        userName = Tools.getSharedPreferences("userName");
//        if (!TextUtils.isEmpty(tel)) {
//            userNameEdit.setText(tel);
//        userNameEdit.setText(Tools.getSharedPreferences("userName"));
//        }

        Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void loginPost(final String userName, String password) {
//        LogInHttp.logIn(userName, password, new LogInHttp.IHttpListener() {
//            @Override
//            public void success() {
//                progressDialog.dismiss();
//                okBtn.setEnabled(true);
//                Tools.setSharedPreferences("UserName", userName);
//                goOtherActivity(MainActivity.class);
//                finish();
//            }
//
//            @Override
//            public void fail(String msg) {
//                progressDialog.dismiss();
//                okBtn.setEnabled(true);
//                showToast(msg);
//            }
//        });
    }
}
