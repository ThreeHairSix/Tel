package com.nptel.tel.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.activity.LookForPeopleActivity;
import com.nptel.tel.db.util.ContactsPeoplesInfo;

import java.util.ArrayList;

/**
 * 通讯录
 */
public class ContactsListViewAdapter extends BaseAdapter implements SectionIndexer {
    private ArrayList<ContactsPeoplesInfo> list;
    private Context context;

    public ContactsListViewAdapter(Context context, ArrayList<ContactsPeoplesInfo> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int arg0, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.listview_contacts_item, null);
            holder.name = (TextView) convertView
                    .findViewById(R.id.listview_contacts_item_name);
            holder.tvLetter = (TextView) convertView.findViewById(R.id.listview_contacts_item_letter);
            holder.phoneNum = (TextView) convertView.findViewById(R.id.listview_contacts_item_phone_num);
            holder.dialImg = (ImageView) convertView.findViewById(R.id.listview_contacts_item_dial_img);
            holder.SMSlImg = (ImageView) convertView.findViewById(R.id.listview_contacts_item_SMS_img);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // 根据position获取分类的首字母的char ascii值
        int section = getSectionForPosition(arg0);
        // 如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
        if (arg0 == getPositionForSection(section)) {
            holder.tvLetter.setVisibility(View.VISIBLE);
            holder.tvLetter.setText(list.get(arg0).sortLetters);
        } else {
            holder.tvLetter.setVisibility(View.GONE);
        }
        holder.phoneNum.setText(list.get(arg0).phoneNum);
        holder.name.setText(list.get(arg0).name);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("tel_num", list.get(arg0).phoneNum);
                intent.setClass(context, LookForPeopleActivity.class);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    class ViewHolder {
        private TextView tvLetter;
        private TextView name;
        private TextView phoneNum;
        private ImageView dialImg;
        private ImageView SMSlImg;
    }

    @Override
    public Object[] getSections() {
        return null;
    }

    /*
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = list.get(i).sortLetters;
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 根据ListView的当前位置获取分类的首字母的char ascii值
     */
    @Override
    public int getSectionForPosition(int position) {
        return list.get(position).sortLetters.charAt(0);
    }
}
//5530 9230