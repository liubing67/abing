package com.liu.abing.refresh;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.abing.R;
import com.liu.extend.adapters.BaseViewHolder;


/**
 * Created by Mr.Jude on 2015/2/22.
 */
public class PersonViewHolder extends BaseViewHolder<Person> {
    private TextView mTv_name;
    private TextView mTv_sign;


    public PersonViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_person);
        mTv_name = $(R.id.person_name);
        mTv_sign = $(R.id.person_sign);
    }

    @Override
    public void setData(final Person person){
        mTv_name.setText(person.getName());
        mTv_sign.setText(person.getSign());
    }
}
