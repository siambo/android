package com.weazrapi;

import com.example.weazrapi.R;
import com.weazrapi.location.WeazrLocationManager;
import com.weazrapi.model.UserLocation;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class Main extends Activity {
	
	private static final String TAG = "Main";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG,"onCreate called");
		
		WeazrLocationManager weatherLocationService = new WeazrLocationManager(getApplicationContext());
		UserLocation userLocation = weatherLocationService.getLocation();
		Log.e(TAG, ":"+userLocation.getCityName()+", "+userLocation.getCountryCode());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
