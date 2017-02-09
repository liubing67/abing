package com.abing.liudialog_lib.animation.BounceEnter;

import android.animation.ObjectAnimator;
import android.util.DisplayMetrics;
import android.view.View;

import com.abing.liudialog_lib.animation.BaseAnimatorSet;


public class BounceLeftEnter extends BaseAnimatorSet {
	public BounceLeftEnter() {
		duration = 1000;
	}

	@Override
	public void setAnimation(View view) {

		DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
		animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1, 1),//
				ObjectAnimator.ofFloat(view, "translationX", -250 * dm.density, 30, -10, 0));
	}
}
