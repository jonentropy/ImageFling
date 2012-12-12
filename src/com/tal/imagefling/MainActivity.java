package com.tal.imagefling;

import com.tal.imagefling.view.FlingView;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		FlingView fv = new FlingView(this, this.getResources().getDrawable(R.drawable.ic_launcher));
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
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

}
