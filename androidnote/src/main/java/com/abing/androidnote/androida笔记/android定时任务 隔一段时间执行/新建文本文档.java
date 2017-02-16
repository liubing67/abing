package com.abing.androidnote.androida笔记.android定时任务

������ÿ��һ��ʱ���ִ��ĳ��������ֱ���رն�ʱ������


final Handler handler = new Handler(); 
Runnable runnable = new Runnable(){ 
@Override 
public void run() { 
// TODO Auto-generated method stub 
// �ڴ˴����ִ�еĴ��� 
handler.postDelayed(this, 50);// 50����ʱʱ�� 
} 
}; 
handler.postDelayed(runnable, 50);// �򿪶�ʱ����ִ�в��� 
handler.removeCallbacks(runnable);// �رն�ʱ������

 

�����Ǹ�һ��ʱ���ִ��ĳ������һ�Σ�ִ����󣬲���ִ��



final Handler handler = new Handler(); 
runCount = 0;// ȫ�ֱ����������ж��Ƿ��ǵ�һ��ִ�� 
Runnable runnable = new Runnable(){ 

@Override 
public void run() { 
// TODO Auto-generated method stub 
if(runCount == 1){// ��һ��ִ����رն�ʱִ�в��� 
// �ڴ˴����ִ�еĴ��� 
handler.removeCallbacks(this); 
} 
handler.postDelayed(this, 50); 
runCount++; 
} 

}; 
handler.postDelayed(runnable, 50);// �򿪶�ʱ����ִ�в���