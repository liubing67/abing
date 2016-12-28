package com.liu.abing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.liu.abing.base.BaseActivity;
import com.tools.util.FastBlur;
import com.tools.util.pictures.BitmapUtil;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2016/12/28 21:02
 * 修改人：Administrator
 * 修改时间：2016/12/28 21:02
 * 修改备注：
 */
public class FastBlurActivity extends BaseActivity {

    private ImageView iv_splash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastblur);

        initView();
    }
    private void initView()
    {
        Button button2 = (Button)findViewById(R.id.button);
        iv_splash=(ImageView) findViewById(R.id.iv_splash);
        new AsycMakeBitmap().execute();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsycMakeBitmap().execute();
            }
        });
    }
    class AsycMakeBitmap extends AsyncTask<String, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... params) {
//            iv_splash.setDrawingCacheEnabled(true);
//            iv_splash.buildDrawingCache(true);
//            Bitmap bmp1 = iv_splash.getDrawingCache();
            Bitmap  bmp1 = BitmapFactory.decodeResource(getResources(), R.mipmap.erha);
            return FastBlur.doBlur(bmp1, 10, false);
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            iv_splash.setBackgroundDrawable(new BitmapDrawable(result));
        }

    }

}
