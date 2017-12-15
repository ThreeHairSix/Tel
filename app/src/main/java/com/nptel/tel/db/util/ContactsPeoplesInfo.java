package com.nptel.tel.db.util;

import android.util.Log;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.PrimaryKey.AssignType;
import com.litesuits.orm.db.annotation.Table;
import com.nptel.tel.db.NPOrmDBHelper;

import java.util.ArrayList;

/**
 * 通讯录
 */
@Table("contacts_people_info")
public class ContactsPeoplesInfo {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("id")
    public long id = 0;
    @Column("phoneNum")
    public String phoneNum = "";// 电话
    @Column("name")
    public String name = "";// 姓名
    @Column("attribution")
    public String attribution = "";// 号码归属地
    @Column("company")
    public String company = "";// 公司
    @Column("position")
    public String position = "";//职位
    @Column("birthday")
    public String birthday = "";//生日
    @Column("adress")
    public String adress = "";//地址
    @Column("remarks")
    public String remarks = "";//备注
    @Column("sex")
    public String sex = "";// 性别或者公司（个人）
    public String sortLetters = "";//首字母

    public static void insert(ArrayList<ContactsPeoplesInfo> list) {
        NPOrmDBHelper.dataBase().insert(list);
    }

    public static void insert(ContactsPeoplesInfo info) {
        delete(info);
        NPOrmDBHelper.dataBase().insert(info);
    }

    public static synchronized ArrayList<ContactsPeoplesInfo> read() {
        ArrayList<ContactsPeoplesInfo> result = NPOrmDBHelper.dataBase().query(
                ContactsPeoplesInfo.class);
        return result;
    }

    public static synchronized ContactsPeoplesInfo readFromPhoneNum(String phoneNum) {
        ArrayList<ContactsPeoplesInfo> result = NPOrmDBHelper.dataBase().query(
                ContactsPeoplesInfo.class);
        if (result.isEmpty()) {
            ContactsPeoplesInfo info = new ContactsPeoplesInfo();
            info.phoneNum = phoneNum;
            info.name = "未知";
            return info;
        }
        for (ContactsPeoplesInfo info : result) {
            if (info.phoneNum.equals(phoneNum)) {
                return info;
            }
        }
        ContactsPeoplesInfo info = new ContactsPeoplesInfo();
        info.phoneNum = phoneNum;
        info.name = "未知";
        return info;
    }

    public static void delete(ContactsPeoplesInfo info) {
      int a=  NPOrmDBHelper.dataBase().delete(info);
        Log.e("asdf",a+"");
    }
    public static void deleteAll() {
        NPOrmDBHelper.dataBase().deleteAll(ContactsPeoplesInfo.class);
    }
}
