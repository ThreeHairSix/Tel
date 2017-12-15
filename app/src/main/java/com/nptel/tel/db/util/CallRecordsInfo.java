package com.nptel.tel.db.util;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.PrimaryKey.AssignType;
import com.litesuits.orm.db.annotation.Table;
import com.nptel.tel.db.NPOrmDBHelper;

import java.util.ArrayList;

/**
 * 通话记录
 */
@Table("call_records_info")
public class CallRecordsInfo {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("id")
    public long id = 0;
    @Column("state")
    public int state = 0;// 状态 0 呼入，1 呼出
    @Column("phoneNum")
    public String phoneNum = "";// 电话
    @Column("name")
    public String name = "";// 姓名
    @Column("time")
    public String time = "";// （呼入）呼出时间
    @Column("mp3Path")
    public String mp3Path = "";// 录音
    @Column("longTime")
    public int longTime = 0;// 录音时长

    public static void insert(ArrayList<CallRecordsInfo> list) {
        deleteAll();
        NPOrmDBHelper.dataBase().insert(list);
    }

    //获取所有通讯录
    public static synchronized ArrayList<CallRecordsInfo> read() {
        ArrayList<CallRecordsInfo> result = NPOrmDBHelper.dataBase().query(
                CallRecordsInfo.class);
        return result;
    }

    //获取指定电话的通讯录
    public static synchronized ArrayList<CallRecordsInfo> readFromPhoneNum(String phoneNum) {
        ArrayList<CallRecordsInfo> result = NPOrmDBHelper.dataBase().query(
                CallRecordsInfo.class);
        ArrayList<CallRecordsInfo> list = new ArrayList<>();
        if (result.isEmpty()) {
            return list;
        }
        for (CallRecordsInfo info : result) {
            if (info.phoneNum.equals(phoneNum)) {
                list.add(info);
            }
        }
        return list;
    }

    public static void deleteAll() {
        NPOrmDBHelper.dataBase().deleteAll(CallRecordsInfo.class);
    }
}
