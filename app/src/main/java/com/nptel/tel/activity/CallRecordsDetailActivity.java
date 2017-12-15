package com.nptel.tel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.activity.BaseActivity.BaseActivity;
import com.nptel.tel.db.util.CallRecordsInfo;
import com.nptel.tel.db.util.ContactsPeoplesInfo;
import com.nptel.tel.dialog.AddBlackListPopWindow;

import java.util.ArrayList;

/**
 * 通话记录详情
 */
public class CallRecordsDetailActivity extends BaseActivity {
    private RelativeLayout backLayout;
    private TextView titleTextView;
    private TextView nameTextView;
    private TextView telNumTextView;
    private TextView attributionTextView;
    private ImageView SMSImg;
    private ListView listView;
    private ContactsPeoplesInfo contactsPeoplesInfoinfo;//通讯录
    private LinearLayout addLayout;
    private LinearLayout editLayout;
    private LinearLayout blackListLayout;
    private String telNum;
    private ArrayList<CallRecordsInfo> list;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_records_detail);
        telNum = getIntent().getStringExtra("tel_num");
        contactsPeoplesInfoinfo = ContactsPeoplesInfo.readFromPhoneNum(telNum);
        titleTextView = (TextView) findViewById(R.id.title_text);
        nameTextView = (TextView) findViewById(R.id.activity_call_records_detail_name);
        telNumTextView = (TextView) findViewById(R.id.activity_call_records_detail_tel_num);
        attributionTextView = (TextView) findViewById(R.id.activity_call_records_detail_adress);
        SMSImg = (ImageView) findViewById(R.id.activity_call_records_detail_sms_img);
        backLayout = (RelativeLayout) findViewById(R.id.title_exit_layout);
        listView = (ListView) findViewById(R.id.activity_call_records_detail_listview);
        addLayout = (LinearLayout) findViewById(R.id.activity_call_records_detail_add_pople);
        editLayout = (LinearLayout) findViewById(R.id.activity_call_records_detail_edit_pople);
        blackListLayout = (LinearLayout) findViewById(R.id.activity_call_records_detail_add_black_list);
        titleTextView.setText("通话记录详情");
        nameTextView.setText(contactsPeoplesInfoinfo.name);
        telNumTextView.setText(contactsPeoplesInfoinfo.phoneNum);
        attributionTextView.setText(contactsPeoplesInfoinfo.attribution);
        if (contactsPeoplesInfoinfo.name.equals("未知")) {
            editLayout.setVisibility(View.GONE);
            addLayout.setVisibility(View.VISIBLE);
        } else {
            editLayout.setVisibility(View.VISIBLE);
            addLayout.setVisibility(View.GONE);
        }
        backLayout.setVisibility(View.VISIBLE);
        list = CallRecordsInfo.readFromPhoneNum(telNum);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        SMSImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("tel_num", contactsPeoplesInfoinfo.phoneNum);
                goOtherActivity(SMSActivity.class, intent);
            }
        });
        addLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        editLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        blackListLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBlackListPopWindow popWindow = new AddBlackListPopWindow(context, contactsPeoplesInfoinfo.phoneNum, new AddBlackListPopWindow.IClickItemListener() {
                    @Override
                    public void setOnItemClick(boolean isCheck) {
                        showToast(isCheck + "");
                    }
                });
                popWindow.showPopupWindow(blackListLayout);
            }
        });
        aaa();
    }

    private void aaa() {
        CallRecordsInfo map1 = new CallRecordsInfo();
        map1.phoneNum = "0123456789012";
        map1.name = "欧阳和惠晓";
        map1.time = "2017-08-01 12:12:12";
        map1.state = 0;
        map1.longTime=10;
        list.add(map1);
        CallRecordsInfo map2 = new CallRecordsInfo();
        map2.phoneNum = "18812345678";
        map2.name = "惠晓和";
        map2.time = "昨天";
        map2.state = 1;
        map2.longTime=100;
        list.add(map2);
        CallRecordsInfo map3 = new CallRecordsInfo();
        map3.phoneNum = "18812345678";
        map3.name = "惠晓和";
        map3.time = "2017-08-01";
        map3.state = 0;
        map3.longTime=400;
        list.add(map3);
        CallRecordsInfo map4 = new CallRecordsInfo();
        map4.phoneNum = "18812345678";
        map4.name = "惠晓和";
        map4.time = "12:00:03";
        map4.state = 1;
        map4.longTime=125;
        list.add(map4);
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

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.listview_call_records_detail_item, null);
                holder.stateIcon = (ImageView) convertView.findViewById(R.id.listview_call_records_detail_item_state);
                holder.stateTextView = (TextView) convertView.findViewById(R.id.listview_call_records_detail_item_state_txt);
                holder.timeTextView = (TextView) convertView.findViewById(R.id.listview_call_records_detail_item_time);
                holder.time2TextView = (TextView) convertView.findViewById(R.id.listview_call_records_detail_item_time2);
                holder.dateTextView = (TextView) convertView.findViewById(R.id.listview_call_records_detail_item_date);
                holder.soundImg = (ImageView) convertView.findViewById(R.id.listview_call_records_detail_item_img);
                holder.delImg = (ImageView) convertView.findViewById(R.id.listview_call_records_detail_item_del_img);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (list.get(position).state == 0) {
                holder.stateIcon.setImageResource(R.drawable.in_icon);
                holder.stateTextView.setText("呼入");
            } else {
                holder.stateIcon.setImageResource(R.drawable.out_icon);
                holder.stateTextView.setText("呼出");
            }
            int imgLong = 100;
            if (list.get(position).longTime > 30) {
                int b = list.get(position).longTime / 30;
                imgLong = b * imgLong;
            }
            if (imgLong < 100) {
                imgLong = 100;
            }
            if (imgLong > 300) {
                imgLong = 300;
            }
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(imgLong, 29);
            holder.soundImg.setLayoutParams(params);
            holder.timeTextView.setText(list.get(position).longTime + "秒");
            holder.time2TextView.setText(list.get(position).longTime+" '");
            holder.dateTextView.setText(list.get(position).time);
            return convertView;
        }

        class ViewHolder {
            private ImageView stateIcon;
            private TextView stateTextView;
            private TextView timeTextView;
            private TextView time2TextView;
            private TextView dateTextView;
            private ImageView soundImg;
            private ImageView delImg;
        }
    }
}
