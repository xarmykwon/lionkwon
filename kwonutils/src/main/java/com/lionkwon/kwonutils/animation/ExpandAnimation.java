package com.lionkwon.kwonutils.animation;

import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;

public class ExpandAnimation extends TranslateAnimation implements
		Animation.AnimationListener {

	private RelativeLayout slidingLayout;
	int panelWidth;
	String isExpanded;

	public ExpandAnimation(RelativeLayout layout, int width, String isExpanded,
			int fromXType, float fromXValue, int toXType, float toXValue,
			int fromYType, float fromYValue, int toYType, float toYValue) {

		super(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue,
				toYType, toYValue);
		this.isExpanded = isExpanded;
		// Initialize
		slidingLayout = layout;
		panelWidth = width;
		setDuration(300);
		setFillAfter(false);
		setInterpolator(new AccelerateDecelerateInterpolator());
		setAnimationListener(this);
		slidingLayout.startAnimation(this);
	}

	public void onAnimationEnd(Animation arg0) {

		// Create margin and align left
		LayoutParams params = (LayoutParams) slidingLayout.getLayoutParams();

		if (isExpanded.equals("left")) {
			params.leftMargin = panelWidth;
			params.gravity = Gravity.LEFT;

		} else {

			params.rightMargin = panelWidth;
			params.gravity = Gravity.RIGHT;

		}
		slidingLayout.clearAnimation();
		slidingLayout.setLayoutParams(params);
		slidingLayout.requestLayout();

	}

	public void onAnimationRepeat(Animation arg0) {

	}

	public void onAnimationStart(Animation arg0) {

	}

}
