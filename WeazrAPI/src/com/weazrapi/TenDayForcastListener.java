package com.weazrapi;

import com.weazrapi.model.Forcast;

public interface TenDayForcastListener  {

	public void onForcastReceived(Forcast forcast);

}
