package com.nptel.tel.db;

import android.database.sqlite.SQLiteDatabase;

import com.litesuits.orm.db.DataBaseConfig;
import com.litesuits.orm.db.impl.SQLiteHelper.OnUpdateListener;
import com.nptel.tel.activity.TelApplication;

public class NPDBConfig implements OnUpdateListener {

	public static final String DB_NAME = "tg_db";

	public static final int DB_VERSION = 1;

	public static DataBaseConfig dbConfig() {
		return new DataBaseConfig(TelApplication.getInstance(), DB_NAME,
				DB_VERSION, new NPDBConfig());
	}

	@Override
	public void onUpdate(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
