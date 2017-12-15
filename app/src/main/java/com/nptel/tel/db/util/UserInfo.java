package com.nptel.tel.db.util;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.PrimaryKey.AssignType;
import com.litesuits.orm.db.annotation.Table;
import com.nptel.tel.db.NPOrmDBHelper;

import java.util.ArrayList;

@Table("user_info")
public class UserInfo {
//	@Ignore
//	private final static String TAG = "UserInfo";
	@PrimaryKey(AssignType.AUTO_INCREMENT)
	@Column("_id")
	public long _id = 0;
	@Column("Id")
	public int Id = 0;// id
	@Column("AppCode")
	public String AppCode = "";// 编号
	@Column("Name")
	public String Name = "";// 姓名
	@Column("Sgin")
	public String Sgin = "";// GUID值
	@Column("Telephone")
	public String Telephone = "";//电话号码
	@Column("Password")
	public String Password = "";// 密码
	@Column("CreateTime")
	public String CreateTime = "";// 生成时间
	@Column("Job")
	public String Job = "";// 职业
	@Column("Age")
	public String Age = "";// 年龄
	@Column("QrCode")
	public String QrCode = "";// 付款二维码
	@Column("token")
	public String token = "";//备注
	@Column("Remark")
	public String Remark = "";//备注

	public static void insert(UserInfo userInfo) {
		deleteAll();
		NPOrmDBHelper.dataBase().insert(userInfo);
	}

	public static synchronized UserInfo read() {
		ArrayList<UserInfo> result = NPOrmDBHelper.dataBase().query(
				UserInfo.class);
		if (result.isEmpty()) {
			return new UserInfo();
		}
		return result.get(0);
	}

	public static boolean isLogin() {
		ArrayList<UserInfo> result = NPOrmDBHelper.dataBase().query(
				UserInfo.class);
		if (result == null) {
			return false;
		}
		if (result.isEmpty()) {
			return false;
		}
		return true;
	}

	public static void deleteAll() {
		NPOrmDBHelper.dataBase().deleteAll(UserInfo.class);
	}
}
