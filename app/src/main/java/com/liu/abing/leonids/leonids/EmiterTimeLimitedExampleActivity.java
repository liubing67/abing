package com.liu.abing.leonids.leonids;


import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;

import com.liu.abing.R;

public class EmiterTimeLimitedExampleActivity extends Activity implements OnClickListener {
	ParticleSystem partOne,partTwo,partThree,partfour,partFive,partSix,partSev,partEig,partNin;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
//				new ParticleSystem(this, 80, R.drawable.confeti2, 10000)
//						.setSpeedByComponentsRange(0f, 0.002f, 0.01f, 0.05f)
//		.setRotationSpeed(144)
//		.setAcceleration(0.000017f, 90)
//		.emit(findViewById(R.id.emiter_top_right), 8);
//
//		new ParticleSystem(this, 10, R.drawable.confeti3, 10000)
//				.setSpeedByComponentsRange(0f, 0.002f, 0.01f, 0.05f)
//		.setRotationSpeed(144) //旋转速度。
//		.setAcceleration(0.000017f, 90)
//		.emit(findViewById(R.id.emiter_top_left), 8);

		partOne = new ParticleSystem(this, 20, R.drawable.scraps_1, 10000);
		partOne.setSpeedByComponentsRange(0f, 0.002f, 0.01f, 0.05f)
				.setRotationSpeed(140)
				.setScaleRange(0.2f, 0.3f)//缩放 图片
				.setAcceleration(0.000015f, 90)
				.setSpeedModuleAndAngleRange(0.007f, 0.05f, 0, 180)//速度控制 左右活动
				.emitWithGravity(findViewById(R.id.emiter_top), Gravity.CENTER, 2);

		partTwo = new ParticleSystem(this, 10, R.drawable.scraps_2, 10000);//粒子最大数 活动时间
		partTwo.setRotationSpeed(140)//旋转幅度
				.setScaleRange(0.2f, 0.3f)//缩放 图片
				.setSpeedModuleAndAngleRange(0.007f, 0.05f, 0, 180)//速度控制 左右活动
				.setAcceleration(0.000015f, 90)//速度
				.emitWithGravity(findViewById(R.id.emiter_top), Gravity.CENTER, 1);//显示位置

		partThree = new ParticleSystem(this, 10, R.drawable.scraps_3, 10000);//粒子最大数 活动时间
		partThree.setRotationSpeed(140)//旋转幅度
				.setScaleRange(0.2f, 0.3f)//缩放 图片
				.setSpeedModuleAndAngleRange(0.007f, 0.05f, 0, 180)//速度控制 左右活动
				.setAcceleration(0.000015f, 90)//速度
				.emitWithGravity(findViewById(R.id.emiter_top), Gravity.CENTER, 1);//显示位置

		partfour = new ParticleSystem(this, 10, R.drawable.scraps_4, 10000);//粒子最大数 活动时间
		partfour.setRotationSpeed(140)//旋转幅度
				.setScaleRange(0.2f, 0.3f)//缩放 图片
				.setSpeedModuleAndAngleRange(0.007f, 0.05f, 0, 180)//速度控制 左右活动
				.setAcceleration(0.000015f, 90)//速度
				.emitWithGravity(findViewById(R.id.emiter_top), Gravity.CENTER, 1);//显示位置

		partFive = new ParticleSystem(this, 10, R.drawable.scraps_5, 10000);//粒子最大数 活动时间
		partFive.setRotationSpeed(140)//旋转幅度
				.setScaleRange(0.2f, 0.3f)//缩放 图片
				.setSpeedModuleAndAngleRange(0.007f, 0.05f, 0, 180)//速度控制 左右活动
				.setAcceleration(0.000015f, 90)//速度
				.emitWithGravity(findViewById(R.id.emiter_top), Gravity.CENTER, 1);//显示位置

		partSix = new ParticleSystem(this, 10, R.drawable.scraps_6, 10000);//粒子最大数 活动时间
		partSix.setRotationSpeed(140)//旋转幅度
				.setScaleRange(0.2f, 0.3f)//缩放 图片
				.setSpeedModuleAndAngleRange(0.007f, 0.05f, 0, 180)//速度控制 左右活动
				.setAcceleration(0.000015f, 90)//速度
				.emitWithGravity(findViewById(R.id.emiter_top), Gravity.CENTER, 1);//显示位置

		partSev = new ParticleSystem(this, 10, R.drawable.scraps_7, 10000);//粒子最大数 活动时间
		partSev.setRotationSpeed(140)//旋转幅度
				.setScaleRange(0.2f, 0.3f)//缩放 图片
				.setSpeedModuleAndAngleRange(0.007f, 0.05f, 0, 180)//速度控制 左右活动
				.setAcceleration(0.000015f, 90)//速度
				.emitWithGravity(findViewById(R.id.emiter_top), Gravity.CENTER, 1);//显示位置

		partEig = new ParticleSystem(this, 10, R.drawable.scraps_8, 10000);//粒子最大数 活动时间
		partEig.setRotationSpeed(140)//旋转幅度
				.setScaleRange(0.2f, 0.3f)//缩放 图片
				.setSpeedModuleAndAngleRange(0.007f, 0.05f, 0, 180)//速度控制 左右活动
				.setAcceleration(0.000015f, 90)//速度
				.emitWithGravity(findViewById(R.id.emiter_top), Gravity.CENTER, 1);//显示位置

		partNin = new ParticleSystem(this, 10, R.drawable.scraps_9, 10000);//粒子最大数 活动时间
		partNin.setRotationSpeed(140)//旋转幅度
				.setScaleRange(0.2f, 0.3f)//缩放 图片
				.setSpeedModuleAndAngleRange(0.007f, 0.05f, 0, 180)//速度控制 左右活动
				.setAcceleration(0.000015f, 90)//速度
				.emitWithGravity(findViewById(R.id.emiter_top), Gravity.CENTER, 1);//显示位置
	}

	@Override
	protected void onStop() {
		super.onStop();
		partOne.cancel();
		partTwo.cancel();
	}
}
