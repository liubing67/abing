package com.abing.androidnote.androida笔记.新建项目所需文件.java.tools.utils.UploadUtil;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/1/15 0015.
 */
public class HttpPostUpFile {
    final static String ENCORDING = "UTF-8";

    public static String uploadMultiple(String actionUrl, Map<String, File> files) throws Exception {
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";
        URL uri = new URL(actionUrl);
        HttpURLConnection conn = (HttpURLConnection) uri.openConnection();
        conn.setReadTimeout(5 * 1000);
        conn.setDoInput(true);// 允许输入
        conn.setDoOutput(true);// 允许输出
        conn.setUseCaches(false);
        conn.setRequestMethod("POST"); // Post方式
        conn.setRequestProperty("connection", "keep-alive");
        conn.setRequestProperty("Charsert", "UTF-8");
        conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
                + ";boundary=" + BOUNDARY);

        DataOutputStream outStream = new DataOutputStream(
                conn.getOutputStream());
        // 发送文件数据
        if (files != null)
            for (Map.Entry<String, File> file : files.entrySet()) {
                // 组拼文本类型的参数
                StringBuilder sb = new StringBuilder();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
                        + file.getKey() + "\"" + LINEND);
                sb.append("Content-Type: multipart/form-data; charset="
                        + CHARSET + LINEND);
                sb.append(LINEND);
                outStream.write(sb.toString().getBytes());
                InputStream is = new FileInputStream(file.getValue());
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                is.close();
                outStream.write(LINEND.getBytes());
            }
        // 请求结束标志
        byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
        outStream.write(end_data);
        outStream.flush();
        // 得到响应码
        boolean success = conn.getResponseCode() == 200;
        InputStream in = conn.getInputStream();
        InputStreamReader isReader = new InputStreamReader(in);
        BufferedReader bufReader = new BufferedReader(isReader);
        String line;
        String data = "";
        while ((line = bufReader.readLine()) != null)
            data += line;
        data = data.replace("null", "");
        outStream.close();
        conn.disconnect();
        Log.e("httppostupfile success", success + "");
        Log.e("httppostupfile data", data + "");

        JSONObject json = new JSONObject();
        json.put("success", success);
        json.put("data", data);
        return json.toString();
    }

    public static String uploadSingle(String actionUrl, String filepath) throws Exception {
        String boundary = "---------------------------7db1c523809b2";
        // 分割线
        File file = new File(filepath);
        // 用来解析主机名和端口
        URL url = new URL(actionUrl + "?r=" + (new Random().nextInt(1000)));
        // 用来开启连接
        StringBuilder sb = new StringBuilder();
        // 文件部分
        sb.append("--" + boundary + "\r\n");
        sb.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + filepath + "\"" + "\r\n");
        sb.append("Content-Type: application/octet-stream" + "\r\n");
        sb.append("\r\n");
        // 将开头和结尾部分转为字节数组，因为设置Content-Type时长度是字节长度
        byte[] before = sb.toString().getBytes(ENCORDING);
        byte[] after = ("\r\n--" + boundary + "--\r\n").getBytes(ENCORDING);
        // 打开连接, 设置请求头
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(10000);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
//        conn.setRequestProperty("Content-Length", before.length + file.length() + after.length + "");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        // 获取输入输出流
        OutputStream out = conn.getOutputStream();
        FileInputStream fis = new FileInputStream(file);
        // 将开头部分写出
        out.write(before);
        // 写出文件数据
        byte[] buf = new byte[1024 * 15];
        int len;
        while ((len = fis.read(buf)) != -1)
            out.write(buf, 0, len);
        // 将结尾部分写出
        out.write(after);
        InputStream in = conn.getInputStream();
        InputStreamReader isReader = new InputStreamReader(in);
        BufferedReader bufReader = new BufferedReader(isReader);
        String line;
        String data = "";
        while ((line = bufReader.readLine()) != null)
            data += line;
        data = data.replace("null", "");
        Log.e("fromServer", "result=" + data);
        boolean success = conn.getResponseCode() == 200;
        in.close();
        fis.close();
        out.close();
        conn.disconnect();
        Log.e("httppostupfile success", success + "");
        Log.e("httppostupfile data", data + "");

        JSONObject json = new JSONObject();
        json.put("success", success);
        json.put("data", data);
        return json.toString();
    }
}
