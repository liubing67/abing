package com.liu.abing.leonids.leonids;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.liu.abing.R;


public class OneShotSimpleExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.background_hook).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		new ParticleSystem(this, 100, R.drawable.qy_pic_succ_a, 800)
		.setSpeedRange(0f, 0.25f)
		.oneShot(arg0, 10);

		new ParticleSystem(this, 100, R.drawable.qy_pic_succ_i, 800)
				.setSpeedRange(0.1f, 0.45f)
				.oneShot(arg0, 10);

		new ParticleSystem(this, 100, R.drawable.qy_pic_succ_d, 800)
				.setSpeedRange(0.1f, 0.55f)
				.oneShot(arg0, 10);
	}

}
