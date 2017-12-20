package com.lionkwon.kwonutils.animation;

import android.view.animation.*;
import android.widget.FrameLayout.LayoutParams;
import android.widget.*;

public class CloseAnimation extends TranslateAnimation implements
		TranslateAnimation.AnimationListener {

	private RelativeLayout slidingLayout;
	int panelWidth;

	public CloseAnimation(RelativeLayout layout, int width, int fromXType,
			float fromXValue, int toXType, float toXValue, int fromYType,
			float fromYValue, int toYType, float toYValue) {

		super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue,
				toYType, toYValue);

		// Initialize
		slidingLayout = layout;
		panelWidth = width;
		setDuration(400);
		setFillAfter(false);
		setInterpolator(new AccelerateDecelerateInterpolator());
		setAnimationListener(this);

		// Clear left and right margins
		LayoutParams params = (LayoutParams) slidingLayout.getLayoutParams();
		params.rightMargin = 0;
		params.leftMargin = 0;
		slidingLayout.setLayoutParams(params);
		slidingLayout.requestLayout();
		slidingLayout.startAnimation(this);

	}

	public void onAnimationEnd(Animation animation) {

	}

	public void onAnimationRepeat(Animation animation) {

	}

	public void onAnimationStart(Animation animation) {

	}

}
