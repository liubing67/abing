package com.abing.androidnote.androida笔记.时间有关的笔记;

Date date = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
String timestamp = sdf.format(date);



timecurrentTimeMillis = System.currentTimeMillis();
		timeGetTime = new Date().getTime();
		timeSeconds = System.currentTimeMillis();
		timeMillis = Calendar.getInstance().getTimeInMillis();

		����ʱ���ת���뿴 TimeStamp�ļ�