package com.nptel.tel.util;

import com.nptel.tel.db.util.ContactsPeoplesInfo;

import java.util.Comparator;

public class PinyinComparator implements Comparator<ContactsPeoplesInfo> {

	public int compare(ContactsPeoplesInfo o1, ContactsPeoplesInfo o2) {
		// 这里主要是用来对ListView里面的数据根据ABCDEFG...来排序
		if (o2.sortLetters.equals("#")) {
			return -1;
		} else if (o1.sortLetters.equals("#")) {
			return 1;
		} else {
			return o1.sortLetters.compareTo(o2.sortLetters);
		}
	}

}