package com.nptel.tel.db.util;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.PrimaryKey.AssignType;
import com.litesuits.orm.db.annotation.Table;
import com.nptel.tel.db.NPOrmDBHelper;

import java.util.ArrayList;

/**
 * 短信模版
 */
@Table("sms_stencil_info")
public class SMSStencilInfo {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("id")
    public long id = 0;
    @Column("smsId")
    public long smsId = 0;//短信id
    @Column("title")
    public String title = "";// 标题
    @Column("content")
    public String content = "";// 内容
    public boolean isCheck = false;//是否被选中

    public static void insert(ArrayList<SMSStencilInfo> list) {
        deleteAll();
        NPOrmDBHelper.dataBase().insert(list);
    }

    public static synchronized ArrayList<SMSStencilInfo> read() {
        ArrayList<SMSStencilInfo> result = NPOrmDBHelper.dataBase().query(
                SMSStencilInfo.class);
        return result;
    }

    public static void deleteAll() {
        NPOrmDBHelper.dataBase().deleteAll(SMSStencilInfo.class);
    }
}
