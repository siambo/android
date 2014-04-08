package com.weazr.intent.manager;

import com.weazrapi.intents.WeazrIntentActionConstant;

import android.content.IntentFilter;

public class MainIntentFilter extends IntentFilter {

	public static IntentFilter getMainIntentFilter(){
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(WeazrIntentActionConstant.ACTION_WEATHER_NOW_ARRIVED);
		intentFilter.addAction(WeazrIntentActionConstant.ACTION_10DAY_FORCAST_ARRIVED);
		return intentFilter;
	}
}
