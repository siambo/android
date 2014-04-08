package com.weazr.intent.manager;

import java.util.HashMap;
import com.weazrapi.intents.WeazrIntentActionConstant;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;

public class MainBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "MainBroadcastReceiver";
	
	
	@SuppressWarnings("unused")
	private Fragment fragment;
	private HashMap<String,IIntentHandler> intentHandlerPool;
	
	public MainBroadcastReceiver(Fragment fragment){
		this.fragment = fragment;
		intentHandlerPool = new HashMap<String,IIntentHandler>();
		intentHandlerPool.put(WeazrIntentActionConstant.ACTION_WEATHER_NOW_ARRIVED, new NowWeatherIntentHandler(fragment));
		intentHandlerPool.put(WeazrIntentActionConstant.ACTION_10DAY_FORCAST_ARRIVED, new TenDayWeatherIntentHandler(fragment));
	}
	
	
	@Override
	public void onReceive(Context context, Intent intent) {	
		try{
			Log.d(TAG, "onReceive()"+intent.getAction());
			IIntentHandler nowIntentHandler = intentHandlerPool.get(intent.getAction());
			nowIntentHandler.handle(intent);
		}catch(Exception e){
			Log.e(TAG, "onReceive(): "+e.getLocalizedMessage()+"\n");
			e.printStackTrace();
			
			// suppressed null intent payload exception: need to be fixed e.printStackTrace();
			//figure out why multiple intents are coming for one call
			//null intent payload exception coming from after the second intent
		}
	}

}
