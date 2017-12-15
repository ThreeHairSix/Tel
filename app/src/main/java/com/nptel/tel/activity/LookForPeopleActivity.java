package com.nptel.tel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.activity.BaseActivity.BaseActivity;
import com.nptel.tel.db.util.ContactsPeoplesInfo;

/**
 * 查看联系人
 */

public class LookForPeopleActivity extends BaseActivity {
    private TextView titleTextView;
    private TextView nameTextView;
    private TextView telTextView;
    private TextView companyTextView;
    private TextView positionTextView;
    private TextView birthdayTextView;
    private TextView adressTextView;
    private TextView remarksTextView;
    private ContactsPeoplesInfo contactsPeoplesInfoinfo;
    private String telNum;
    private Button editBtn;
    private Button delBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_look_for_people);
        telNum = getIntent().getStringExtra("tel_num");
        contactsPeoplesInfoinfo = ContactsPeoplesInfo.readFromPhoneNum(telNum);
        titleTextView = (TextView) findViewById(R.id.title_text);
        nameTextView = (TextView) findViewById(R.id.activity_look_for_people_name);
        telTextView = (TextView) findViewById(R.id.activity_look_for_people_tel);
        companyTextView = (TextView) findViewById(R.id.activity_look_for_people_company);
        positionTextView = (TextView) findViewById(R.id.activity_look_for_people_position);
        birthdayTextView = (TextView) findViewById(R.id.activity_look_for_people_brithday);
        adressTextView = (TextView) findViewById(R.id.activity_look_for_people_address);
        remarksTextView = (TextView) findViewById(R.id.activity_look_for_people_remarks);
        RelativeLayout exitLayout = (RelativeLayout) findViewById(R.id.title_exit_layout);
        editBtn = (Button) findViewById(R.id.activity_look_for_people_edit_btn);
        delBtn = (Button) findViewById(R.id.activity_look_for_people_del_btn);
        titleTextView.setText("查看联系人");
        nameTextView.setText(contactsPeoplesInfoinfo.name);
        birthdayTextView.setText(contactsPeoplesInfoinfo.birthday);
        companyTextView.setText(contactsPeoplesInfoinfo.company);
        telTextView.setText(contactsPeoplesInfoinfo.phoneNum);
        positionTextView.setText(contactsPeoplesInfoinfo.position);
        adressTextView.setText(contactsPeoplesInfoinfo.adress);
        remarksTextView.setText(contactsPeoplesInfoinfo.remarks);
        exitLayout.setVisibility(View.VISIBLE);
        exitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("tel_num", contactsPeoplesInfoinfo.phoneNum);
                goOtherActivity(AddPeopleActivity.class, intent);
                onBackPressed();
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactsPeoplesInfo.delete(contactsPeoplesInfoinfo);
                onBackPressed();
            }
        });
    }
}


