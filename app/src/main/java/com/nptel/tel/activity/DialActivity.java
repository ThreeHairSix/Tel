package com.nptel.tel.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.activity.BaseActivity.BaseActivity;
import com.nptel.tel.db.util.ContactsPeoplesInfo;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 通话界面
 */

public class DialActivity extends BaseActivity {
    private TextView nameTextView;
    private TextView telNumTextView;
    private TextView timeTextView;
    private TextView telStateTextView;
    private TextView recordingTextView;
    private TextView muteTextView;
    private TextView keyBoardTextView;
    private TextView handsFreeTextView;
    private CheckBox muteCheckBox;
    private CheckBox keyBoardCheckBox;
    private CheckBox handsFreeCheckBox;
    private LinearLayout keyBoardLayout;
    private TextView zeroTextView;
    private TextView oneTextView;
    private TextView twoTextView;
    private TextView threeTextView;
    private TextView fourTextView;
    private TextView fiveTextView;
    private TextView sixTextView;
    private TextView sevenTextView;
    private TextView eightTextView;
    private TextView nineTextView;
    private TextView xingTextView;
    private TextView jingTextView;

    private String telNum;
    private int time = 0;
    private Button hangsUpBtn;
    private Button smsBtn;
    private Button serviceApponintmentBtn;
    private Button remarksBtn;
    private ContactsPeoplesInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dial);
        telNum = getIntent().getStringExtra("tel_num");
        info = ContactsPeoplesInfo.readFromPhoneNum(telNum);
        telNumTextView = (TextView) findViewById(R.id.activity_dial_tel_num);
        timeTextView = (TextView) findViewById(R.id.activity_dial_tel_time);
        nameTextView = (TextView) findViewById(R.id.activity_dial_name);
        hangsUpBtn = (Button) findViewById(R.id.activity_dial_hangs_up);
        smsBtn = (Button) findViewById(R.id.activity_dial_sms);
        serviceApponintmentBtn = (Button) findViewById(R.id.activity_dial_service_apponintment);
        remarksBtn = (Button) findViewById(R.id.activity_dial_remarks);
        telStateTextView = (TextView) findViewById(R.id.activity_dial_tel_state);
        recordingTextView = (TextView) findViewById(R.id.activity_dial_recording_textview);
        muteTextView = (TextView) findViewById(R.id.activity_dial_mute_textview);
        keyBoardTextView = (TextView) findViewById(R.id.activity_dial_keyboard_textview);
        handsFreeTextView = (TextView) findViewById(R.id.activity_dial_hands_free_textview);
        muteCheckBox = (CheckBox) findViewById(R.id.activity_dial_mute_check_box);
        keyBoardCheckBox = (CheckBox) findViewById(R.id.activity_dial_keyboard_check_box);
        handsFreeCheckBox = (CheckBox) findViewById(R.id.activity_dial_hands_free_check_box);
        keyBoardLayout = (LinearLayout) findViewById(R.id.activity_dial_keyboard_layout);
        zeroTextView = (TextView) findViewById(R.id.activity_dial_keyboard_zero);
        oneTextView = (TextView) findViewById(R.id.activity_dial_keyboard_one);
        twoTextView = (TextView) findViewById(R.id.activity_dial_keyboard_two);
        threeTextView = (TextView) findViewById(R.id.activity_dial_keyboard_three);
        fourTextView = (TextView) findViewById(R.id.activity_dial_keyboard_four);
        fiveTextView = (TextView) findViewById(R.id.activity_dial_keyboard_five);
        sixTextView = (TextView) findViewById(R.id.activity_dial_keyboard_six);
        sevenTextView = (TextView) findViewById(R.id.activity_dial_keyboard_seven);
        eightTextView = (TextView) findViewById(R.id.activity_dial_keyboard_eight);
        nineTextView = (TextView) findViewById(R.id.activity_dial_keyboard_nine);
        xingTextView = (TextView) findViewById(R.id.activity_dial_keyboard_xing);
        jingTextView = (TextView) findViewById(R.id.activity_dial_keyboard_jing);

        timer.schedule(task, 1000, 1000); // 1s后执行task,经过1s再次执行
        telNumTextView.setText(info.phoneNum);
        nameTextView.setText(info.name);
        timeTextView.setText("00:00");
        hangsUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("tel_num", telNum);
                goOtherActivity(SMSActivity.class, intent);
            }
        });
        serviceApponintmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        remarksBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("tel_num", telNum);
                goOtherActivity(RemarksActivity.class, intent);
            }
        });
        muteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    muteTextView.setTextColor(getResources().getColor(R.color.color_1aad19));
                } else {
                    muteTextView.setTextColor(getResources().getColor(R.color.color_999999));
                }
            }
        });
        keyBoardCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    keyBoardTextView.setTextColor(getResources().getColor(R.color.color_1aad19));
                    keyBoardLayout.setVisibility(View.VISIBLE);
                } else {
                    keyBoardTextView.setTextColor(getResources().getColor(R.color.color_999999));
                    keyBoardLayout.setVisibility(View.GONE);
                }
            }
        });
        handsFreeCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    handsFreeTextView.setTextColor(getResources().getColor(R.color.color_1aad19));
                } else {
                    handsFreeTextView.setTextColor(getResources().getColor(R.color.color_999999));
                }
            }
        });
        oneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("1");
            }
        });
        twoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("2");
            }
        });
        threeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("3");
            }
        });
        fourTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("4");
            }
        });
        fiveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("5");
            }
        });
        sixTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("6");
            }
        });
        sevenTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("7");
            }
        });
        eightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("8");
            }
        });
        nineTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("9");
            }
        });
        xingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("*");
            }
        });
        zeroTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("0");
            }
        });
        jingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("#");
            }
        });
    }

    String date = "";
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            time++;
            int second = time % 60;
            int min = (time / 60) % 60;
            date = getTime(min) + ":" + getTime(second);
            handler.sendEmptyMessage(1);
        }
    };

    private String getTime(int time) {
        if (time < 10) {
            return "0" + time;
        } else {
            return time + "";
        }
    }

    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            timeTextView.setText(date);
        }
    };
}


