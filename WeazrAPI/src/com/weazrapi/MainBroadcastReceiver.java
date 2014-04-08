package com.weazrapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MainBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "MainBroadcastReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
			Log.d(TAG, "onReceive");
	}

}
