package com.nptel.tel.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.activity.BaseActivity.BaseFragmentActivity;
import com.nptel.tel.adapter.MyFragmentPagerAdapter;
import com.nptel.tel.fragment.DialFragment;
import com.nptel.tel.fragment.ServiceAppointmentFragment;
import com.nptel.tel.fragment.SetUpFragment;
import com.nptel.tel.fragment.SmartDialFragment;
import com.nptel.tel.fragment.SmsRecordFragment;

import java.util.ArrayList;

public class MainActivity extends BaseFragmentActivity {
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private MyFragmentPagerAdapter fragmentPagerAdapter;
    private RelativeLayout dialLayout;
    private View dialLine;
    private TextView dialTextView;
    private RelativeLayout smartDialLayout;
    private View smartDialLine;
    private TextView smartDialTextView;
    private RelativeLayout serviceAppointmentLayout;
    private View serviceAppointmentLine;
    private TextView serviceAppointmentTextView;
    private RelativeLayout smsRecordLayout;
    private View smsRecordLine;
    private TextView smsRecordTextView;
    private RelativeLayout setUpLayout;
    private View setUpLine;
    private TextView setUpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = (ViewPager) findViewById(R.id.main_activity_viewpager);
        dialLayout = (RelativeLayout) findViewById(R.id.menu_dial_layout);
        dialLine = findViewById(R.id.menu_dial_line);
        dialTextView = (TextView) findViewById(R.id.menu_dial_textview);
        smartDialLayout = (RelativeLayout) findViewById(R.id.menu_smart_dial_layout);
        smartDialLine = findViewById(R.id.menu_smart_dial_line);
        smartDialTextView = (TextView) findViewById(R.id.menu_smart_dial_textview);
        serviceAppointmentLayout = (RelativeLayout) findViewById(R.id.menu_service_appointment_layout);
        serviceAppointmentLine = findViewById(R.id.menu_service_appointment_line);
        serviceAppointmentTextView = (TextView) findViewById(R.id.menu_service_appointment_textview);
        smsRecordLayout = (RelativeLayout) findViewById(R.id.menu_SMS_record_layout);
        smsRecordLine = findViewById(R.id.menu_SMS_record_line);
        smsRecordTextView = (TextView) findViewById(R.id.menu_SMS_record_textview);
        setUpLayout = (RelativeLayout) findViewById(R.id.menu_set_up_layout);
        setUpLine = findViewById(R.id.menu_set_up_line);
        setUpTextView = (TextView) findViewById(R.id.menu_set_up_textview);
        fragments = new ArrayList<>();
        fragments.add(new DialFragment());
        fragments.add(new SmartDialFragment());
        fragments.add(new ServiceAppointmentFragment());
        fragments.add(new SmsRecordFragment());
        fragments.add(new SetUpFragment());
        fragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragments);
        dialLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(0);
            }
        });
        smartDialLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(1);
            }
        });
        serviceAppointmentLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(2);
            }
        });
        smsRecordLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(3);
            }
        });
        setUpLayout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewPager.setCurrentItem(4);
            }
        });
        viewPager.setAdapter(fragmentPagerAdapter);
        setButtonState(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setButtonState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setBtnBackground() {
        dialLine.setBackgroundResource(R.color.color_d9d9d9);
        smartDialLine.setBackgroundResource(R.color.color_d9d9d9);
        serviceAppointmentLine.setBackgroundResource(R.color.color_d9d9d9);
        smsRecordLine.setBackgroundResource(R.color.color_d9d9d9);
        setUpLine.setBackgroundResource(R.color.color_d9d9d9);
        dialTextView.setTextColor(getResources().getColor(R.color.color_333333));
        smartDialTextView.setTextColor(getResources().getColor(R.color.color_333333));
        serviceAppointmentTextView.setTextColor(getResources().getColor(R.color.color_333333));
        smsRecordTextView.setTextColor(getResources().getColor(R.color.color_333333));
        setUpTextView.setTextColor(getResources().getColor(R.color.color_333333));
    }

    private void setButtonState(int positon) {
        setBtnBackground();
        switch (positon) {
            case 0:
                dialLine.setBackgroundResource(R.color.color_007aff);
                dialTextView.setTextColor(getResources().getColor(R.color.color_007aff));
                break;
            case 1:
                smartDialLine.setBackgroundResource(R.color.color_007aff);
                smartDialTextView.setTextColor(getResources().getColor(R.color.color_007aff));
                break;
            case 2:
                serviceAppointmentLine.setBackgroundResource(R.color.color_007aff);
                serviceAppointmentTextView.setTextColor(getResources().getColor(R.color.color_007aff));
                break;
            case 3:
                smsRecordLine.setBackgroundResource(R.color.color_007aff);
                smsRecordTextView.setTextColor(getResources().getColor(R.color.color_007aff));
                break;
            case 4:
                setUpLine.setBackgroundResource(R.color.color_007aff);
                setUpTextView.setTextColor(getResources().getColor(R.color.color_007aff));
                break;
        }
    }
}


