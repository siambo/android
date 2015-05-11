package com.weazr.tabs;

import java.util.List;

import com.weazr.main.R;
import com.weazrapi.TenDayForcastListener;
import com.weazrapi.WeazrService;
import com.weazrapi.model.Forcast;
import com.weazrapi.model.Weather;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

public class TenDayForcastFragment extends Fragment implements TenDayForcastListener{

	private static final String TAG = TenDayForcastFragment.class.getSimpleName();
	
	private WeazrService weazrService;
	
	private Context context;
	private TenDayForcastFragment tenDayForcastFragment = this;
	
	private WeazrListAdapter adapter;

	
	private ListView listView;
	private View progressBarLayout;
	private ProgressBar progressBar;
	
	public TenDayForcastFragment(WeazrService weazrService){
		this.weazrService = weazrService;
	}
    
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
		
		
		return rootView;
	}
	
	@Override
	public void onStart() {
		super.onStart();
		
	}
	
	@Override
	public void onResume() {
		super.onResume();
		weazrService.setTenDayForcastListener(this);
		weazrService.get10DaysForcast();
	}

	public void makeProgressBarLayoutGone(){
		if(progressBarLayout.isShown())
			progressBarLayout.setVisibility(View.GONE);
	}
	
	public void makeProgressBarLayoutVisible(){
		if(!progressBarLayout.isShown())
			progressBarLayout.setVisibility(View.VISIBLE);
	}

	@Override
	public void onForcastReceived(final Forcast forcast) {
		Log.i(TAG, "Forcast received: "+forcast.toString());
		
		
		this.getActivity().runOnUiThread(new Runnable(){

			@Override
			public void run() {
				makeProgressBarLayoutGone();
				List<Weather> weazrList = forcast.getWeatherList();
				adapter = new WeazrListAdapter(tenDayForcastFragment, weazrList);
				getListView().setAdapter(adapter);
				
			}});
		
	}

}
