package com.abing.androidnote.androida笔记;

if(!TextUtils.isEmpty(state)){
            map.put("state",state);
        }else{
            map.put("state","\"\"");
        }

//���state=""

  String res = result.replace("null", "\"\"");
//ȥ��gridview�ĵ������ɫ
 gridview.setSelector(new ColorDrawable(Color.TRANSPARENT));


// listview ����

Collections.sort(personlist, new Comparator<!-- <Person> -->() {
           @Override
           public int compare(Person lhs, Person rhs) {
               return rhs.getTime().compareTo(lhs.getTime());
           }
       });


//��ȡlistview�� item�ϵ����ݲ�����otherActivity  arrayList.get(position).getData()��getData����Ϊ  ����  get set������
intent.putExtra("data", arrayList.get(position).getData());

 // ����
//                    Collections.sort(recordlist, new Comparator<!-- <RecordData> -->() {
//                        @Override
//                        public int compare(RecordData lhs, RecordData rhs) {
//                            return lhs.getTime().compareTo(rhs.getTime());
//                        }
//                    });

android:windowSoftInputMode="adjustPan|stateHidden"  // ����Manifest�ļ��У�����ֹEdittext������ѹ��


/// ��list������ߵ�ʵ����ת��Ϊ�ַ�������
 int size=balconyDataList1.size();
        String[] str=new String[size];
        for (int i=0;i<size;i++)
        {
            str[i]= balconyDataList1.get(i).getBalconyno();
        }

///popupwindow����   ǳ��͸��ɫandroid:background="#a0000000"



/// /////////////////////////////////////�˳���¼����
		Intent logoutIntent = new Intent(PersonalActivity.this, LoginActivity.class);
        logoutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(logoutIntent);


		/////////////////////////////��ȡradiogroup��radiobutton�ϵ�����
		int leixingcheck=radiogroupleixing.getCheckedRadioButtonId();//��ȡѡ��radiobuttonid
				RadioButton leixingbutton= (RadioButton) findViewById(leixingcheck);
				String leixing=leixingbutton.getText().toString();//��ȡѡ�е�radiobutton
			

///////////////����绰����
		 case R.id.decotate_phone:
                index= (int) v.getTag();

                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                //url:ͳһ��Դ��λ��
                //uri:ͳһ��Դ��ʾ�������㣩
                intent.setData(Uri.parse("tel:" + decorateDataList.get(index).getPhone()));
                //����ϵͳ������
                startActivity(intent);
                break;

////////////  �Զ���ȡ��7���ʱ��

    // �Զ���ȡ��7���ʱ��
    private List<!-- <String> --> gettime()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i=0;i<7;i++)
        {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, +i);
            Date monday = c.getTime();
            String preMonday = sdf.format(monday);
            spinnerdatalist.add(preMonday);

        }
        return spinnerdatalist;
    }

	////��imageView ��ͼƬ��ָ������ڳ�����ͬʱ�߶�����Ӧ,


	<ImageView android:id="@+id/test_image"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:scaleType="fitXY"
	android:adjustViewBounds="true"
	android:layout_gravity="center"
	android:contentDescription="@string/app_name"
	android:src="@drawable/ic_launcher" />


	/////Map����JsonObject 
						String value = "�ύʧ��";
                        String message = new JSONObject(resulttemp.getString("message")).toString();
                        JSONObject json =  new JSONObject(message);
                        Iterator iterator = json.keys();
                        while(iterator.hasNext()){
                               String key = (String) iterator.next();
                               value = json.getString(key);
                            }
                        Tools.showToast(AddContract.this, value);


///����հ״����������

public boolean onTouchEvent(MotionEvent event) {
		if(null != this.getCurrentFocus()){
			/**
			 * ����հ�λ�� ���������
			 */
			InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
		}
		return super .onTouchEvent(event);
	}


//�ж�bundle�Ƿ�������
Bundle bundle = getIntent().getExtras(); 
if��bundle!=null){
	String data=bundle.getString("Data");
	//��������
	}


//	===��instanceof�����ж��ڴ���ʵ�ʶ���A�ǲ���B����
	if (view instanceof TextView) {
					// �����Ҫ��ȡ����һ�У����Զ���gridview��adapter��item����2��textviewһ����������id����ʾ��һ��
					TextView tv = (TextView) view;
					Toast.makeText(context,
							"position=" + position + "||" + tv.getText(),
							Toast.LENGTH_SHORT).show();
					Log.e("hefeng", "gridView listaner position=" + position
							+ "||text=" + tv.getText());
				}

�������������������Ҫǿ��ת����ʱ��
class Dog extends Animal
Ʃ��dog�������Լ��ķ���wangwang

Animal anAnimal = new Dog();
��ʱ����ֱ�ӵ���anAnimal.wangwang����
���ǿ���
if( anAnimal instanceof Dog){
Dog dog = (Dog)anAnimal;
dog.wangwang�Ϳ��Ե�����
}

/////////////��һ��Edittext����������һ��edittext����ʾ

  phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                userNameEditText.setText(phoneEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



///////////////��ٷ���
NumberFormat nt = NumberFormat.getPercentInstance();
//���ðٷ�����ȷ��2��������λС��
nt.setMinimumFractionDigits(2);
text_Conversionrate.setText(nt.format(Double.valueOf(10) / Double.valueOf(3)));
text_Conversionrate.setText(nt.format(10/3));

//����Ӧ�÷�ֹ������ʾ
  <item name="android:windowIsTranslucent">true</item>
  <item name="android:windowNoTitle">true</item>






  // �����������   km
    private final double EARTH_RADIUS = 6378137.0;
    private double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
        double radLat1 = (lat_a * Math.PI / 180.0);
        double radLat2 = (lat_b * Math.PI / 180.0);
        double a = radLat1 - radLat2;
        double b = (lng_a - lng_b) * Math.PI / 180.0;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000/1000;
        Log.e("222222222222222",lat_a+"---"+lng_a+"----------"+lat_b+"---"+lng_b+"------"+s+"");
        return s;
    }
	
	
	
	shrinkResources true  // ���õ���Դ\\\
	
	
	////////////��ֹappӦ�ý���
	getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);