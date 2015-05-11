package com.weazr.main;

import com.weazrapi.WeazrService;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class WeazrLauncherActivity extends Activity {

	private static final String TAG = WeazrLauncherActivity.class.getSimpleName();
	
	private WeazrService weazrService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weazr_launcher);
		bindToWeazrService();
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		if(weazrService==null){
			bindToWeazrService();
		}
	}

   public void bindToWeazrService(){
	   Intent intent = new Intent(this, WeazrService.class);
		bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
   }

	private void openMainActivity(){
		Log.i(TAG, "opening main activity");
		Intent intent = new Intent(this,MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	public void openErrorActivity(){
		Log.i(TAG, "opening error activity");
		Intent intent = new Intent(this, WeazrErrorActivity.class);
		startActivity(intent);
	}
	
	private ServiceConnection serviceConnection = new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			weazrService = ((WeazrService.WeazrBinder) service).getService();
			AppContext.setWeazrService(weazrService);
			openMainActivity();
			Log.i(TAG, "WeazrService running and bound to");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			weazrService = null;
			openErrorActivity();
			Log.i(TAG, "WeazrService disconnected and unbound to");
		}
		
	};
}
