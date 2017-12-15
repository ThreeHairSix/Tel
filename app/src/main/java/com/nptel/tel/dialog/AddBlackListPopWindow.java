package com.nptel.tel.dialog;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.db.util.CallRecordsInfo;

/**
 * 添加黑名单dialog
 */
public class AddBlackListPopWindow extends PopupWindow {
    private View conentView;
    private TextView msg;//新建预约
    private View bgView;
    private LinearLayout checkBoxLayout;
    private CheckBox checkBox;
    private Button okBtn;

    public AddBlackListPopWindow(Context context, String phoneNum, final IClickItemListener listener) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        conentView = inflater.inflate(R.layout.dialog_add_black_list, null);
        msg = (TextView) conentView.findViewById(R.id.dialog_add_black_list_msg);
        bgView = conentView.findViewById(R.id.dialog_add_black_list_bg);
        checkBoxLayout = (LinearLayout) conentView.findViewById(R.id.dialog_add_black_list_check_box_layout);
        checkBox = (CheckBox) conentView.findViewById(R.id.dialog_add_black_list_check_box);
        okBtn = (Button) conentView.findViewById(R.id.dialog_add_black_list_btn);
        msg.setText("将" + phoneNum + "加到用户黑名单");
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
        checkBoxLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setOnItemClick(checkBox.isChecked());
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

    public interface IClickItemListener {
        public void setOnItemClick(boolean isCheck);

    }
}
