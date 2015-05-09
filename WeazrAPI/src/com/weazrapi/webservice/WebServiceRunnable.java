package com.weazrapi.webservice;

import android.util.Log;

public class WebServiceRunnable implements Runnable {

	private static final String TAG = WebServiceRunnable.class.getSimpleName();
	
	protected String weatherRequestResponseJson;
	@SuppressWarnings("unused")
	private String dataLocation;

	protected WebServiceDataImporter webServiceDataImporter;
	
	public WebServiceRunnable(String dataLocation){
		this.dataLocation = dataLocation;
		this.webServiceDataImporter = new WebServiceDataImporter(dataLocation);
		Log.i(TAG, ": created");
	}
	
	@Override
	public void run() {

	}

}
