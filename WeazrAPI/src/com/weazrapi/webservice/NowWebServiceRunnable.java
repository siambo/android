package com.weazrapi.webservice;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.weazrapi.intents.WeazrIntentActionConstant;
import com.weazrapi.model.NowForcast;
import com.weazrapi.parser.NowForcastParser;

public class NowWebServiceRunnable extends WebServiceRunnable{

	private static final String TAG = "NowWebServiceRunnable";
	
	private NowForcast nowForcast;
	
	public NowWebServiceRunnable(Context context, NowForcast nowForcast, String dataLocation) {
		super(context,dataLocation);
		this.nowForcast = nowForcast;
	}

	@Override
	public void run() {
		
		try{
			nowWeatherJsonResponse = webServiceDataImporter.getRemoteData();
			NowForcastParser parser = new NowForcastParser(nowForcast, nowWeatherJsonResponse);
			parser.parse();
			
			Intent nowWeatherIntent = new Intent(WeazrIntentActionConstant.ACTION_WEATHER_NOW_ARRIVED);
			nowWeatherIntent.putExtra("com.weazrapi.model.NowForcast",nowForcast);
			context.sendBroadcast(nowWeatherIntent, WeazrIntentActionConstant.ACTION_PERMISSION_WEAZR);
			
		}catch(Exception e){
			Log.e(TAG, "error NowWeatherServiceRunnable() "+e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	}
}
