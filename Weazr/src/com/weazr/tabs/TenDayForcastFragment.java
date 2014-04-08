package com.weazr.tabs;

import java.util.ArrayList;
import java.util.HashMap;

import com.weazr.intent.manager.MainBroadcastReceiver;
import com.weazr.intent.manager.MainIntentFilter;
import com.weazr.main.R;
import com.weazr.utilities.FormatBox;
import com.weazrapi.location.WeazrLocationService;
import com.weazrapi.model.NowForcast;
import com.weazrapi.model.TenDayForcast;
import com.weazrapi.model.UserLocation;
import com.weazrapi.webservice.NowWebServiceRunnable;
import com.weazrapi.webservice.TenDayWebServiceRunnable;
import com.weazrapi.webservice.WebServiceConstant;
import com.weazrapi.webservice.WebServiceRunnable;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class TenDayForcastFragment extends Fragment {

	private static final String TAG = "TenDayForcastFragment";
	
	private Context context;
	private TenDayForcastFragment tenDayForcastFragment = this;
	
	private UserLocation userLocation;
	private TenDayForcast tenDayForcast;
	private WebServiceRunnable webServiceRunnable;
	
	private Thread thread;
	
	private ListView listView;
	private View progressBarLayout;
	private ProgressBar progressBar;
    
	public Context getContext() {
		return context;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public ListView getListView() {
		return listView;
	}

	public void setListView(ListView listView) {
		this.listView = listView;
	}

	public View getProgressBarLayout() {
		return progressBarLayout;
	}

	public void setProgressBarLayout(View progressBarLayout) {
		this.progressBarLayout = progressBarLayout;
	}

	public ProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.context = activity.getApplicationContext();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.ten_days_forcast_fragment, container, false);
		this.listView = (ListView)rootView.findViewById(R.id.list);
		this.progressBarLayout = (View)rootView.findViewById(R.id.progressBarLayout);
		
		context.registerReceiver(new MainBroadcastReceiver(tenDayForcastFragment), MainIntentFilter.getMainIntentFilter());
		return rootView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
		try{
			WeazrLocationService locationService = new WeazrLocationService(context);
			userLocation = locationService.readLocation();
			
			SharedPreferences sharedPreferences = context.getSharedPreferences("weazrPreferences", Context.MODE_PRIVATE);
			String cityName = sharedPreferences.getString("cityName", "");
			String countryCode = sharedPreferences.getString("countryCode", "");
		
			Log.e(TAG,"Preferences reading: "+cityName+", "+countryCode);
			
			if(cityName != null && countryCode != null){
				tenDayForcast = new TenDayForcast();
				
				webServiceRunnable = new TenDayWebServiceRunnable(context, tenDayForcast, 
									 WebServiceConstant.getTenDayWeatherUrl(
											  FormatBox.removeWhiteSpace(cityName),countryCode));

				thread = new Thread(webServiceRunnable);
				thread.start();
			}
		}catch(Exception e){
			Toast.makeText(this.getActivity(), "Location inaccessible", Toast.LENGTH_LONG).show();
		}
	}
	
	public void makeProgressBarLayoutGone(){
		if(progressBarLayout.isShown())
			progressBarLayout.setVisibility(View.GONE);
	}
	
	public void makeProgressBarLayoutVisible(){
		if(!progressBarLayout.isShown())
			progressBarLayout.setVisibility(View.VISIBLE);
	}

}
