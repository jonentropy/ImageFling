package com.tal.imagefling;

import com.tal.imagefling.view.FlingView;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity {
	private static String TAG = "MainActivity";

	private FlingView fv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		fv = new FlingView(this, this.getResources().getDrawable(R.drawable.ic_launcher));
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);

		if(savedInstanceState != null){
			Log.v(TAG, "create state is not null");
		}
		
		fv.setX(metrics.widthPixels/2);
		fv.setY(metrics.heightPixels/2);

		LayoutParams params = new LayoutParams(1,1);
		params.height = LayoutParams.WRAP_CONTENT;
		params.width = LayoutParams.WRAP_CONTENT;

		this.addContentView(fv, params);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		Log.v(TAG, "on saved called");
		super.onSaveInstanceState(savedInstanceState);
		if(fv != null){
			savedInstanceState.putFloat("ImageX", fv.getX());
			savedInstanceState.putFloat("ImageY", fv.getY());
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.v(TAG, "on restore called");
		super.onRestoreInstanceState(savedInstanceState);
		if(fv != null){
			fv.setX(savedInstanceState.getFloat("ImageX"));
			fv.setY(savedInstanceState.getFloat("ImageY"));
		}
	}

}
