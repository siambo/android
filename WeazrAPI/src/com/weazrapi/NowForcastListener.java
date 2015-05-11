package com.weazrapi;

import com.weazrapi.model.Forcast;

public interface NowForcastListener {
	public void onForcastReceived(Forcast forcast);
}
