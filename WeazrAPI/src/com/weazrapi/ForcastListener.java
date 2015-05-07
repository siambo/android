package com.weazrapi;

import com.weazrapi.model.Forcast;

public interface ForcastListener {
	public void onForcastReceived(Forcast forcast);
}
