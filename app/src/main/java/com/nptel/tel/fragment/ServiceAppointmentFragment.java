package com.nptel.tel.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.adapter.SMSFragmentAdapter;

import java.util.ArrayList;

/**
 * 服务预约
 */
public class ServiceAppointmentFragment extends BaseFragment {
    private TextView titleText;

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service_appointment, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        titleText.setText("服务预约");
        return view;
    }
}
