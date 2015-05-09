package com.weazr.intent.manager;

import com.example.weazrapi.R;
import com.weazrapi.ForcastListener;
import com.weazrapi.WeazrService;
import com.weazrapi.WeazrService.WeazrBinder;
import com.weazrapi.location.WeazrLocationManager;
import com.weazrapi.model.Forcast;
import com.weazrapi.model.UserLocation;

import android.os.Bundle;
import android.os.IBinder;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;

public class Main extends Activity implements ForcastListener{
	
	private static final String TAG = Main.class.getSimpleName();
	
	private Main mainActivity;
	private WeazrService weazrService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG,"onCreate called");
		mainActivity = this;

	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
		if(weazrService == null){
			Intent intent = new Intent(this, WeazrService.class);
			bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private ServiceConnection serviceConnection = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			weazrService = ((WeazrService.WeazrBinder) service).getService();
			weazrService.setForcastListener(mainActivity);
			weazrService.get10DaysForcast();
			Log.i(TAG, "WeazrService running and bound to");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			weazrService = null;
			Log.i(TAG, "WeazrService disconnected and unbound to");
		}
		
	};

	@Override
	public void onForcastReceived(Forcast forcast) {
		Log.i(TAG, "Forcast Received: "+forcast.toString());
	}
	
}
