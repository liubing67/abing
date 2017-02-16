package com.abing.androidnote.androida笔记.时间有关的笔记;
/////////�ж�����ʱ����������
 String date1="1987-01-01";
  String date2="2010-01-01";
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
  Date d1=sdf.parse(date1);
  Date d2=sdf.parse(date2);
  long daysBetween=(d2.getTime()-d1.getTime()+1000000)/(3600*24*1000);
  System.out.println("1987-01-01 �� 2010-01-01 ��� "+daysBetween+" ��");
  ///////ʱ���ת��Ϊ���ڵ�����
  String beginDate="1328007600000";
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
  String sd = sdf.format(new Date(Long.parseLong(beginDate)));
  System.out.println(sd);


//�Ƚ�����ʱ��Ĵ�С
  public void DateCompare(String s1,String s2)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = sdf.parse(s1);
            Date d2 = sdf.parse(s2);
            if(Math.abs(((d1.getTime() - d2.getTime())/(24*3600*1000))) >=3)
            {
                System.out.println("��������");
            }else{
                System.out.println("С������");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
