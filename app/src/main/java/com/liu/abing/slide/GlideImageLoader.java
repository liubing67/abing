package com.liu.abing.slide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.loader.ImageLoader;


public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext())
                .load(path)
                .crossFade()
//                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
//                .error(R.mipmap.banner)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }
    public void clearCache(Context context){
        Glide.get(context.getApplicationContext()).clearMemory();//清理内存缓存  可以在UI主线程中进行
    }

}
