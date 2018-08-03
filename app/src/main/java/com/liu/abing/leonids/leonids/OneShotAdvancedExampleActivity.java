package com.liu.abing.leonids.leonids;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;

import com.liu.abing.R;

public class OneShotAdvancedExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// Launch 2 particle systems one for each image
//		ParticleSystem ps = new ParticleSystem(this, 100, R.drawable.qy_pic_succ_d, 100,R.id.background_hook);
//		ps.setScaleRange(0.7f, 1.3f);
//		ps.setSpeedRange(0.1f, 0.15f);
//		ps.setAcceleration(0.0001f, 90);
//		ps.setRotationSpeedRange(90, 180);
//		ps.setFadeOut(200, new AccelerateInterpolator());
//		ps.oneShot(arg0, 10);
//
//		ParticleSystem ps1 = new ParticleSystem(this, 100, R.drawable.qy_pic_succ_a, 300,R.id.background_hook);
//		ps1.setScaleRange(0.7f, 1.3f);
//		ps1.setSpeedRange(0.01f, 0.35f);
//		ps1.setAcceleration(0.0001f, 90);
//		ps1.setRotationSpeedRange(90, 180);
//		ps1.setFadeOut(200, new AccelerateInterpolator());
//		ps1.oneShot(arg0, 10);
//		ps1.emit(arg0, 10);
//
//
//		ParticleSystem ps2 = new ParticleSystem(this, 100, R.drawable.qy_pic_succ_i, 500,R.id.background_hook);
//		ps2.setScaleRange(0.7f, 1.3f);
//		ps2.setSpeedRange(0.1f, 0.45f);
//		ps2.setAcceleration(0.0001f, 90);
//		ps2.setRotationSpeedRange(90, 180);
//		ps2.setFadeOut(200, new AccelerateInterpolator());
//		ps2.oneShot(arg0, 10);



		ParticleSystem ps3 = new ParticleSystem(this, 100, R.drawable.qy_pic_succ_c, 800,R.id.background_hook);
		ps3.setScaleRange(0.9f, 1.3f);
		ps3.setSpeedRange(0.1f, 0.25f);
		ps3.setAcceleration(0.0001f, 180);
		ps3.setRotationSpeedRange(0, 180);
		ps3.setFadeOut(200, new AccelerateInterpolator());
		ps3.oneShot(arg0, 50);




	}

}
