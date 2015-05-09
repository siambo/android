package com.weazrapi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.weazr.utilities.FormatBox;
import com.weazrapi.location.WeazrLocationManager;
import com.weazrapi.model.UserLocation;
import com.weazrapi.webservice.NowWebServiceRunnable;
import com.weazrapi.webservice.TenDayWebServiceRunnable;
import com.weazrapi.webservice.WebServiceConstant;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class WeazrService extends Service {

	private static final String TAG = WeazrService.class.getSimpleName();
	
	private static final ExecutorService executor = Executors.newFixedThreadPool(1);
	
	private WeazrLocationManager weazrLocationManager;
	private UserLocation userLocation;
	private String weatherUrlLocation;
	
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

	
	@Override
	public void onDestroy() {
		super.onDestroy();
		executor.shutdown();
	}

	public ForcastListener getForcastListener() {
		return forcastListener;
	}

	public void setForcastListener(ForcastListener forcastListener) {
		this.forcastListener = forcastListener;
	}
	
	public void getNowForcast(){
		userLocation = weazrLocationManager.getLocation();
		String cityName = FormatBox.removeWhiteSpace(userLocation.getCityName());
		weatherUrlLocation = WebServiceConstant.getNowWeatherUrl(cityName, userLocation.getCountryCode());
		executor.execute(new NowWebServiceRunnable(weatherUrlLocation,forcastListener));
	}
	
	public void get10DaysForcast(){
		userLocation = weazrLocationManager.getLocation();
		weatherUrlLocation = WebServiceConstant.getTenDayWeatherUrl(userLocation.getCityNameWithNoSpace(), userLocation.getCountryCode());
		executor.execute(new TenDayWebServiceRunnable(weatherUrlLocation, forcastListener));
	}
}
