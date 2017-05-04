package com.liu.abing.refresh;

import android.content.Context;
import android.view.ViewGroup;

import com.liu.extend.adapters.BaseViewHolder;
import com.liu.extend.adapters.RecyclerArrayAdapter;


/**
 * Created by Mr.Jude on 2015/7/18.
 */
public class PersonAdapter extends RecyclerArrayAdapter<Person> {
    public PersonAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonViewHolder(parent);
    }
}
