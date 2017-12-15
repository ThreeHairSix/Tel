package com.nptel.tel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.nptel.tel.dialog.AddBlackListPopWindow;
import com.nptel.tel.dialog.CallRecordsPopWindow;
import com.nptel.tel.dialog.Mp3PlayPopWindow;

import java.util.ArrayList;

/**
 * 通话记录
 * <p>
 * TODO 查看录音有问题 时候要删除，之前只要确定按钮 这个记录是集合统计，录音不止一条
 */
public class CallRecordsActivity extends BaseActivity {
    private RelativeLayout backLayout;
    private TextView titleTextView;
    private ListView listView;
    public ArrayList<CallRecordsInfo> list;
    public MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_records);
        titleTextView = (TextView) findViewById(R.id.title_text);
        backLayout = (RelativeLayout) findViewById(R.id.title_exit_layout);
        listView = (ListView) findViewById(R.id.activity_call_records_listview);
        titleTextView.setText("通话记录");
        list = new ArrayList<CallRecordsInfo>();
        adapter = new MyAdapter();
        backLayout.setVisibility(View.VISIBLE);
        listView.setAdapter(adapter);
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        aaa();
    }

    private void aaa() {
        CallRecordsInfo map1 = new CallRecordsInfo();
        map1.phoneNum = "0123456789012";
        map1.name = "欧阳和惠晓";
        map1.time = "2017-08-01";
        map1.state = 0;
        list.add(map1);
        CallRecordsInfo map2 = new CallRecordsInfo();
        map2.phoneNum = "18812345678";
        map2.name = "惠晓和";
        map2.time = "昨天";
        map2.state = 1;
        list.add(map2);
        CallRecordsInfo map3 = new CallRecordsInfo();
        map3.phoneNum = "18812345678";
        map3.name = "惠晓和";
        map3.time = "2017-08-01";
        map3.state = 0;
        list.add(map3);
        CallRecordsInfo map4 = new CallRecordsInfo();
        map4.phoneNum = "18812345678";
        map4.name = "惠晓和";
        map4.time = "12:00:03";
        map4.state = 1;
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
            final ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.listview_call_records_item, null);
                holder.stateIcon = (ImageView) convertView.findViewById(R.id.listview_call_records_item_state);
                holder.numTextView = (TextView) convertView.findViewById(R.id.listview_call_records_item_num);
                holder.nameTextView = (TextView) convertView.findViewById(R.id.listview_call_records_item_name);
                holder.dateTextView = (TextView) convertView.findViewById(R.id.listview_call_records_item_date);
                holder.phoneNumTextView = (TextView) convertView.findViewById(R.id.listview_call_records_item_tel_num);
                holder.callIcon = (ImageView) convertView.findViewById(R.id.listview_call_records_item_call_icon);
                holder.SMSIcon = (ImageView) convertView.findViewById(R.id.listview_call_records_item_SMS_icon);
                holder.setUpIcon = (ImageView) convertView.findViewById(R.id.listview_call_records_item_set_up_icon);
                holder.layout = (LinearLayout) convertView.findViewById(R.id.listview_call_records_item_layout);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            int index = position % 2;
            if (index == 0) {
                holder.layout.setBackgroundColor(getResources().getColor(R.color.color_ffffff));
            } else {
                holder.layout.setBackgroundColor(getResources().getColor(R.color.color_f3f5f7));
            }
            if (list.get(position).state == 0) {
                holder.stateIcon.setImageResource(R.drawable.listview_call_records_item_state_icon0);
            } else {
                holder.stateIcon.setImageResource(R.drawable.listview_call_records_item_state_icon1);
            }
            holder.numTextView.setText("(" + 123 + ")");
            holder.nameTextView.setText(list.get(position).name);
            holder.phoneNumTextView.setText(list.get(position).phoneNum);
            holder.dateTextView.setText(list.get(position).time);
            holder.callIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("tel_num", list.get(position).phoneNum);
                    goOtherActivity(DialActivity.class, intent);
                }
            });
            holder.SMSIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("tel_num", list.get(position).phoneNum);
                    goOtherActivity(SMSActivity.class, intent);
                }
            });
            holder.setUpIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(holder.setUpIcon, list.get(position));
                }
            });
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("tel_num", list.get(position).phoneNum);
                    goOtherActivity(CallRecordsDetailActivity.class, intent);
                }
            });
            return convertView;
        }

        class ViewHolder {
            private ImageView stateIcon;
            private TextView numTextView;
            private TextView nameTextView;
            private TextView dateTextView;
            private TextView phoneNumTextView;
            private ImageView callIcon;
            private ImageView SMSIcon;
            private ImageView setUpIcon;
            private LinearLayout layout;
        }
    }

    private void showDialog(final View view, final CallRecordsInfo info) {
        CallRecordsPopWindow popWindow = new CallRecordsPopWindow(context, info, new CallRecordsPopWindow.IClickItemListener() {
            @Override
            public void setOnItemClick(int index) {
                switch (index) {
                    case 1://新建预约
                        break;
                    case 2://查看录音
                        showMp3PlayDialog(view, info);
                        break;
                    case 3://添加通讯录
                        Intent intent = new Intent();
                        intent.putExtra("tel_num", info.phoneNum);
                        goOtherActivity(AddPeopleActivity.class, intent);
                        break;
                    case 4://加入黑名单
                        showBlckListDialog(view, info);
                        break;
                }
            }
        });
        popWindow.showPopupWindow(view);
    }

    private void showBlckListDialog(View view, final CallRecordsInfo info) {
        AddBlackListPopWindow popWindow = new AddBlackListPopWindow(context, info.phoneNum, new AddBlackListPopWindow.IClickItemListener() {
            @Override
            public void setOnItemClick(boolean isCheck) {
                showToast(isCheck + "");
            }
        });
        popWindow.showPopupWindow(view);
    }

    private void showMp3PlayDialog(View view, final CallRecordsInfo info) {
        Mp3PlayPopWindow popWindow = new Mp3PlayPopWindow(context, info.mp3Path, info.longTime);
        popWindow.showPopupWindow(view);
    }
}
