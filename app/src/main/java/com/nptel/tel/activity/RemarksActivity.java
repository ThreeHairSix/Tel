package com.nptel.tel.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.activity.BaseActivity.BaseActivity;
import com.nptel.tel.db.util.ContactsPeoplesInfo;
import com.nptel.tel.db.util.SMSStencilInfo;

import java.util.ArrayList;

/**
 * 快速备注
 * <p>
 * TODO 这个界面有问题
 * Created by 闹皮科技 on 2017/8/21.
 */

public class RemarksActivity extends BaseActivity {

    private TextView titleTextView;
    private String telNum;
    private ContactsPeoplesInfo contactsPeoplesInfoinfo;
    private Button btn;
    private RelativeLayout exitLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remarks);
        telNum = getIntent().getStringExtra("tel_num");
        contactsPeoplesInfoinfo = ContactsPeoplesInfo.readFromPhoneNum(telNum);
        titleTextView = (TextView) findViewById(R.id.title_text);
        exitLayout = (RelativeLayout) findViewById(R.id.title_exit_layout);
        btn = (Button) findViewById(R.id.activity_remarks_button);
        titleTextView.setText("快速备注");
        exitLayout.setVisibility(View.VISIBLE);
        exitLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }
}
