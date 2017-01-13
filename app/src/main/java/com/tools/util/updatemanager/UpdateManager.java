package com.tools.util.updatemanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liu.abing.R;
import com.liu.extend.constant.Urls;
import com.orhanobut.logger.Logger;
import com.tools.util.ToastUtil;

import org.xmlpull.v1.XmlPullParser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * App更新工具包
 * Created by Administrator on 2015/12/14.
 */
public class UpdateManager {
    private static final int DOWN_NOSDCARD = 0;//没有挂载SD卡，无法下载文件
    private static final int DOWN_UPDATE = 1;//下载时更新进度
    private static final int DOWN_OVER = 2; //下载完成时
    private final int GET_UNDATAINFO_ERROR = 3; //请求xml和解析xml抛出异常时
    private final int DOWN_ERROR = 4; //下载apk失败时
    private final int UPDATA_NONEED = 5; //版本号相同时
    private final int UPDATA_CLIENT = 6; //版本号不相同时

    private static UpdateManager updateManager;

    private Context mContext;
    // 下载对话框
    private Dialog downloadDialog;
    // 进度条
    private ProgressBar mProgress;
    // 显示下载数值
    private TextView mProgressText;
    // 进度值
    private int progress;
    // 下载线程
    private Thread downLoadThread;
    // 终止标记
    private boolean interceptFlag;
    // 下载包保存路径
    private String savePath = "";
    // apk保存完整路径
    private String apkFilePath = "";
    // 临时下载文件路径
    private String tmpFilePath = "";
    // 下载文件大小
    private String apkFileSize;
    // 已下载文件大小
    private String tmpFileSize;

    private String curVersionName = "";
    private int curVersionCode;
    private Update updateinfo;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWN_UPDATE: //下载时更新进度
                    mProgress.setProgress(progress);
                    mProgressText.setText(tmpFileSize + "/" + apkFileSize);
                    break;
                case DOWN_OVER:
                    downloadDialog.dismiss();
                    installApk();
                    break;
                case DOWN_NOSDCARD://没有挂载SD卡，无法下载文件
                    downloadDialog.dismiss();
                    ToastUtil.customShow(mContext, "无法下载安装文件，请检查SD卡是否挂载");
                    break;
                case GET_UNDATAINFO_ERROR:
                    //服务器超时
                    Toast.makeText(mContext, "获取服务器更新信息失败", Toast.LENGTH_SHORT).show();
                    break;
                case DOWN_ERROR:
                    //下载apk失败
                    Toast.makeText(mContext, "下载新版本失败", Toast.LENGTH_SHORT).show();
                    break;
                case UPDATA_NONEED:
                    Toast.makeText(mContext, "不需要更新",
                            Toast.LENGTH_SHORT).show();
                    break;
                case UPDATA_CLIENT:
                    showNoticeDialog();

                    break;
            }
        }
    };

    /**
     * 检查App更新
     *
     * @param context
     * @param isShowMsg 是否显示提示消息
     */
    public void checkAppUpdate(Context context, final boolean isShowMsg) {
        this.mContext = context;
        getCurrentVersion();
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateVersion();
            }
        }).start();
    }

    /**
     * 获取当前客户端版本信息
     */
    private void getCurrentVersion() {
        try {
            PackageInfo info = mContext.getPackageManager().getPackageInfo(
                    mContext.getPackageName(), 0);
            curVersionName = info.versionName;
            curVersionCode = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示版本更新通知对话框
     */
    private void showNoticeDialog() {

        final PopupWindow popupWindow;
        View view = LayoutInflater.from(mContext).inflate(R.layout.update_dialog, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        popupWindow.setFocusable(false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        TextView textVersion = (TextView) view.findViewById(R.id.text_version);
        TextView textUpdateMessage = (TextView) view.findViewById(R.id.text_updateMessage);
        TextView textCancle = (TextView) view.findViewById(R.id.text_cancle);
        TextView textUpdate = (TextView) view.findViewById(R.id.text_update);
        View view_Split = (View) view.findViewById(R.id.view_Split);

        textUpdateMessage.setText(updateinfo.getUpdateLog());
        textVersion.setText("[" + updateinfo.getVersionName() + "]  新版上线");
        if (updateinfo.getForceUpdate() != 0) {
            view_Split.setVisibility(View.GONE);
            textCancle.setVisibility(View.GONE);

        }
        textUpdate.setOnClickListener(new View.OnClickListener() {//去更新
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                showDownloadDialog();
            }
        });
        textCancle.setOnClickListener(new View.OnClickListener() {//点击取消
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 显示下载对话框
     */
    private void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("正在下载新版本");
        builder.setIcon(R.mipmap.ic_locate);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.update_progress, null);
        mProgress = (ProgressBar) v.findViewById(R.id.update_progress);
        mProgressText = (TextView) v.findViewById(R.id.update_progress_text);

        builder.setView(v);
        //非强制更新，显示取消按钮
        if (updateinfo.getForceUpdate() == 0) {

            builder.setNegativeButton("取消", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    interceptFlag = true;
                }
            });
            builder.setOnCancelListener(new OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    dialog.dismiss();
                    interceptFlag = true;
                }
            });
        }
        downloadDialog = builder.create();
        downloadDialog.setCancelable(false);
        downloadDialog.show();

        downloadApk();
    }

    private Runnable mdownApkRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                String apkName = "SxzhiboApp_" + updateinfo.getVersionName()
                        + ".apk";
                String tmpApk = "SxzhiboApp_" + updateinfo.getVersionName()
                        + ".tmp";
                // 判断是否挂载了SD卡
                String storageState = Environment.getExternalStorageState();
                if (storageState.equals(Environment.MEDIA_MOUNTED)) {
                    savePath = Environment.getExternalStorageDirectory()
                            .getAbsolutePath() + "/ShangHuTong/Update/";
                    File file = new File(savePath);
                    if (!file.exists()) {
                        file.mkdirs();
                    } else {
                        //删除安装包目录下所有文件
                        File[] files = file.listFiles();
                        if (files != null) {
                            for (int i = 0; i < files.length; i++) {
                                files[i].delete();
                            }
                        }
                    }


                    apkFilePath = savePath + apkName;
                    tmpFilePath = savePath + tmpApk;
                }

                // 没有挂载SD卡，无法下载文件
                if (apkFilePath == null || apkFilePath == "") {
                    mHandler.sendEmptyMessage(DOWN_NOSDCARD);
                    return;
                }

                File ApkFile = new File(apkFilePath);

                // 是否已下载更新文件
                /*if (ApkFile.exists()) {
                    downloadDialog.dismiss();
                    installApk();
                    return;
                }*/

                // 输出临时下载文件
                File tmpFile = new File(tmpFilePath);
                FileOutputStream fos = new FileOutputStream(tmpFile);

                URL url = new URL(updateinfo.getDownloadUrl());
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                // 显示文件大小格式：2个小数点显示
                DecimalFormat df = new DecimalFormat("0.00");
                // 进度条下面显示的总文件大小
                apkFileSize = df.format((float) length / 1024 / 1024) + "MB";

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    // 进度条下面显示的当前下载文件大小
                    tmpFileSize = df.format((float) count / 1024 / 1024) + "MB";
                    // 当前进度值
                    progress = (int) (((float) count / length) * 100);
                    // 更新进度
                    mHandler.sendEmptyMessage(DOWN_UPDATE);
                    if (numread <= 0) {
                        // 下载完成 - 将临时下载文件转成APK文件
                        if (tmpFile.renameTo(ApkFile)) {
                            // 通知安装
                            mHandler.sendEmptyMessage(DOWN_OVER);
                        }
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!interceptFlag);// 点击取消就停止下载

                fos.close();
                is.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    /**
     * 下载apk
     */
    private void downloadApk() {
        downLoadThread = new Thread(mdownApkRunnable);
        downLoadThread.start();
    }

    /**
     * 安装apk
     */
    private void installApk() {
        File apkfile = new File(apkFilePath);
        if (!apkfile.exists()) {
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivityForResult(intent, 0);// 如果用户取消安装的话,
        ((Activity) mContext).startActivityForResult(i, 0);
        // 会返回结果,回调方法onActivityResult
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取服务器的最新版本信息
     */
    private void updateVersion() {
        InputStream inputStream = null;
        try {
            URL url = new URL(Urls.UPDATE_INFO);
            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                // 从服务器获得一个输入流
                inputStream = conn.getInputStream();
            }
            updateinfo = getUpdataInfo(inputStream);
            Log.d("UpdateManager","downloadUrl-----"+updateinfo.getDownloadUrl());
            Log.d("UpdateManager","forceUpdate-----"+updateinfo.getForceUpdate());
            Log.d("UpdateManager","updateLog-----"+updateinfo.getUpdateLog());
            Log.d("UpdateManager","versionName-----"+updateinfo.getVersionName());
            Log.d("UpdateManager","versionCode-----"+updateinfo.getVersionCode());
            if (updateinfo.getVersionCode() == curVersionCode) {
//                Log.i(TAG, "版本号相同");
                Message msg = new Message();
                msg.what = UPDATA_NONEED;
                mHandler.sendMessage(msg);
            } else {
//                Log.i(TAG, "版本号不相同 ");
                Message msg = new Message();
                msg.what = UPDATA_CLIENT;
                mHandler.sendMessage(msg);
            }
        } catch (Exception e) {
            Message msg = new Message();
            msg.what = GET_UNDATAINFO_ERROR;
            mHandler.sendMessage(msg);
            e.printStackTrace();
        }
    }

    /*
 * 用pull解析器解析服务器返回的xml文件 (xml封装了版本号)
 */
    public static Update getUpdataInfo(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");//设置解析的数据源
        int type = parser.getEventType();
        Update update = null;
        while (type != XmlPullParser.END_DOCUMENT) {
            String tag = parser.getName();
            switch (type) {
                case XmlPullParser.START_TAG:
                    // 通知信息
                    if (tag.equalsIgnoreCase("android")) {
                        update = new Update();
                    } else if (update != null) {
                        if (tag.equalsIgnoreCase("versionCode")) {
                            update.setVersionCode(Integer.parseInt(parser.nextText()));
                        } else if (tag.equalsIgnoreCase("versionName")) {
                            update.setVersionName(parser.nextText());
                        } else if (tag.equalsIgnoreCase("downloadUrl")) {
                            update.setDownloadUrl(parser.nextText());
                        } else if (tag.equalsIgnoreCase("updateLog")) {
                            update.setUpdateLog(parser.nextText());
                        } else if (tag.equalsIgnoreCase("forceUpdate")) {
                            update.setForceUpdate(Integer.parseInt(parser.nextText()));
                        }
                    }

                    break;
                case XmlPullParser.END_TAG:
                    break;
            }
            type = parser.next();
        }
        return update;
    }

}
