package com.liu.abing.leonids.leonids;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import com.liu.abing.R;

public class FollowCursorExampleActivity extends Activity implements View.OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qipao);
		findViewById(R.id.button1).setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {

		new ParticleSystem(this, 60, R.drawable.animation_bubble, 10000)
				.setSpeedModuleAndAngleRange(0f, 0.1f, 270, 180)
				.setRotationSpeed(144)
				.setAcceleration(0.000017f, 270)
				.setScaleRange(0.5f, 0.7f)
				.emit(findViewById(R.id.emiter_right), 4);

		new ParticleSystem(this, 50, R.drawable.animation_bubble, 10000)
				.setSpeedModuleAndAngleRange(0f, 0.1f, 270, 180)
				.setRotationSpeed(144)
				.setAcceleration(0.000017f, 270)
				.setScaleRange(0.5f, 0.7f)
				.emit(findViewById(R.id.emiter_left), 4);

	}

	
	
}