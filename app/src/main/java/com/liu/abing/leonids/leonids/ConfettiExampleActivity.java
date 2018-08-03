package com.liu.abing.leonids.leonids;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.liu.abing.R;

public class ConfettiExampleActivity extends Activity implements OnClickListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confetti_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {		
//		new ParticleSystem(this, 50, R.drawable.confeti2, 10000)
//		.setSpeedModuleAndAngleRange(0f, 0.1f, 180, 180)
//		.setRotationSpeed(144)
//		.setAcceleration(0.000017f, 90)
//		.emit(findViewById(R.id.emiter_top_right), 8);
//
//		new ParticleSystem(this, 50, R.drawable.confeti3, 10000)
//		.setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
//		.setRotationSpeed(144) //旋转速度。
//		.setAcceleration(0.000017f, 90)
//		.emit(findViewById(R.id.emiter_top_left), 8);

		new ParticleSystem(this, 10, R.drawable.petal_0, 10000)
				.setSpeedModuleAndAngleRange(0f, 0.1f, 270, 180)
				.setRotationSpeed(144)
				.setAcceleration(0.000017f, 270)
				.setScaleRange(0.5f, 0.7f)//缩放 图片
				.emit(findViewById(R.id.emiter_bottom_left), 8);

		new ParticleSystem(this, 70, R.drawable.petal_1, 10000)
				.setSpeedModuleAndAngleRange(0f, 0.1f, 270, 180)
				.setRotationSpeed(144)
				.setAcceleration(0.000017f, 270)
				.setScaleRange(0.3f, 0.5f)//缩放 图片
				.emit(findViewById(R.id.emiter_bottom_left), 8);

		new ParticleSystem(this, 20, R.drawable.petal_2, 10000)
				.setSpeedModuleAndAngleRange(0f, 0.1f, 270, 180)
				.setRotationSpeed(144)
				.setAcceleration(0.000017f, 270)
				.setScaleRange(0.4f, 0.6f)//缩放 图片
				.emit(findViewById(R.id.emiter_bottom_left), 8);


//		new ParticleSystem(this, 50, R.drawable.confeti3, 10000)
//		.setSpeedModuleAndAngleRange(0f, 0.1f, 0, 0)
//		.setRotationSpeed(144) //旋转速度。
//		.setAcceleration(0.000017f, 90)
//		.emit(findViewById(R.id.emiter_top_left), 8);
	}
}
