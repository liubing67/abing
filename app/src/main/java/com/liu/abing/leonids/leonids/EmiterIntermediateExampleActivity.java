package com.liu.abing.leonids.leonids;


import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;

import com.liu.abing.R;

public class EmiterIntermediateExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {		
		ParticleSystem ps = new ParticleSystem(this, 10, R.drawable.qy_pic_succ_h, 2000);
		ps.setScaleRange(0.7f, 1.3f);
		ps.setSpeedModuleAndAngleRange(0.07f, 0.16f, 0, 180);
		ps.setRotationSpeedRange(90, 180);
		ps.setAcceleration(0.00013f, 90);
		ps.setFadeOut(200, new AccelerateInterpolator());
		ps.emitWithGravity(arg0, Gravity.TOP,10,1000);

		ParticleSystem ps1 = new ParticleSystem(this, 10, R.drawable.qy_pic_succ_a, 2000);
		ps1.setScaleRange(0.7f, 1.3f);
		ps1.setSpeedModuleAndAngleRange(0.07f, 0.16f, 0, 180);
		ps1.setRotationSpeedRange(90, 180);
		ps1.setAcceleration(0.00013f, 90);
		ps1.setFadeOut(200, new AccelerateInterpolator());
		ps1.emitWithGravity(arg0,  Gravity.TOP,10,1000);


		ParticleSystem ps2 = new ParticleSystem(this, 10, R.drawable.qy_pic_succ_b, 2000);
		ps2.setScaleRange(0.7f, 1.3f);
		ps2.setSpeedModuleAndAngleRange(0.07f, 0.16f, 0, 180);
		ps2.setRotationSpeedRange(90, 180);
		ps2.setAcceleration(0.00013f, 90);
		ps2.setFadeOut(20, new AccelerateInterpolator());
		ps2.emitWithGravity(arg0, Gravity.TOP,10,1000);


		ParticleSystem ps3 = new ParticleSystem(this, 10, R.drawable.qy_pic_succ_i, 2000);
		ps3.setScaleRange(0.7f, 1.3f);
		ps3.setSpeedModuleAndAngleRange(0.07f, 0.16f, 0, 180);
		ps3.setRotationSpeedRange(90, 180);
		ps3.setAcceleration(0.00013f, 90);
		ps3.setFadeOut(200, new AccelerateInterpolator());
		ps3.emitWithGravity(arg0, Gravity.TOP,10,1000);
	}

}
