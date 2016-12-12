package com.tools.util.pictures;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * 项目名称：BingYao
 * 类描述： 获取图片的方法
 * 创建人：liubing
 * 创建时间：2016/9/27 9:28
 * 修改人：Administrator
 * 修改时间：2016/9/27 9:28
 * 修改备注：
 */
public class GetPicturesUtil {

    private static Activity activity;
    private static final int GET_BY_ALBUM = 801;//如果有冲突，记得修改
//    private static final int GET_BY_CAMERA = 802;//如果有冲突，记得修改
//    private static final int CROP = 803;//如果有冲突，记得修改
    private static String path;
    private static byte[] mContent;
    public GetPicturesUtil(Activity conten) {
        this.activity = conten;
    }

    /**
     * 调用系统相机拍照
     */
    public static void takePhoto(int requestCode) {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        File vFile = new File(Environment.getExternalStorageDirectory()
                , String.valueOf(System.currentTimeMillis())
                + ".png");//
        if (!vFile.exists()) {
            File vDirPath = vFile.getParentFile();
            vDirPath.mkdirs();
        } else {
            if (vFile.exists()) {
                vFile.delete();
            }
        }
        path = vFile.getPath();
        Uri cameraUri = Uri.fromFile(vFile);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, cameraUri);
        activity.startActivityForResult(openCameraIntent, requestCode);
    }
    /**从相册获取图片*/
    public static void getByAlbum(){
        Intent intentxiangce = new Intent(Intent.ACTION_PICK, null);
        intentxiangce.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*");
        activity.startActivityForResult(intentxiangce, GET_BY_ALBUM);
    }

    /**
     *
     * @param requestCode 请求码
     * @param resultCode 返回结果码
     * @param data 返回数据
     * @param imageView 显示图片的控件
     * @param map 存放图片的map集合
     * @param key 存放图片map集合对应的key值
     */

    public static void onActivityResult(int requestCode, int resultCode, Intent data, ImageView imageView, Map map, String key) {
        if (resultCode == Activity.RESULT_OK) {
           if (requestCode==GET_BY_ALBUM)
           {
               ContentResolver resolver = activity.getContentResolver();
               try {
//              // 获得图片的uri
                   Uri originalUri = data.getData();
                   // 将图片内容解析成字节数组
                   mContent = readStream(resolver.openInputStream(Uri
                           .parse(originalUri.toString())));
                   // 将字节数组转换为ImageView可调用的Bitmap对象
                   Bitmap myBitmap = getPicFromBytes(mContent, null);
                   // //把得到的图片绑定在控件上显示
                   imageView.setImageBitmap(myBitmap);
//                   map.put(key, new File(DeUtils.getCacheUrl(myBitmap)));
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
            else {
               if (!TextUtils.isEmpty(path)) {
                   HelperMethod.setBackgroundDrawable(activity,imageView,path);//在空件中显示图片
                   map.put(key, new File(DeUtils.getCacheUrl(path)));//将压缩后的图片放入map中
               } else {
                   Toast.makeText(activity, "请重新拍摄", Toast.LENGTH_SHORT).show();
               }
           }

        }
    }
    private static Bitmap getPicFromBytes(byte[] bytes,
                                          BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null)
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            else
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return null;
    }

    private static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }
}
