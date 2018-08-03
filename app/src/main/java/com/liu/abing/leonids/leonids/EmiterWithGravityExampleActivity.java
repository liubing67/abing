package com.liu.abing.leonids.leonids;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;

import com.liu.abing.R;

public class EmiterWithGravityExampleActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.snow);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		new ParticleSystem(this, 100, R.drawable.snow, 3000)//粒子最大数 活动时间
		.setRotationSpeed(140)//旋转幅度
		.setScaleRange(0.7f, 1.3f)//缩放 图片
		.setSpeedModuleAndAngleRange(0.007f, 0.05f, 0, 180)//速度控制 左右活动
		.setAcceleration(0.000015f, 90)//速度
		.setFadeOut(200, new AccelerateInterpolator())
		.emitWithGravity(findViewById(R.id.emiter_top), Gravity.TOP, 5);//显示位置

//		new ParticleSystem(this, 80, R.drawable.snow, 10000)
//				.setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
//				.setRotationSpeed(144)
//		.setAcceleration(0.000015f, 90)
//		.emit(findViewById(R.id.emiter_top_right), 5);
//
//		new ParticleSystem(this, 30, R.drawable.snow, 10000)
//				.setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
//				.setRotationSpeed(144) //旋转速度。
//		.setAcceleration(0.000015f, 90)
//		.emit(findViewById(R.id.emiter_top_left), 5);

	}
}
