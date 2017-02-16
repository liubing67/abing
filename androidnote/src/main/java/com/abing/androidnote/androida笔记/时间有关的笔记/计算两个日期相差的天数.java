package com.abing.androidnote.androida笔记.时间有关的笔记;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class test16 {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1=sdf.parse("2012-09-08 10:10:10");
		Date d2=sdf.parse("2012-09-15 00:00:00");
		System.out.println(daysBetween(d1,d2));

		System.out.println(daysBetween("2012-09-08 10:10:10","2012-09-15 00:00:00"));
	}
	
	/** 
     * ������������֮���������� 
     * @param smdate ��С��ʱ��
     * @param bdate  �ϴ��ʱ��
     * @return �������
	 * @throws ParseException 
     */  
    public static int daysBetween(Date smdate,Date bdate) throws ParseException  
    {  
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	smdate=sdf.parse(sdf.format(smdate));
    	bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();  
        cal.setTime(smdate);  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(bdate);  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);
          
       return Integer.parseInt(String.valueOf(between_days));         
    }  
    
/**
*�ַ��������ڸ�ʽ�ļ���
*/
    public static int daysBetween(String smdate,String bdate) throws ParseException{
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();  
        cal.setTime(sdf.parse(smdate));  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(sdf.parse(bdate));  
        long time2 = cal.getTimeInMillis();       
        long between_days=(time2-time1)/(1000*3600*24);
          
       return Integer.parseInt(String.valueOf(between_days));   
    }

}
