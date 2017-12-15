package com.nptel.tel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.fragment.SmsRecordFragment;

import java.util.ArrayList;

/**
 * 查看考勤適配
 *
 * @author Administrator
 */
public class SMSFragmentAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<SmsRecordFragment.DataMap> list;

    public SMSFragmentAdapter(Context context, ArrayList<SmsRecordFragment.DataMap> list) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
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
        ViewHolder holder = new ViewHolder();
        convertView = mInflater
                .inflate(R.layout.listview_sms_fragment, null);
        holder.nameTextView = (TextView) convertView
                .findViewById(R.id.listview_sms_fragment_name);
        holder.contTextView = (TextView) convertView
                .findViewById(R.id.listview_sms_fragment_cont);
        holder.timeTextView = (TextView) convertView
                .findViewById(R.id.listview_sms_fragment_time);

        holder.nameTextView.setText(list.get(position).name);
        holder.contTextView.setText(list.get(position).cont);
        holder.timeTextView.setText(list.get(position).time);
        return convertView;
    }

    class ViewHolder {
        protected TextView nameTextView;
        protected TextView contTextView;
        protected TextView timeTextView;
    }
}
