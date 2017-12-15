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

public class SmsRecordFragment extends BaseFragment {
    private TextView titleText;
    private SMSFragmentAdapter adapter;
    private ArrayList<DataMap> list;
    private ListView listView;

    @Override
    protected void lazyLoad() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sms_record, container, false);
        titleText = (TextView) view.findViewById(R.id.title_text);
        listView = (ListView) view.findViewById(R.id.fragment_sms_record_list);
        titleText.setText("短信记录");
        list = new ArrayList<>();
        DataMap map1 = new DataMap();
        map1.cont = "阿萨德发送开绿灯飞机拉萨觉得弗兰克撒娇所拉动房价雷克萨江东父老接撒拉开大飞机拉萨觉得法律界阿斯兰地块附近 拉萨觉得弗兰克撒娇的法兰克接撒拉开大飞机拉萨等级分类考试的风景拉萨等级分类撒娇的法兰克家阿斯利康大飞机拉萨卡觉得弗兰克撒娇的洒落的房价雷克萨大飞机拉萨解放立刻撒旦解放雷克萨德房价雷克萨的积分所拉动房价雷克萨的积分拉萨觉得分绿卡时间的李开复接撒拉开大飞机阿斯利康大解放立刻阿斯利康的房价雷克萨大解放立刻撒娇的分拉萨觉得分卢卡斯的积分阿隆索卡觉得分拉萨卡的积分雷克萨江东父老阿隆索卡的积分雷克萨的积分卢卡斯大飞机拉萨觉得分接撒拉开大飞机拉萨大解放立刻撒娇的李开复接撒拉开大解放立刻撒娇的法兰克接撒的浪费空间撒到了疯狂阿斯兰的房价雷克萨大解放立刻撒娇的浪费卡视角的李开复家阿斯利康的积分卡拉萨觉得分了阿斯兰的房价绿卡时间的分拉萨觉得看来飞机阿隆索的积分卡拉萨觉得分绿卡时间的李开复家阿斯兰的分洒落的房价雷克萨的积分雷克萨的积分雷克萨大解放立刻撒娇的分拉萨觉得分拉萨觉得浪费卡视角阿道夫快乐萨拉地块附近雷克萨德房价雷克萨的积分卢卡斯觉得分拉萨觉得分拉萨的房价雷克萨的积分卢卡斯的积分卢卡斯觉得分卡拉萨觉得弗兰克撒娇的分阿斯利康的房价雷克萨的积分雷克萨的积分雷克萨的积分雷克萨的积分拉萨等级分类所拉动房价雷克萨大飞机拉萨等级分类撒娇的分卡拉萨大分卡拉萨的积分卡拉萨大分卡拉萨等级";
        map1.name = "张三";
        map1.time = "2017-08-01 12:00:00";
        list.add(map1);
        DataMap map2 = new DataMap();
        map2.cont = "阿萨德发送开绿灯飞机拉萨觉得弗兰克撒娇所拉动房价雷克萨江东父老接撒拉开大飞机拉萨觉得法律界阿斯兰地块附近 拉萨觉得弗兰克撒娇的法兰克接撒拉开大飞机拉萨等级分类考试的风景拉萨等级分类撒娇的法兰克家阿斯利康大飞机拉萨卡觉得弗兰克撒娇的洒落的房价雷克萨大飞机拉萨解放立刻撒旦解放雷克萨德房价雷克萨的积分所拉动房价雷克萨的积分拉萨觉得分绿卡时间的李开复接撒拉开大飞机阿斯利康大解放立刻阿斯利康的房价雷克萨大解放立刻撒娇的分拉萨觉得分卢卡斯的积分阿隆索卡觉得分拉萨卡的积分雷克萨江东父老阿隆索卡的积分雷克萨的积分卢卡斯大飞机拉萨觉得分接撒拉开大飞机拉萨大解放立刻撒娇的李开复接撒拉开大解放立刻撒娇的法兰克接撒的浪费空间撒到了疯狂阿斯兰的房价雷克萨大解放立刻撒娇的浪费卡视角的李开复家阿斯利康的积分卡拉萨觉得分了阿斯兰的房价绿卡时间的分拉萨觉得看来飞机阿隆索的积分卡拉萨觉得分绿卡时间的李开复家阿斯兰的分洒落的房价雷克萨的积分雷克萨的积分雷克萨大解放立刻撒娇的分拉萨觉得分拉萨觉得浪费卡视角阿道夫快乐萨拉地块附近雷克萨德房价雷克萨的积分卢卡斯觉得分拉萨觉得分拉萨的房价雷克萨的积分卢卡斯的积分卢卡斯觉得分卡拉萨觉得弗兰克撒娇的分阿斯利康的房价雷克萨的积分雷克萨的积分雷克萨的积分雷克萨的积分拉萨等级分类所拉动房价雷克萨大飞机拉萨等级分类撒娇的分卡拉萨大分卡拉萨的积分卡拉萨大分卡拉萨等级";
        map2.name = "李四";
        map2.time = "2017-08-01 12:00:00";
        list.add(map2);
        DataMap map3 = new DataMap();
        map3.cont = "阿萨德发送开绿灯飞机拉萨觉得弗兰克撒娇所拉动房价雷克萨江东父老接撒拉开大飞机拉萨觉得法律界阿斯兰地块附近 拉萨觉得弗兰克撒娇的法兰克接撒拉开大飞机拉萨等级分类考试的风景拉萨等级分类撒娇的法兰克家阿斯利康大飞机拉萨卡觉得弗兰克撒娇的洒落的房价雷克萨大飞机拉萨解放立刻撒旦解放雷克萨德房价雷克萨的积分所拉动房价雷克萨的积分拉萨觉得分绿卡时间的李开复接撒拉开大飞机阿斯利康大解放立刻阿斯利康的房价雷克萨大解放立刻撒娇的分拉萨觉得分卢卡斯的积分阿隆索卡觉得分拉萨卡的积分雷克萨江东父老阿隆索卡的积分雷克萨的积分卢卡斯大飞机拉萨觉得分接撒拉开大飞机拉萨大解放立刻撒娇的李开复接撒拉开大解放立刻撒娇的法兰克接撒的浪费空间撒到了疯狂阿斯兰的房价雷克萨大解放立刻撒娇的浪费卡视角的李开复家阿斯利康的积分卡拉萨觉得分了阿斯兰的房价绿卡时间的分拉萨觉得看来飞机阿隆索的积分卡拉萨觉得分绿卡时间的李开复家阿斯兰的分洒落的房价雷克萨的积分雷克萨的积分雷克萨大解放立刻撒娇的分拉萨觉得分拉萨觉得浪费卡视角阿道夫快乐萨拉地块附近雷克萨德房价雷克萨的积分卢卡斯觉得分拉萨觉得分拉萨的房价雷克萨的积分卢卡斯的积分卢卡斯觉得分卡拉萨觉得弗兰克撒娇的分阿斯利康的房价雷克萨的积分雷克萨的积分雷克萨的积分雷克萨的积分拉萨等级分类所拉动房价雷克萨大飞机拉萨等级分类撒娇的分卡拉萨大分卡拉萨的积分卡拉萨大分卡拉萨等级";
        map3.name = "王五";
        map3.time = "2017-08-01 12:00:00";
        list.add(map3);
        adapter = new SMSFragmentAdapter(getContext(), list);
        listView.setAdapter(adapter);
        return view;
    }

    public class DataMap {
        public String cont;
        public String name;
        public String time;
    }
}
