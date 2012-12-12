package com.tal.imagefling.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

public class FlingView extends ImageView {
	private static String TAG = "Flinger";
	
	private float lastX = 0.0f;
	private float lastY = 0.0f;

	private float lastXdiff = 0.0f;
	private float lastYdiff = 0.0f;

	@Override
	public void setX(float x){
		View parent = (View)getParent();
		if(parent != null){
			int width = parent.getWidth();
			if(x < 0){
				Log.v(TAG, "OFF EDGE");
				super.setX(0);
				return;
			}
			if(x > width - getWidth()){
				Log.v(TAG, "OFF EDGE");
				super.setX(width - getWidth());
				return;	
			}
		}
		super.setX(x);
	}

	@Override
	public void setY(float y){
		View parent = (View)getParent();
		if(parent != null){
			int height = parent.getHeight();
			if(y < 0){
				Log.v(TAG, "OFF EDGE");
				super.setY(0);
				return;
			}
			if(y > height - getHeight()){
				Log.v(TAG, "OFF EDGE");
				super.setY(height - getHeight());
				return;	
			}
		}
		super.setY(y);
	}

	public FlingView(Context context, Drawable d) {
		super(context);

		//ToDo: Replace this deprecated method when targeting API level 16+.
		this.setBackgroundDrawable(d);

		this.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				final float newX = event.getRawX();
				final float newY = event.getRawY();

				switch(event.getAction()){
				case MotionEvent.ACTION_UP:
					//fling it good...
					Log.v(TAG, "Flinging");

					AnimatorSet flinger = new AnimatorSet();

					ObjectAnimator animatorX = ObjectAnimator.ofFloat(v, "X", getX() + lastXdiff*10);				
					ObjectAnimator animatorY = ObjectAnimator.ofFloat(v, "Y", getY() + lastYdiff*10);

					flinger.play(animatorX).with(animatorY);
					flinger.setInterpolator(new DecelerateInterpolator());
					flinger.start();

					break;

				case MotionEvent.ACTION_MOVE:	

					//distance moved
					final float dx = newX - lastX;
					final float dy = newY - lastY;

					setX(getX() + dx);
					setY(getY() + dy);

					lastXdiff = dx;
					lastYdiff = dy;

				case MotionEvent.ACTION_DOWN:
					lastX = newX;
					lastY = newY;
					break;
				}

				return true;
			}
		});
	}
}
