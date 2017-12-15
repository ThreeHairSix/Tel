/**
 * Project Name:1.5.5 Alpha
 * File Name:HCZDBHelper.java
 * Package Name:com.pingan.carowner.db
 * Date:2015-1-14下午2:56:50
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
 */

package com.nptel.tel.db;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.DataBase;


public class NPOrmDBHelper {
	private static final Object LOCK = new Object();

	private static DataBase instance;

	public static DataBase dataBase() {
		if (instance != null) {
			return instance;
		}
		synchronized (LOCK) {
			if (instance == null) {
				instance = LiteOrm.newInstance(NPDBConfig.dbConfig());

			}
		}
		return instance;
	}

}
