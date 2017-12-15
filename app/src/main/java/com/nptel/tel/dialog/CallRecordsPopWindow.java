package com.nptel.tel.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.db.util.CallRecordsInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * 通话记录dialog
 */
public class CallRecordsPopWindow extends PopupWindow {
    private View conentView;
    private TextView serviceAppointment;//新建预约
    private TextView lookForMp3;//查看录音
    private TextView addToContacts;//添加通讯录
    private TextView addToBlacklist;//添加黑名单

    public CallRecordsPopWindow(Context context, CallRecordsInfo info, final IClickItemListener listener) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.dialog_call_records, null);
        serviceAppointment = (TextView) conentView.findViewById(R.id.dialog_call_records_service_appointment);
        lookForMp3 = (TextView) conentView.findViewById(R.id.dialog_call_records_look_for_mp3);
        addToContacts = (TextView) conentView.findViewById(R.id.dialog_call_records_add_to_contacts);
        addToBlacklist = (TextView) conentView.findViewById(R.id.dialog_call_records_add_to_blacklist);
        if (info.name.equals("未知")) {
            addToContacts.setVisibility(View.VISIBLE);
        } else {
            addToContacts.setVisibility(View.GONE);
        }
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        serviceAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setOnItemClick(1);
                dismissPopupWindow();
            }
        });
        lookForMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setOnItemClick(2);
                dismissPopupWindow();
            }
        });
        addToContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setOnItemClick(3);
                dismissPopupWindow();
            }
        });
        addToBlacklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setOnItemClick(4);
                dismissPopupWindow();
            }
        });
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

    public void dismissPopupWindow() {
        if (this.isShowing()) {
            this.dismiss();
        }
    }

    public interface IClickItemListener {
        public void setOnItemClick(int index);

    }
}
