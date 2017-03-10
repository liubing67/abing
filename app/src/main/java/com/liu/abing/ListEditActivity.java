package com.liu.abing;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.liu.abing.base.BaseActivity;

import java.util.ArrayList;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/3/10 8:09
 * 修改人：Administrator
 * 修改时间：2017/3/10 8:09
 * 修改备注：
 */
public class ListEditActivity extends BaseActivity {
    private ListView lvMain;
    private MainAdapter adapter;
    private Button btn_add_new;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listedit);
        initView();
        initData();
    }

    private void initData() {
        list = getData();
        adapter = new MainAdapter(list);
        lvMain.setAdapter(adapter);
        btn_add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addData("", lvMain);
            }
        });
    }

    private void initView() {
        lvMain = (ListView) findViewById(R.id.lv_main);
        btn_add_new = (Button) findViewById(R.id.btn_add_new);
    }

    private ArrayList<String> getData() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("input data from" + i);
        }
        return list;
    }

    private class MainAdapter extends BaseAdapter {

        private ArrayList<String> dataList;
        private EditTextWater watcher;
        private int add_tag = -1;//用于控制滑动状态输入法会弹起的bug

        public MainAdapter(ArrayList<String> dataList) {
            this.dataList = dataList;
        }

        /**
         * 手动新增一条数据
         *
         * @param sn
         * @param listView
         */
        public void addData(String sn, ListView listView) {
            add_tag = 1;
            dataList.add(sn);
            notifyDataSetChanged();
            listView.smoothScrollToPosition(getCount() - 1);//让ListView滚动到底部使新增的item可以看到
        }

        /**
         * 手动删除一条数据
         *
         * @param position
         */
        public void removeData(int position) {
            dataList.remove(position);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            final ViewHolder holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_edittext, parent, false);
            holder.tvNum = (TextView) convertView.findViewById(R.id.tv_num);
            holder.etNewAdd = (EditText) convertView.findViewById(R.id.et_new_add);
            holder.tvChangeTitle = (TextView) convertView.findViewById(R.id.tv_change_title);
            convertView.setTag(holder);
            watcher = new EditTextWater(position);
            holder.etNewAdd.addTextChangedListener(watcher);
            holder.etNewAdd.setText(dataList.get(position));
            holder.tvNum.setText(position + 1 + "");
            holder.etNewAdd.setSelection(dataList.get(position).length());//设置光标问题
            holder.tvChangeTitle.setText("删除");
            holder.tvChangeTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.etNewAdd.removeTextChangedListener(watcher);
                    removeData(position);
                }
            });

            /**
             * 当前这里手动获取焦点
             * 但是实际输入法没有在这里弹出
             * 因此如果需要弹出输入法的时候就需要手动写一个
             */
            if (position == getCount() - 1 && add_tag == 1) {
                add_tag = -1;
                holder.etNewAdd.requestFocus();
                holder.etNewAdd.post(new Runnable() {
                    @Override
                    public void run() {
                        InputMethodManager imm = (InputMethodManager) parent.getContext().getSystemService(INPUT_METHOD_SERVICE);
                        imm.showSoftInput(holder.etNewAdd, InputMethodManager.SHOW_IMPLICIT);
                    }
                });
            } else {
                holder.etNewAdd.clearFocus();
            }

            return convertView;
        }

        /**
         * 重新文字监听器
         * 保存当前输入的信息以保证滑动的时候EditText显示的文字同步
         */
        class EditTextWater implements TextWatcher {

            private int position;

            public EditTextWater(int position) {
                this.position = position;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("usher", "afterTextChanged: " + position + "-" + s);
                dataList.set(position, s.toString());
            }
        }

        class ViewHolder {
            TextView tvNum;
            EditText etNewAdd;
            TextView tvChangeTitle;
        }
    }
}
