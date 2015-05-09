package com.weazrapi.webservice;

import android.util.Log;
import com.weazrapi.ForcastListener;
import com.weazrapi.model.NowForcast;
import com.weazrapi.parser.NowForcastParser;

public class NowWebServiceRunnable extends WebServiceRunnable{

	private static final String TAG = NowWebServiceRunnable.class.getSimpleName();
	
	private NowForcast nowForcast;
	private ForcastListener forcastListener;
	
	public NowWebServiceRunnable(String dataLocation, ForcastListener forcastListener) {
		super(dataLocation);
		this.nowForcast = new NowForcast();
		this.forcastListener = forcastListener;
	}

	@Override
	public void run() {
		
		try{
			weatherRequestResponseJson = webServiceDataImporter.getRemoteData();
			NowForcastParser parser = new NowForcastParser(nowForcast, weatherRequestResponseJson);
			parser.parse();
			forcastListener.onForcastReceived(nowForcast);
				
		}catch(Exception e){
			Log.e(TAG, "error NowWeatherServiceRunnable() "+e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	}
}
