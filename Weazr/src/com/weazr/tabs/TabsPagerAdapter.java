package com.weazr.tabs;

import com.weazr.main.AppContext;
import com.weazrapi.WeazrService;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	private static final String TAG = TabsPagerAdapter.class.getSimpleName();
	
	private WeazrService weazrService;
	
	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		weazrService = AppContext.getWeazrService();
	}
	
	@Override
	public Fragment getItem(int tabIndex) {
		Log.i(TAG,"Fragments being created");
		switch (tabIndex) {
        case 0:
            return new NowWeatherFragment(weazrService);
        case 1:
            return new TenDayForcastFragment(weazrService);
        case 2:
            return new TBDFragment();
        default:
        	return null;
        }
	}

	@Override
	public int getCount() {
		return 3;
	}

}
