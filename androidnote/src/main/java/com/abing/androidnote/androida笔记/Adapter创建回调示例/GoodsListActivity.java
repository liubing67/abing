package com.abing.androidnote.androida笔记.Adapter创建回调示例;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.activity.base.BaseActivity;
import com.example.activity.evaluate.EvaluateActivity;
import com.example.activity.order.CommitOrderActivity;
import com.example.datas.goods.ContentData;
import com.example.tools.helper.Tools;
import com.example.view.DividerItemDecoration;
import com.example.adapter.foods.ListContentAdapter;
import com.example.adapter.foods.ListMenuAdapter;
import com.example.animutils.GoodsAnimUtil;
import com.example.customshoppingcardemo.R;
import com.example.datasave.GoodsDataBaseInterface;
import com.example.datasave.OperateGoodsDataBase;

import java.util.ArrayList;
import java.util.List;

public class GoodsListActivity extends BaseActivity implements View.OnClickListener{

    //控件
    private RecyclerView m_list_menu,m_list_content;
    private TextView m_list_num,m_list_all_price;
    private RelativeLayout rela_shopcar;
    private Button button_diancan;
    private TextView text_title_title,text_title_right;
    private ImageButton imb_title_back;

    //数据
    private List<String> stringMenuList = new ArrayList<String>();
    private List<ContentData> stringContentList = new ArrayList<ContentData>();
    private ListMenuAdapter listMenuAdapter;//
    private ListContentAdapter listContentAdapter;
    public static int SELECTPOSITION = 0;

    /**
     * 数据操作接口
     */
    GoodsDataBaseInterface mGoodsDataBaseInterface = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
        setRecyclerView();
        initHttp();
    }
    private void initview()
    {
        m_list_menu= (RecyclerView) findViewById(R.id.m_list_menu);
        m_list_content= (RecyclerView) findViewById(R.id.m_list_content);
        m_list_num= (TextView) findViewById(R.id.m_list_num);
        m_list_all_price= (TextView) findViewById(R.id.m_list_all_price);
        rela_shopcar= (RelativeLayout) findViewById(R.id.rela_shopcar);
        button_diancan= (Button) findViewById(R.id.button_diancan);
        text_title_title= (TextView) findViewById(R.id.text_title_title);
        imb_title_back= (ImageButton) findViewById(R.id.imb_title_back);
        text_title_right= (TextView) findViewById(R.id.text_title_right);
        text_title_title.setText("火锅店1");
        text_title_right.setText("评价");
        imb_title_back.setOnClickListener(this);
        text_title_right.setOnClickListener(this);
        mGoodsDataBaseInterface = OperateGoodsDataBase.getInstance();
        //清空数据库缓存
        mGoodsDataBaseInterface.deleteAll(this);
        button_diancan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tools.startactivity(GoodsListActivity.this,null, CommitOrderActivity.class);
            }
        });
    }

    /**
     * 设置RecyclerView的布局方式
     */
    private void setRecyclerView() {
        //垂直listview显示方式
        m_list_menu.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        m_list_menu.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        m_list_content.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
    /**
     * 模拟网络请求数据
     */
    private void initHttp() {
        for (int i = 0; i < 10; i++) {
            stringMenuList.add("芝心披萨"+i);
        }
        ContentData contentData = null;
        for (int i = 0; i < 4; i++) {
            contentData=new ContentData();
            contentData.setGoodsid("11"+i);
            contentData.setGoodsname("name"+i);
            contentData.setGoodsprice("2"+i);
            contentData.setMenupos("3"+i);
            stringContentList.add(contentData);
        }

        setMenuCommonadapter();
        setContentCommonadapter();
    }
    /**
     * 菜单列表 数据填充
     */
    private void setMenuCommonadapter()
    {
        //初始化数据
        listMenuAdapter=new ListMenuAdapter(this,stringMenuList);
        m_list_menu.setAdapter(listMenuAdapter);
        listMenuAdapter.setOnMenuItemClickListenter(new ListMenuAdapter.OnMenuItemClickListenter() {
            @Override
            public void onItemClick(View v, int position) {
                SELECTPOSITION = position;
                Log.e("TAG", "SELECTPOSITION:" + SELECTPOSITION);
                listMenuAdapter.notifyDataSetChanged();
                listContentAdapter.notifyDataSetChanged();
                setAll();
            }
        });
    }

    /**
     * 菜品内容列表
     */
    private void setContentCommonadapter()
    {
        listContentAdapter=new ListContentAdapter(this,stringContentList);
        m_list_content.setAdapter(listContentAdapter);
        listContentAdapter.setOnContentItemClickListener(new ListContentAdapter.OnContentItemClickListener() {
            @Override
            public void onItemClick(ListContentAdapter.ViewHolder holder) {
//                Tools.startactivity(GoodsListActivity.this, null, GoodsDetailActivity.class);
            }

            @Override
            public void onItemJiaClick(ListContentAdapter.ViewHolder holder, int position) {
                String numText = holder.mNumber.getText().toString().trim();
                /** 点击加号之前还没有数据的时候 */
                if (numText.isEmpty() || numText.equals("0")) {
//                    Log.e("TAG", "点击获取信息：SELECTPOSITION--" + SELECTPOSITION + "  DemoData.ListMenu_GOODSID[position]--" + DemoData.ListMenu_GOODSID[holder.getPosition()]);
                    holder.mImgJian.setVisibility(View.VISIBLE);
                    holder.mNumber.setText(mGoodsDataBaseInterface.saveGoodsNumber(GoodsListActivity.this, SELECTPOSITION, Integer.parseInt(stringContentList.get(holder.getPosition()).getGoodsid()), "1", stringContentList.get(holder.getPosition()).getGoodsprice() + "") + "");
                    holder.mNumber.setVisibility(View.VISIBLE);
                }/** 点击加号之前有数据的时候 */
                else {
                    holder.mNumber.setText(mGoodsDataBaseInterface.saveGoodsNumber(GoodsListActivity.this, SELECTPOSITION, Integer.parseInt(stringContentList.get(holder.getPosition()).getGoodsid()), String.valueOf(Integer.parseInt(numText) + 1), stringContentList.get(holder.getPosition()).getGoodsprice() + "") + "");
                }
                String goodsnum = holder.mNumber.getText().toString().trim();
                stringContentList.get(position).setGoodsnum(goodsnum);
                /** 动画 */
                GoodsAnimUtil.setAnim(GoodsListActivity.this, holder.mImgJia, rela_shopcar);
                GoodsAnimUtil.setOnEndAnimListener(new onEndAnim());
                /** 统计购物总数和购物总价 */
                setAll();
            }

            @Override
            public void onItemJianClick(ListContentAdapter.ViewHolder holder, int position) {
                String numText = holder.mNumber.getText().toString().trim();
                holder.mNumber.setText(mGoodsDataBaseInterface.saveGoodsNumber(GoodsListActivity.this, SELECTPOSITION, Integer.parseInt(stringContentList.get(holder.getPosition()).getGoodsid()), String.valueOf(Integer.parseInt(numText) - 1), stringContentList.get(holder.getPosition()).getGoodsprice() + "") + "");
                numText = holder.mNumber.getText().toString().trim();
                /** 减完之后  数据为0 */
                if (numText.equals("0")) {
                    holder.mNumber.setVisibility(View.GONE);
                    holder.mImgJian.setVisibility(View.GONE);
                }
                String goodsnum = holder.mNumber.getText().toString().trim();
                stringContentList.get(position).setGoodsnum(goodsnum);
                setAll();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imb_title_back:
                finish();
                break;
            case R.id.text_title_right:
                Tools.startactivity(GoodsListActivity.this,null, EvaluateActivity.class);
                break;
        }
    }
    /**
     * 动画结束后，更新所有数量和所有价格
     */
    class onEndAnim implements GoodsAnimUtil.OnEndAnimListener {
        @Override
        public void onEndAnim() {

        }
    }
    /**
     * 点击加号和减号的时候设置总数和总价格
     */
    private void setAll() {
        //设置所有购物数量
        if (mGoodsDataBaseInterface.getSecondGoodsNumberAll(this, SELECTPOSITION) == 0) {
            m_list_num.setVisibility(View.GONE);
            m_list_all_price.setText("￥0");
            m_list_num.setText("0");
        } else {
            m_list_all_price.setText("￥" + mGoodsDataBaseInterface.getSecondGoodsPriceAll(this, SELECTPOSITION) + "");
            m_list_num.setText(mGoodsDataBaseInterface.getSecondGoodsNumberAll(this, SELECTPOSITION) + "");
            m_list_num.setVisibility(View.VISIBLE);
        }
    }

}
