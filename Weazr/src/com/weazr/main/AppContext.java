package com.weazr.main;

import com.weazrapi.WeazrService;
import com.weazrapi.model.Forcast;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
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
