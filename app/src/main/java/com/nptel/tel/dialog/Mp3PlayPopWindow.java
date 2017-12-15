package com.nptel.tel.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nptel.tel.R;

/**
 * 播放录音
 */
public class Mp3PlayPopWindow extends PopupWindow {
    private View conentView;
    private TextView msg;//
    private View bgView;
    private ImageView img;
    private Button okBtn;
    private TextView time;
    private int imgLong=100;

    public Mp3PlayPopWindow(Context context, String mp3Path, int longTime) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.dialog_mp3_play, null);
        msg = (TextView) conentView.findViewById(R.id.dialog_mp3_play_msg);
        bgView = conentView.findViewById(R.id.dialog_mp3_play_bg);
        img = (ImageView) conentView.findViewById(R.id.dialog_mp3_play_recording_icon);
        okBtn = (Button) conentView.findViewById(R.id.dialog_mp3_play_btn);
        time = (TextView) conentView.findViewById(R.id.dialog_mp3_play_time);
        time.setText(longTime + " '");
        msg.setText("通话时长：" + longTime + "秒");
        if (longTime > 30) {
            int b = longTime / 30;
            imgLong = b * imgLong;
        }
        if (imgLong < 100) {
            imgLong = 100;
        }
        if (imgLong > 300) {
            imgLong = 300;
        }
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(imgLong, 29);
        img.setLayoutParams(params);
        // 设置SelectPicPopupWindow的View
        this.setContentView(conentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.MATCH_PARENT);
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
        bgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopupWindow();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissPopupWindow();
            }
        });
    }

    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.CENTER, 0, 0);
        } else {
            this.dismiss();
        }
    }

    public void dismissPopupWindow() {
        if (this.isShowing()) {
            this.dismiss();
        }
    }
}
