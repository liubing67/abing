package com.abing.androidnote.androida笔记.获取短信验证码.code;

import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/5/26 0026.
 */
public class UserUtils { //手机号分段显示

    public static void formatPhoneNumber(CharSequence s, int cursorPosition, int before, int count,EditText mEditText,TextWatcher mTextWatcher){
        if(before == 0 && count == 1){  //Entering values

            String val = s.toString();
            String a = "";
            String b = "";
            String c = "";
            if (val != null && val.length() > 0) {
                val = val.replace(" ", "");
                if (val.length() >= 3) {
                    a = val.substring(0, 3);
                } else if (val.length() < 3) {
                    a = val.substring(0, val.length());
                }
                if (val.length() >= 7) {
                    b = val.substring(3, 7);
                    c = val.substring(7, val.length());
                } else if (val.length() > 3 && val.length() < 7) {
                    b = val.substring(3, val.length());
                }
                StringBuffer stringBuffer = new StringBuffer();
                if (a != null && a.length() > 0) {
                    stringBuffer.append(a);

                }
                if (b != null && b.length() > 0) {
                    stringBuffer.append(" ");
                    stringBuffer.append(b);

                }
                if (c != null && c.length() > 0) {
                    stringBuffer.append(" ");
                    stringBuffer.append(c);
                }
                mEditText.removeTextChangedListener(mTextWatcher);
                mEditText.setText(stringBuffer.toString());
                if(cursorPosition == 3 || cursorPosition == 8){
                    cursorPosition = cursorPosition+2;
                }else{
                    cursorPosition = cursorPosition+1;
                }
                if(cursorPosition <= mEditText.getText().toString().length()) {
                    mEditText.setSelection(cursorPosition);
                }else{
                    mEditText.setSelection(mEditText.getText().toString().length());
                }
                mEditText.addTextChangedListener(mTextWatcher);
            } else {
                mEditText.removeTextChangedListener(mTextWatcher);
                mEditText.setText("");
                mEditText.addTextChangedListener(mTextWatcher);
            }

        }

        if(before == 1 && count == 0){  //Deleting values

            String val = s.toString();
            String a = "";
            String b = "";
            String c = "";

            if (val != null && val.length() > 0) {
                val = val.replace(" ", "");
                if(cursorPosition == 3){
                    val = removeCharAt(val,cursorPosition-1,s.toString().length()-1);
                }else if(cursorPosition == 8){
                    val = removeCharAt(val,cursorPosition-2,s.toString().length()-2);
                }
                if (val.length() >= 3) {
                    a = val.substring(0, 3);
                } else if (val.length() < 3) {
                    a = val.substring(0, val.length());
                }
                if (val.length() >= 7) {
                    b = val.substring(3, 7);
                    c = val.substring(7, val.length());
                } else if (val.length() > 3 && val.length() < 7) {
                    b = val.substring(3, val.length());
                }
                StringBuffer stringBuffer = new StringBuffer();
                if (a != null && a.length() > 0) {
                    stringBuffer.append(a);

                }
                if (b != null && b.length() > 0) {
                    stringBuffer.append(" ");
                    stringBuffer.append(b);

                }
                if (c != null && c.length() > 0) {
                    stringBuffer.append(" ");
                    stringBuffer.append(c);
                }
                mEditText.removeTextChangedListener(mTextWatcher);
                mEditText.setText(stringBuffer.toString());
                if(cursorPosition == 3 || cursorPosition == 8){
                    cursorPosition = cursorPosition-1;
                }
                if(cursorPosition <= mEditText.getText().toString().length()) {
                    mEditText.setSelection(cursorPosition);
                }else{
                    mEditText.setSelection(mEditText.getText().toString().length());
                }
                mEditText.addTextChangedListener(mTextWatcher);
            } else {
                mEditText.removeTextChangedListener(mTextWatcher);
                mEditText.setText("");
                mEditText.addTextChangedListener(mTextWatcher);
            }

        }
    }
    public static String removeCharAt(String s, int pos,int length) {

        String value = "";
        if(length > pos){
            value = s.substring(pos + 1);
        }
        return s.substring(0, pos)+value ;
    }


    /*

     edit_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            private boolean isDelete;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edit_phone.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_DEL) {
                            isDelete = true;
                        }
                        return false;
                    }
                });
                UserUtils.formatPhoneNumber(s, start, before, count, edit_phone, this);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


     */
}

