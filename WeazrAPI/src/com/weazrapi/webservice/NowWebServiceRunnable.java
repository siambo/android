package com.weazrapi.webservice;

import android.util.Log;
import com.weazrapi.NowForcastListener;
import com.weazrapi.model.NowForcast;
import com.weazrapi.parser.NowForcastParser;

public class NowWebServiceRunnable extends WebServiceRunnable{

	private static final String TAG = NowWebServiceRunnable.class.getSimpleName();
	
	private NowForcast nowForcast;
	private NowForcastListener nowForcastListener;
	
	public NowWebServiceRunnable(String dataLocation, NowForcastListener nowForcastListener) {
		super(dataLocation);
		this.nowForcast = new NowForcast();
		this.nowForcastListener = nowForcastListener;
	}

	@Override
	public void run() {
		
		try{
			weatherRequestResponseJson = webServiceDataImporter.getRemoteData();
			NowForcastParser parser = new NowForcastParser(nowForcast, weatherRequestResponseJson);
			parser.parse();
			nowForcastListener.onForcastReceived(nowForcast);
				
		}catch(Exception e){
			Log.e(TAG, "error NowWeatherServiceRunnable() "+e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	}
}
