package com.liu.abing.handwrite;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.liu.abing.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandscapeActivity extends Activity {
    @BindView(R.id.view)
    LinePathView pathView;
    @BindView(R.id.clear1)
    Button mClear;
    @BindView(R.id.save1)
    Button mSave;
    @BindView(R.id.ll)
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hand_write);
        ButterKnife.bind(this);
        setResult(50);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (pathView.getTouched())
            {
                try {
                    pathView.save(SignUsActivity.path1,false,10);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                setResult(101);
                finish();
            }else
            {
                Toast.makeText(LandscapeActivity.this,"您没有签名~",Toast.LENGTH_SHORT).show();
            }
        }});
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pathView.clear();
            }
        });
    }



}
