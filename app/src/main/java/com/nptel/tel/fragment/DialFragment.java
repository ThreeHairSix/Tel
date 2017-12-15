package com.nptel.tel.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nptel.tel.R;
import com.nptel.tel.activity.CallRecordsActivity;
import com.nptel.tel.activity.ContactsActivity;
import com.nptel.tel.activity.DialActivity;

/**
 * 拨号
 */
public class DialFragment extends Fragment {
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
    private ImageView clearIcon;
    private TextView phoneNumTextView;
    private LinearLayout telLayout;
    private LinearLayout callRecordsLayout;
    private LinearLayout contactsLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dial, container, false);
        zeroTextView = (TextView) view.findViewById(R.id.fragment_dial_zero);
        oneTextView = (TextView) view.findViewById(R.id.fragment_dial_one);
        twoTextView = (TextView) view.findViewById(R.id.fragment_dial_two);
        threeTextView = (TextView) view.findViewById(R.id.fragment_dial_three);
        fourTextView = (TextView) view.findViewById(R.id.fragment_dial_four);
        fiveTextView = (TextView) view.findViewById(R.id.fragment_dial_five);
        sixTextView = (TextView) view.findViewById(R.id.fragment_dial_six);
        sevenTextView = (TextView) view.findViewById(R.id.fragment_dial_seven);
        eightTextView = (TextView) view.findViewById(R.id.fragment_dial_eight);
        nineTextView = (TextView) view.findViewById(R.id.fragment_dial_nine);
        xingTextView = (TextView) view.findViewById(R.id.fragment_dial_xing);
        jingTextView = (TextView) view.findViewById(R.id.fragment_dial_jing);
        clearIcon = (ImageView) view.findViewById(R.id.fragment_dial_clear_icon);
        phoneNumTextView = (TextView) view.findViewById(R.id.fragment_dial_phone_num);
        telLayout=(LinearLayout)view.findViewById(R.id.fragment_dial_dial_layout);
        callRecordsLayout=(LinearLayout)view.findViewById(R.id.fragment_dial_call_records_layout);
        contactsLayout=(LinearLayout)view.findViewById(R.id.fragment_dial_contacts_layout);
        oneTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + 1);
            }
        });
        twoTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + 2);
            }
        });
        threeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + 3);
            }
        });
        fourTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + 4);
            }
        });
        fiveTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + 5);
            }
        });
        sixTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + 6);
            }
        });
        sevenTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + 7);
            }
        });
        eightTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + 8);
            }
        });
        nineTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + 9);
            }
        });
        xingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + "*");
            }
        });
        zeroTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + "0");
            }
        });
        jingTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumTextView.setText(phoneNumTextView.getText().toString() + "#");
            }
        });
        phoneNumTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //这个方法被调用，说明在s字符串中，从start位置开始的count个字符即将被长度为after的新文本所取代。在这个方法里面改变s，会报错。
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //这个方法被调用，说明在s字符串中，从start位置开始的count个字符刚刚取代了长度为before的旧文本。在这个方法里面改变s，会报错。
            }

            @Override
            public void afterTextChanged(Editable s) {
                //这个方法被调用，那么说明s字符串的某个地方已经被改变。
                if (TextUtils.isEmpty(s.toString())){
                    clearIcon.setVisibility(View.GONE);
                }else{
                    clearIcon.setVisibility(View.VISIBLE);
                }
            }
        });
        clearIcon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                phoneNumTextView.setText("");
                return true;
            }
        });
        clearIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=phoneNumTextView.getText().toString();
                if (TextUtils.isEmpty(str)){
                    return;
                }
                phoneNumTextView.setText(str.substring(0,str.length()-1));
            }
        });
        callRecordsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), CallRecordsActivity.class);
                startActivity(intent);
            }
        });
        contactsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getContext(), ContactsActivity.class);
                startActivity(intent);
            }
        });
        telLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tleNum = phoneNumTextView.getText().toString();
                if (TextUtils.isEmpty(tleNum)) {
                    Toast.makeText(getContext(), "请输入电话号", Toast.LENGTH_SHORT).show();
                    return;
                }
                phoneNumTextView.setText("");
                Intent intent = new Intent();
                intent.setClass(getContext(), DialActivity.class);
                intent.putExtra("tel_num", tleNum);
                startActivity(intent);
            }
        });
        return view;
    }

}
