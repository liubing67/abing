package com.abing.androidnote.androida笔记;
//����״̬������ɫ
	<style name="Theme.Timetodo" parent="@style/AppTheme.NoActionBar">
        <!-- translucent system bars -->
        <item name="android:fitsSystemWindows">true</item>
        <item name="android:clipToPadding">true</item>
        <item name="android:statusBarColor">@color/statusbar</item>///����״̬������ɫ
    </style>

	 <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
    </style>




	///����ҳ���ǳ���ʽ��ʽ
	 <style name="Theme.Light.NoTitleBar">
        <item name="android:windowNoTitle">true</item>
    </style>


	///����ҳȫ����ʾ

	 requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //ȫ��