package com.weazrapi;

import com.weazrapi.location.WeazrLocationManager;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class WeazrService extends Service {

	private static final String TAG = WeazrService.class.getSimpleName();
	
	private WeazrLocationManager weazrLocationManager;
	
	private ForcastListener forcastListener;
	
	private IBinder mBinder = new WeazrBinder();
	
	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	public class WeazrBinder extends Binder{
		public WeazrService getService(){
			return WeazrService.this;
		}
	}
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.i(TAG, " WeazrService created");
		
		weazrLocationManager = new WeazrLocationManager(this);
	}

	public ForcastListener getForcastListener() {
		return forcastListener;
	}

	public void setForcastListener(ForcastListener forcastListener) {
		this.forcastListener = forcastListener;
	}
	
}
