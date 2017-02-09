package com.liu.abing.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.abing.liudialog_lib.animation.BaseAnimatorSet;
import com.abing.liudialog_lib.animation.BounceEnter.BounceTopEnter;
import com.abing.liudialog_lib.animation.SlideExit.SlideBottomExit;
import com.abing.liudialog_lib.dialog.entity.DialogMenuItem;
import com.abing.liudialog_lib.dialog.listener.OnOperItemClickL;
import com.abing.liudialog_lib.dialog.widget.ActionSheetDialog;
import com.abing.liudialog_lib.dialog.widget.NormalListDialog;
import com.liu.abing.R;
import com.liu.abing.base.BaseActivity;
import com.tools.util.ToastUtil;

import java.util.ArrayList;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/2/9 15:09
 * 修改人：Administrator
 * 修改时间：2017/2/9 15:09
 * 修改备注：
 */
public class LiuDialogActivity extends BaseActivity {


    private ArrayList<DialogMenuItem> mMenuItems = new ArrayList<>();
    private String[] mStringItems = {"收藏", "下载", "分享", "删除", "歌手", "专辑"};
    private BaseAnimatorSet mBasIn;
    private BaseAnimatorSet mBasOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liudialog);

        initView();
    }


    private void initView()
    {

        mMenuItems.add(new DialogMenuItem("收藏", R.mipmap.ic_winstyle_favor));
        mMenuItems.add(new DialogMenuItem("下载", R.mipmap.ic_winstyle_download));
        mMenuItems.add(new DialogMenuItem("分享", R.mipmap.ic_winstyle_share));
        mMenuItems.add(new DialogMenuItem("删除", R.mipmap.ic_winstyle_delete));
        mMenuItems.add(new DialogMenuItem("歌手", R.mipmap.ic_winstyle_artist));
        mMenuItems.add(new DialogMenuItem("专辑", R.mipmap.ic_winstyle_album));

        mBasIn = new BounceTopEnter();
        mBasOut = new SlideBottomExit();












        //单选对话框
        findViewById(R.id.but_dialog1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final NormalListDialog dialog = new NormalListDialog(LiuDialogActivity.this, mMenuItems);
                dialog.title("请选择")//
                        .showAnim(mBasIn)//
                        .dismissAnim(mBasOut)//
                        .show();
                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                       ToastUtil.showShort(LiuDialogActivity.this, mMenuItems.get(position).mOperName);
                        dialog.dismiss();
                    }
                });
            }
        });

        //仿IOS底部弹出
        findViewById(R.id.but_dialog2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] stringItems = {"接收消息并提醒", "接收消息但不提醒", "收进群助手且不提醒", "屏蔽群消息"};
                final ActionSheetDialog dialog = new ActionSheetDialog(LiuDialogActivity.this, stringItems, null);
                dialog.title("选择群消息提醒方式\r\n(该群在电脑的设置:接收消息并提醒)")//
                        .titleTextSize_SP(14.5f)//
                        .show();

                dialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ToastUtil.showShort(LiuDialogActivity.this, stringItems[position]);
                        dialog.dismiss();
                    }
                });
            }
        });
    }

}
