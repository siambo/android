package com.weazr.intent.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.weazr.main.R;
import com.weazr.tabs.TenDayForcastFragment;
import com.weazr.tabs.WeazrListAdapter;
import com.weazrapi.model.NowForcast;
import com.weazrapi.model.TenDayForcast;
import com.weazrapi.model.Weather;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class TenDayWeatherIntentHandler implements IIntentHandler {

	private static final String TAG = "TenDayWeatherIntentHandler";
	
	private TenDayForcastFragment fragment;
	
	private WeazrListAdapter adapter;
	
	public TenDayWeatherIntentHandler(Fragment fragment){
		try{
			if(fragment instanceof TenDayForcastFragment)
			     this.fragment = (TenDayForcastFragment)fragment;
		}catch(Exception e){
			Log.e(TAG,"error "+e.getLocalizedMessage());
		}
	}
	
	@Override
	public void handle(Intent intent) {
		Log.d(TAG, "TenDayWeatherIntentHandler()");
		
		TenDayForcast tenDayForcast =(TenDayForcast)intent.getExtras().getParcelable("com.weazrapi.model.TenDayForcast");
		
		fragment.makeProgressBarLayoutGone();

		List<Weather> weazrList = tenDayForcast.getWeatherList();

		adapter = new WeazrListAdapter(fragment, weazrList);
		fragment.getListView().setAdapter(adapter);
		
	}
}
