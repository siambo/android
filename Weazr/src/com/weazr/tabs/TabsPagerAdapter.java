package com.weazr.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		
	}

	@Override
	public Fragment getItem(int tabIndex) {
		switch (tabIndex) {
        case 0:
            return new NowWeatherFragment();
        case 1:
            return new TenDayForcastFragment();
        	//return new MobileListView();
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
