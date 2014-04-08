package com.weazrapi.webservice;

import com.weazrapi.intents.WeazrIntentActionConstant;
import com.weazrapi.model.TenDayForcast;
import com.weazrapi.parser.TenDayForcastParser;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TenDayWebServiceRunnable extends WebServiceRunnable {

	private static final String TAG = "TenDayWebServiceRunnable";
	
	private TenDayForcast tenDayForcast;
	
	public TenDayWebServiceRunnable(Context context, TenDayForcast tenDayForcast, String dataLocation) {
		super(context, dataLocation);
		this.tenDayForcast = tenDayForcast;
	}

	@Override
	public void run() {
		
		try{
			nowWeatherJsonResponse = webServiceDataImporter.getRemoteData();
			TenDayForcastParser parser = new TenDayForcastParser(tenDayForcast, nowWeatherJsonResponse);
			parser.parse();
			
			Intent tenDayWeatherIntent = new Intent(WeazrIntentActionConstant.ACTION_10DAY_FORCAST_ARRIVED);
			tenDayWeatherIntent.putExtra("com.weazrapi.model.TenDayForcast",tenDayForcast);
			context.sendBroadcast(tenDayWeatherIntent, WeazrIntentActionConstant.ACTION_PERMISSION_WEAZR);
								
		} catch (Exception e) {
			Log.e(TAG,"Background error: "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
