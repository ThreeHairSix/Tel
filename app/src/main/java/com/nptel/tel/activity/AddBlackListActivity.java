package com.nptel.tel.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nptel.tel.R;

/**
 * 添加黑名单
 */

public class AddBlackListActivity extends Activity {
    private TextView titleTextView;
    private RadioGroup radioGroup;
    private RadioButton menRadioButton;
    private RadioButton womenRadioButton;
    private RadioButton companyRadioButton;
    private String telNum;
    private String sex = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        telNum = getIntent().getStringExtra("tel_num");
        titleTextView = (TextView) findViewById(R.id.title_text);
        radioGroup = (RadioGroup) findViewById(R.id.activity_add_people_radioGroupID);
        menRadioButton = (RadioButton) findViewById(R.id.activity_add_people_radiogroup_men);
        womenRadioButton = (RadioButton) findViewById(R.id.activity_add_people_radiogroup_women);
        companyRadioButton = (RadioButton) findViewById(R.id.activity_add_people_radiogroup_company);
        titleTextView.setText("添加联系人");
        //设置监听
        radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
        RelativeLayout exitLayout = (RelativeLayout) findViewById(R.id.title_exit_layout);
        exitLayout.setVisibility(View.VISIBLE);
        exitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if (checkedId == menRadioButton.getId()) {
                sex = "男士";
            } else if (checkedId == womenRadioButton.getId()) {
                sex = "女士";
            } else if (checkedId == companyRadioButton.getId()) {
                sex = "公司";
            }
        }
    }
}


