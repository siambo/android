package com.weazr.main;

import com.weazr.main.R;
import com.weazr.settings.SettingsActivity;
import com.weazr.tabs.TabsPagerAdapter;
import com.weazr.utilities.FormatBox;
import com.weazrapi.location.WeazrLocationService;
import com.weazrapi.model.NowForcast;
import com.weazrapi.model.UserLocation;
import com.weazrapi.webservice.WebServiceConstant;
import com.weazrapi.webservice.WebServiceRunnable;

import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Main extends FragmentActivity implements ActionBar.TabListener{

	private static final String TAG = Main.class.getSimpleName();
	
	
	private ViewPager viewPager;
	private TabsPagerAdapter tabAdapter;
	private ActionBar actionBar;
	NowForcast nowForcast = new NowForcast();
	
	private String[] tabs = {"NOW", "10 Day", "Info"};
	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "Main onCreate");
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		tabAdapter  = new TabsPagerAdapter(getSupportFragmentManager());
		
		viewPager.setAdapter(tabAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		SharedPreferences sharedPreferences = getSharedPreferences("weazrPreferences",Context.MODE_PRIVATE);
		
		try{
			WeazrLocationService locationService = new WeazrLocationService(getApplicationContext());
			UserLocation userLocation = locationService.readLocation();
		    
			Editor editor = sharedPreferences.edit();
			editor.putString("cityName", userLocation.getCityName());
			editor.putString("countryName", userLocation.getCountryName());
			editor.putString("countryCode", userLocation.getCountryCode());
			editor.commit();
			
		}catch(Exception e){
			Toast.makeText(this, "Location inaccessible in shared preferneces", Toast.LENGTH_LONG).show();
		}
				
    	viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});	
		
		for(String tabname: tabs){
			actionBar.addTab(actionBar.newTab().setText(tabname).setTabListener(this));
		}
		
		
		Log.d(TAG, "Main onCreate ends");
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	/**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.action_settings:
            openSettings();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
 

    private void openSettings() {
        Intent i = new Intent(Main.this, SettingsActivity.class);
        startActivity(i);
    }
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	
	}

}
