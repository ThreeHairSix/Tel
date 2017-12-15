package com.nptel.tel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.nptel.tel.util.DatePickDialogUtil;

/**
 * 添加联系人
 */

public class AddPeopleActivity extends BaseActivity {
    private RelativeLayout exitLayout;
    private TextView titleTextView;
    private EditText nameEdit;
    private EditText telEdit;
    private EditText companyEdit;
    private EditText positionEdit;
    private TextView birthdayTextView;
    private LinearLayout birthdayLayout;
    private EditText adressEdit;
    private EditText remarksEdit;
    private RadioGroup radioGroup;
    private RadioButton menRadioButton;
    private RadioButton womenRadioButton;
    private RadioButton companyRadioButton;
    private ContactsPeoplesInfo contactsPeoplesInfoinfo;
    private String telNum;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        telNum = getIntent().getStringExtra("tel_num");
        if (TextUtils.isEmpty(telNum)) {
            contactsPeoplesInfoinfo = new ContactsPeoplesInfo();
        } else {
            contactsPeoplesInfoinfo = ContactsPeoplesInfo.readFromPhoneNum(telNum);
        }
        titleTextView = (TextView) findViewById(R.id.title_text);
        birthdayLayout = (LinearLayout) findViewById(R.id.activity_add_people_birthday_layout);
        birthdayTextView = (TextView) findViewById(R.id.activity_add_people_birthday_textview);
        nameEdit = (EditText) findViewById(R.id.activity_add_people_name);
        telEdit = (EditText) findViewById(R.id.activity_add_people_tel);
        companyEdit = (EditText) findViewById(R.id.activity_add_people_company);
        positionEdit = (EditText) findViewById(R.id.activity_add_people_position);
        adressEdit = (EditText) findViewById(R.id.activity_add_people_address);
        remarksEdit = (EditText) findViewById(R.id.activity_add_people_remarks);
        radioGroup = (RadioGroup) findViewById(R.id.activity_add_people_radioGroupID);
        menRadioButton = (RadioButton) findViewById(R.id.activity_add_people_radiogroup_men);
        womenRadioButton = (RadioButton) findViewById(R.id.activity_add_people_radiogroup_women);
        companyRadioButton = (RadioButton) findViewById(R.id.activity_add_people_radiogroup_company);
        saveBtn = (Button) findViewById(R.id.activity_add_people_btn);
        titleTextView.setText("添加联系人");
        //设置监听
        radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
        exitLayout = (RelativeLayout) findViewById(R.id.title_exit_layout);
        exitLayout.setVisibility(View.VISIBLE);
        exitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        birthdayLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickDialogUtil datePicKDialog = new DatePickDialogUtil(
                        AddPeopleActivity.this);
                datePicKDialog.dateTimePicKDialog(birthdayTextView);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contactsPeoplesInfoinfo.name=nameEdit.getText().toString();
                contactsPeoplesInfoinfo.birthday=birthdayTextView.getText().toString();
                contactsPeoplesInfoinfo.company=companyEdit.getText().toString();
                contactsPeoplesInfoinfo.phoneNum=telEdit.getText().toString();
                contactsPeoplesInfoinfo.position=positionEdit.getText().toString();
                contactsPeoplesInfoinfo.adress=adressEdit.getText().toString();
                contactsPeoplesInfoinfo.remarks=remarksEdit.getText().toString();
                if (TextUtils.isEmpty(contactsPeoplesInfoinfo.name)){
                    showToast("请输入联系人姓名");
                    return;
                }
                if (TextUtils.isEmpty(contactsPeoplesInfoinfo.phoneNum)){
                    showToast("请输入联系人电话");
                    return;
                }
                ContactsPeoplesInfo.insert(contactsPeoplesInfoinfo);
                onBackPressed();
            }
        });
        setData();
    }

    private void setData() {
        nameEdit.setText(contactsPeoplesInfoinfo.name);
        birthdayTextView.setText(contactsPeoplesInfoinfo.birthday);
        companyEdit.setText(contactsPeoplesInfoinfo.company);
        telEdit.setText(contactsPeoplesInfoinfo.phoneNum);
        positionEdit.setText(contactsPeoplesInfoinfo.position);
        adressEdit.setText(contactsPeoplesInfoinfo.adress);
        remarksEdit.setText(contactsPeoplesInfoinfo.remarks);
        if (contactsPeoplesInfoinfo.sex.equals("男士")) {
            menRadioButton.setChecked(true);
        } else if (contactsPeoplesInfoinfo.sex.equals("女士")) {
            womenRadioButton.setChecked(true);
        } else if (contactsPeoplesInfoinfo.sex.equals("公司")) {
            companyRadioButton.setChecked(true);
        } else {
            contactsPeoplesInfoinfo.sex = "男士";
            menRadioButton.setChecked(true);
        }
    }

    public class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == menRadioButton.getId()) {
                contactsPeoplesInfoinfo.sex = "男士";
            } else if (checkedId == womenRadioButton.getId()) {
                contactsPeoplesInfoinfo.sex = "女士";
            } else if (checkedId == companyRadioButton.getId()) {
                contactsPeoplesInfoinfo.sex = "公司";
            }
        }
    }
}


