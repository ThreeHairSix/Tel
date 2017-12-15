package com.nptel.tel.activity;

import android.app.Activity;
import android.content.Context;
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
 * Created by 闹皮科技 on 2017/8/21.
 */

public class SMSActivity extends BaseActivity {

    private TextView titleTextView;
    private TextView telNumTextView;
    private TextView nameTextView;
    private String telNum;
    private ContactsPeoplesInfo contactsPeoplesInfoinfo;
    private Button btn;
    private ListView listView;
    private ArrayList<SMSStencilInfo> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        telNum = getIntent().getStringExtra("tel_num");
        contactsPeoplesInfoinfo = ContactsPeoplesInfo.readFromPhoneNum(telNum);
        titleTextView = (TextView) findViewById(R.id.title_text);
        RelativeLayout exitLayout = (RelativeLayout) findViewById(R.id.title_exit_layout);
        telNumTextView = (TextView) findViewById(R.id.activity_sms_tel_num);
        nameTextView = (TextView) findViewById(R.id.activity_sms_name);
        btn = (Button) findViewById(R.id.activity_sms_button);
        listView = (ListView) findViewById(R.id.activity_sms_listview);
        list = new ArrayList<SMSStencilInfo>();
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        titleTextView.setText("发短信");
        telNumTextView.setText(contactsPeoplesInfoinfo.phoneNum);
        nameTextView.setText(contactsPeoplesInfoinfo.name);
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
                SMSStencilInfo info = null;
                for (SMSStencilInfo info1 : list) {
                    if (info1.isCheck) {
                        info = info1;
                    }
                }
                if (info == null) {
                    showToast("请选择短信内容");
                    return;
                }
                showToast("短信内容：" + info.title);
                onBackPressed();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (SMSStencilInfo info : list) {
                    info.isCheck = false;
                }
                list.get(position).isCheck = true;
                adapter.notifyDataSetChanged();
            }
        });
        aaa();
    }

    private void aaa() {
        SMSStencilInfo info1 = new SMSStencilInfo();
        info1.isCheck = true;
        info1.title = "短信挂机模版1";
        list.add(info1);
        SMSStencilInfo info2 = new SMSStencilInfo();
        info2.isCheck = false;
        info2.title = "短信挂机模版2";
        list.add(info2);
        SMSStencilInfo info3 = new SMSStencilInfo();
        info3.isCheck = false;
        info3.title = "短信挂机模版3";
        list.add(info3);
        SMSStencilInfo info4 = new SMSStencilInfo();
        info4.isCheck = false;
        info4.title = "短信挂机模版4";
        list.add(info4);
        SMSStencilInfo info5 = new SMSStencilInfo();
        info5.isCheck = false;
        info5.title = "短信挂机模版5";
        list.add(info5);
        SMSStencilInfo info6 = new SMSStencilInfo();
        info6.isCheck = false;
        info6.title = "短信挂机模版6";
        list.add(info6);
        SMSStencilInfo info7 = new SMSStencilInfo();
        info7.isCheck = false;
        info7.title = "短信挂机模版7";
        list.add(info7);
        SMSStencilInfo info8 = new SMSStencilInfo();
        info8.isCheck = false;
        info8.title = "短信挂机模版8";
        list.add(info8);
        SMSStencilInfo info9 = new SMSStencilInfo();
        info9.isCheck = false;
        info9.title = "短信挂机模版9";
        list.add(info9);
        SMSStencilInfo info10 = new SMSStencilInfo();
        info10.isCheck = false;
        info10.title = "短信挂机模版10";
        list.add(info10);
        SMSStencilInfo info11 = new SMSStencilInfo();
        info11.isCheck = false;
        info11.title = "短信挂机模版11";
        list.add(info11);
        SMSStencilInfo info12 = new SMSStencilInfo();
        info12.isCheck = false;
        info12.title = "短信挂机模版12";
        list.add(info12);
        SMSStencilInfo info13 = new SMSStencilInfo();
        info13.isCheck = false;
        info13.title = "短信挂机模版13";
        list.add(info13);
        SMSStencilInfo info14 = new SMSStencilInfo();
        info14.isCheck = false;
        info14.title = "短信挂机模版14";
        list.add(info14);
        SMSStencilInfo info15 = new SMSStencilInfo();
        info15.isCheck = false;
        info15.title = "短信挂机模版15";
        list.add(info15);
        adapter.notifyDataSetChanged();
    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater mInflater;

        public MyAdapter() {
            mInflater = LayoutInflater.from(context);

        }

        public int getCount() {
            return list.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.listview_sms_stencil_item, null);
                holder.icon = (ImageView) convertView.findViewById(R.id.listview_sms_stencil_item_icon);
                holder.titleTextView = (TextView) convertView.findViewById(R.id.listview_sms_stencil_item_title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.titleTextView.setText(list.get(position).title);
            if (list.get(position).isCheck) {
                holder.icon.setVisibility(View.VISIBLE);
            } else {
                holder.icon.setVisibility(View.GONE);
            }
            return convertView;
        }

        class ViewHolder {
            private TextView titleTextView;
            private ImageView icon;
        }

    }

}
