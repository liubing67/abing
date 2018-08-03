package com.liu.abing.leonids.leonids;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.liu.abing.R;

public class EmiterBackgroundSimpleExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		new ParticleSystem(this, 50, R.drawable.star_pink, 1000, R.id.background_hook)
		.setSpeedRange(0, 0.15f)
		.emit(arg0, 5);

		new ParticleSystem(this, 50, R.drawable.qy_pic_succ_a, 1000, R.id.background_hook)
				.setSpeedRange(0, 0.25f)
				.emit(arg0, 5);


		new ParticleSystem(this, 50, R.drawable.qy_pic_succ_b, 1000, R.id.background_hook)
				.setSpeedRange(0, 0.35f)
				.emit(arg0, 5);

		new ParticleSystem(this, 50, R.drawable.qy_pic_succ_d, 1000, R.id.background_hook)
				.setSpeedRange(0, 0.45f)
				.emit(arg0, 5);

		new ParticleSystem(this, 50, R.drawable.qy_pic_succ_i, 1000, R.id.background_hook)
				.setSpeedRange(0, 0.55f)
				.emit(arg0, 5);
	}

}
