package com.weazrapi.webservice;

import android.content.Context;

public class WebServiceRunnable implements Runnable {

	@SuppressWarnings("unused")
	private static final String TAG = "WeatherNowRunnable";
	
	protected String nowWeatherJsonResponse;
	@SuppressWarnings("unused")
	private String dataLocation;
	protected Context context;

	protected WebServiceDataImporter webServiceDataImporter;
	
	public WebServiceRunnable(Context context, String dataLocation){
		this.context = context;
		this.dataLocation = dataLocation;
		this.webServiceDataImporter = new WebServiceDataImporter(dataLocation);
	}
	
	@Override
	public void run() {

	}

}
