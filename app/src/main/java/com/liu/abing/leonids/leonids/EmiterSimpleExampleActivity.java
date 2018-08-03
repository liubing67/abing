package com.liu.abing.leonids.leonids;


import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

import com.liu.abing.R;

public class EmiterSimpleExampleActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_particle_system_example);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		new ParticleSystem(this, 50, R.drawable.star_pink, 1000)
		.setSpeedRange(0.1f, 0.25f)
		.emit(arg0, 100);
	}

}
