package com.weazrapi;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class WeazrService extends Service {

	private static final String TAG = WeazrService.class.getSimpleName();
	
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

	public ForcastListener getForcastListener() {
		return forcastListener;
	}

	public void setForcastListener(ForcastListener forcastListener) {
		this.forcastListener = forcastListener;
	}

	public class WeazrBinder extends Binder{
		public WeazrService getService(){
			return WeazrService.this;
		}
	}
}
