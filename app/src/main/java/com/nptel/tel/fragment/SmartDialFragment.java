package com.nptel.tel.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nptel.tel.R;

/**
 * 智能拨号
 */
public class SmartDialFragment extends BaseFragment {
    private TextView titleText;

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_smart_dial, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        titleText.setText("智能拨号");
        return view;
    }


}
