package com.weazr.main;

import com.weazrapi.WeazrService;
import android.app.Application;
import android.util.Log;

public class AppContext extends Application {

	private static final String TAG = AppContext.class.getSimpleName();
	
	private static WeazrService weazrService;
	
	@Override
	public void onCreate() {
		super.onCreate();
	    Log.i(TAG, " created");
	}
	
	public static WeazrService getWeazrService() {
		return weazrService;
	}
	
	public static void setWeazrService(WeazrService weazrService) {
		AppContext.weazrService = weazrService;
	}
}
