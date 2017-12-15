package com.nptel.tel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nptel.tel.R;
import com.nptel.tel.activity.BaseActivity.BaseActivity;
import com.nptel.tel.adapter.ContactsListViewAdapter;
import com.nptel.tel.db.util.ContactsPeoplesInfo;
import com.nptel.tel.util.CharacterParser;
import com.nptel.tel.util.PinyinComparator;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 通讯录
 */
public class ContactsActivity extends BaseActivity {
    private TextView titleTextView;
    private ListView listView;
    private RelativeLayout backLayout;
    private CharacterParser characterParser;
    private ContactsListViewAdapter adapter;
    private ArrayList<ContactsPeoplesInfo> list;
    private LinearLayout addPeopleLayout;
    private PinyinComparator pinyinComparator;
    private EditText serchEdit;
    private Button serchBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        titleTextView = (TextView) findViewById(R.id.title_text);
        backLayout = (RelativeLayout) findViewById(R.id.title_exit_layout);
        listView = (ListView) findViewById(R.id.activity_contacts_list);
        addPeopleLayout = (LinearLayout) findViewById(R.id.activity_contacts_add_people);
        serchEdit = (EditText) findViewById(R.id.activity_contacts_search_edit);
        serchBtn = (Button) findViewById(R.id.activity_contacts_search_btn);
        titleTextView.setText("通讯录");
        list = new ArrayList<>();
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        adapter = new ContactsListViewAdapter(context, list);
        listView.setAdapter(adapter);
        backLayout.setVisibility(View.VISIBLE);
        backLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        serchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData(serchEdit.getText().toString());
            }
        });
        addPeopleLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goOtherActivity(AddPeopleActivity.class);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        filledData();
    }

    private void filledData() {
        list.clear();
        list.addAll(ContactsPeoplesInfo.read());
        ArrayList<ContactsPeoplesInfo> mSortList = new ArrayList<ContactsPeoplesInfo>();
        for (int i = 0; i < list.size(); i++) {
            // 汉字转换成拼音
            String pinyin = characterParser.getSelling(list.get(i).name);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                list.get(i).sortLetters = sortString.toUpperCase();
            } else {
                list.get(i).sortLetters = "#";
            }
            mSortList.add(list.get(i));
        }
        list.clear();
        list.addAll(mSortList);
        Collections.sort(list, pinyinComparator);
        adapter.notifyDataSetChanged();
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param str
     */
    private void searchData(String str) {
        if (TextUtils.isEmpty(str)) {
            filledData();
            return;
        }
        if (list.isEmpty()) {
            list.addAll(ContactsPeoplesInfo.read());
        }
        ArrayList<ContactsPeoplesInfo> mSortList = new ArrayList<>();
        for (ContactsPeoplesInfo info : list) {
            String name = info.name;
            if (name.toUpperCase().indexOf(str.toUpperCase()) != -1) {
                mSortList.add(info);
            }
        }
        list.clear();
        list.addAll(mSortList);
        Collections.sort(list, pinyinComparator);
        adapter.notifyDataSetChanged();
    }
}
