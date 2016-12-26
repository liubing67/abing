package com.liu.abing.drag;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.tools.util.DensityUtil;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/26 17:46
 * 修改人：Administrator
 * 修改时间：2016/12/26 17:46
 * 修改备注：
 */
public class DragThreeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navView;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragthree);

        initView();
    }
    private void initView()
    {
        navView= (NavigationView) findViewById(R.id.nav_view);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.select_city, R.string.located_failed);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
//
        navView.setNavigationItemSelectedListener(this);
        setTitle(getString(R.string.located_failed));

        //头部布局点击设置
        View drawview = navView.inflateHeaderView(R.layout.nav_header_main_new);
        ImageView head_iv = (ImageView) drawview.findViewById(R.id.imageView);
        LinearLayout linearLayoutl = (LinearLayout) drawview.findViewById(R.id.nav_layout);
        head_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DragThreeActivity.this,"头部被点击了",Toast.LENGTH_SHORT).show();
            }
        });
        linearLayoutl.setMinimumHeight(DensityUtil.getWindowHeight(DragThreeActivity.this));

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_tngou_girl) {
            Toast.makeText(DragThreeActivity.this,"1",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_my_blog) {
            Toast.makeText(DragThreeActivity.this,"2",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_tngou_news) {
            Toast.makeText(DragThreeActivity.this,"3",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_set) {
            Toast.makeText(DragThreeActivity.this,"4",Toast.LENGTH_SHORT).show();
            return true;
        }
        setTitle(item.getTitle());
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private long exitTime = 0;
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(getApplicationContext(),"再按一次退出", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }
        }
    }
}
