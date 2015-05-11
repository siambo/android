package com.weazrapi.webservice;

import com.weazrapi.TenDayForcastListener;
import com.weazrapi.model.TenDayForcast;
import com.weazrapi.parser.TenDayForcastParser;

import android.util.Log;

public class TenDayWebServiceRunnable extends WebServiceRunnable {

	private static final String TAG = TenDayWebServiceRunnable.class.getSimpleName();
	
	private TenDayForcast tenDayForcast;
	private TenDayForcastParser tenDayForcastParser;
	private TenDayForcastListener tenDayForcastListener;
	
	public TenDayWebServiceRunnable(String dataLocation, TenDayForcastListener tenDayForcastListener) {
		super(dataLocation);
		this.tenDayForcastListener = tenDayForcastListener;
		this.tenDayForcast = new TenDayForcast();
	}

	@Override
	public void run() {
		
		try{
			weatherRequestResponseJson = webServiceDataImporter.getRemoteData();
			tenDayForcastParser = new TenDayForcastParser(tenDayForcast, weatherRequestResponseJson);
			tenDayForcastParser.parse();
			tenDayForcastListener.onForcastReceived(tenDayForcast);
								
		} catch (Exception e) {
			Log.e(TAG,"10 day forcast thread error: "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
