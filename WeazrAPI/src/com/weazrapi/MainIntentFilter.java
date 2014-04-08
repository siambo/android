package com.weazrapi;

import com.weazrapi.intents.WeazrIntentActionConstant;

import android.content.IntentFilter;

public class MainIntentFilter extends IntentFilter {


	public IntentFilter getMainIntentFilter(){
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(WeazrIntentActionConstant.ACTION_WEATHER_NOW_ARRIVED);
		intentFilter.addAction(WeazrIntentActionConstant.ACTION_10DAY_FORCAST_ARRIVED);
		intentFilter.addAction(WeazrIntentActionConstant.ACTION_USER_LOCATION_ARRIVED_AVAILABLE);
		intentFilter.addAction(WeazrIntentActionConstant.ACTION_USER_LOCATION_ARRIVED_NOT_AVAILABLE);
		return intentFilter;
	}
}
