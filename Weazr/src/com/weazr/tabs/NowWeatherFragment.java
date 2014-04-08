package com.weazr.tabs;
import java.util.Locale;

import com.weazr.intent.manager.MainBroadcastReceiver;
import com.weazr.intent.manager.MainIntentFilter;
import com.weazr.main.R;
import com.weazr.utilities.FormatBox;
import com.weazrapi.location.WeazrLocationService;
import com.weazrapi.model.NowForcast;
import com.weazrapi.model.UserLocation;
import com.weazrapi.webservice.NowWebServiceRunnable;
import com.weazrapi.webservice.WebServiceConstant;
import com.weazrapi.webservice.WebServiceRunnable;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NowWeatherFragment extends Fragment {

	private static final String TAG = "TopRatedFragment";
	

	private NowForcast nowForcast;
	private NowWebServiceRunnable webServiceRunnable;
	private UserLocation userLocation;
	private Thread thread;
	
	private NowWeatherFragment nowWeatherFragment = this;
	private Context context;
	
	private View progressBarLayout;
	private TextView minTempLbl;
	private TextView maxTempLbl;
	private TextView humidityLbl;
	private TextView pressureLbl;
	private TextView speedLbl;
	private TextView degreeLbl;
	
	private TextView dateLbl;
	private TextView cityLbl;
	private TextView nowTempLbl;
	private TextView minTempValueLbl;
	private TextView maxTempValueLbl;
	private TextView humidityValueLbl;
	private TextView pressureValueLbl;
	private TextView speedValueLbl;
	private TextView degreeValueLbl;
	private TextView weatherDescriptionLbl;
	private ImageView flagImg;
	private ImageView imageImg;

	private ProgressBar progressBar;
	
	public NowForcast getNowForcast() {
		return nowForcast;
	}

	public void setNowForcast(NowForcast nowForcast) {
		this.nowForcast = nowForcast;
	}

	public TextView getDateLbl() {
		return dateLbl;
	}

	public void setDateLbl(TextView dateLbl) {
		this.dateLbl = dateLbl;
	}

	public TextView getCityLbl() {
		return cityLbl;
	}

	public void setCityLbl(TextView cityLbl) {
		this.cityLbl = cityLbl;
	}

	public TextView getNowTempLbl() {
		return nowTempLbl;
	}

	public void setNowTempLbl(TextView nowTempLbl) {
		this.nowTempLbl = nowTempLbl;
	}

	public TextView getMinTempLbl() {
		return minTempValueLbl;
	}

	public void setMinTempLbl(TextView minTempLbl) {
		this.minTempValueLbl = minTempLbl;
	}

	public TextView getMaxTempLbl() {
		return maxTempValueLbl;
	}

	public void setMaxTempLbl(TextView maxTempLbl) {
		this.maxTempValueLbl = maxTempLbl;
	}

	public TextView getHumidityLbl() {
		return humidityValueLbl;
	}

	public void setHumidityLbl(TextView humidityLbl) {
		this.humidityValueLbl = humidityLbl;
	}

	public TextView getPressureLbl() {
		return pressureValueLbl;
	}

	public void setPressureLbl(TextView pressureLbl) {
		this.pressureValueLbl = pressureLbl;
	}

	public TextView getSpeedLbl() {
		return speedValueLbl;
	}

	public void setSpeedLbl(TextView speedLbl) {
		this.speedValueLbl = speedLbl;
	}

	public TextView getDegreeLbl() {
		return degreeValueLbl;
	}

	public void setDegreeLbl(TextView degreeLbl) {
		this.degreeValueLbl = degreeLbl;
	}
	
	public TextView getWeatherDescriptionLbl() {
		return weatherDescriptionLbl;
	}

	public void setWeatherDescriptionLbl(TextView weatherDescriptionLbl) {
		this.weatherDescriptionLbl = weatherDescriptionLbl;
	}

	public ImageView getFlagImg() {
		return flagImg;
	}

	public void setFlagImg(ImageView flagImg) {
		this.flagImg = flagImg;
	}

	public ImageView getImageImg() {
		return imageImg;
	}

	public void setImageImg(ImageView imageImg) {
		this.imageImg = imageImg;
	}


	public UserLocation getUserLocation() {
		return userLocation;
	}


	public void setUserLocation(UserLocation userLocation) {
		this.userLocation = userLocation;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = activity.getApplicationContext();
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		
		Log.d(TAG,"started creating view");
		
		View rootView = inflater.inflate(R.layout.now_weather_fragment, container, false);
		progressBarLayout = (View)rootView.findViewById(R.id.progressBarLayout);
		minTempLbl  = (TextView)rootView.findViewById(R.id.minTempLbl);
		maxTempLbl  = (TextView)rootView.findViewById(R.id.maxTempLbl);
		humidityLbl = (TextView)rootView.findViewById(R.id.humidityLbl);
		pressureLbl = (TextView)rootView.findViewById(R.id.pressureLbl);
		speedLbl    = (TextView)rootView.findViewById(R.id.speedLbl);
		degreeLbl   = (TextView)rootView.findViewById(R.id.degreeLbl);
		
		dateLbl 	= (TextView)rootView.findViewById(R.id.dateLbl);
		cityLbl 	= (TextView)rootView.findViewById(R.id.cityLbl);
		
		nowTempLbl 	= (TextView)rootView.findViewById(R.id.nowTempLbl);
		minTempValueLbl 	= (TextView)rootView.findViewById(R.id.minTempValueLbl);
		maxTempValueLbl 	= (TextView)rootView.findViewById(R.id.maxTempValueLbl);
		humidityValueLbl = (TextView)rootView.findViewById(R.id.humidityValueLbl);
		pressureValueLbl = (TextView)rootView.findViewById(R.id.pressureValueLbl);
		speedValueLbl 	= (TextView)rootView.findViewById(R.id.speedValueLbl);
		degreeValueLbl 	= (TextView)rootView.findViewById(R.id.degreeValueLbl);
		weatherDescriptionLbl = (TextView)rootView.findViewById(R.id.weatherDescriptionLbl);
		flagImg 	= (ImageView)rootView.findViewById(R.id.flagImg);
		imageImg 	= (ImageView)rootView.findViewById(R.id.imageImg);
		progressBar = (ProgressBar)rootView.findViewById(R.id.loading_time_progress_bar);
		
		context.registerReceiver(new MainBroadcastReceiver(nowWeatherFragment), MainIntentFilter.getMainIntentFilter());
		return rootView;
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		makeAllWidgetsGone();
		
		try{
			WeazrLocationService locationService = new WeazrLocationService(context);
			userLocation = locationService.readLocation();
			
			SharedPreferences sharedPreferences = context.getSharedPreferences("weazrPreferences", Context.MODE_PRIVATE);
			String cityName = sharedPreferences.getString("cityName", "");
			String countryCode = sharedPreferences.getString("countryCode", "");
		
			Log.e(TAG,"Preferences reading: "+cityName+", "+countryCode);
/*			if(userLocation != null){
				nowForcast = new NowForcast();
				webServiceRunnable = new WebServiceRunnable(context, nowForcast, 
						WebServiceConstant.getNowWeatherUrl(
								FormatBox.removeWhiteSpace(
										userLocation.getCityName()),userLocation.getCountryCode()));*/
			
			if(cityName != null && countryCode != null){
				nowForcast = new NowForcast();
				
				webServiceRunnable = new NowWebServiceRunnable(context, nowForcast, 
									 WebServiceConstant.getNowWeatherUrl(
											  FormatBox.removeWhiteSpace(cityName),countryCode));

				thread = new Thread(webServiceRunnable);
				thread.start();
			}
		}catch(Exception e){
			Toast.makeText(this.getActivity(), "Location inaccessible", Toast.LENGTH_LONG).show();
		}
	}

	public void makeAllWidgetsGone(){
		if(dateLbl.isShown())
			dateLbl.setVisibility(View.GONE);
		if(cityLbl.isShown())
			cityLbl.setVisibility(View.GONE);
		if(nowTempLbl.isShown())
			nowTempLbl.setVisibility(View.GONE);
		if(minTempValueLbl.isShown())
			minTempValueLbl.setVisibility(View.GONE);
		if(maxTempValueLbl.isShown())
			maxTempValueLbl.setVisibility(View.GONE);
		if(humidityValueLbl.isShown())
			humidityValueLbl.setVisibility(View.GONE);
		if(pressureValueLbl.isShown())
			pressureValueLbl.setVisibility(View.GONE);
		if(speedValueLbl.isShown())
			speedValueLbl.setVisibility(View.GONE);
		if(degreeValueLbl.isShown())
			degreeValueLbl.setVisibility(View.GONE);
		if(weatherDescriptionLbl.isShown())
			weatherDescriptionLbl.setVisibility(View.GONE);
		if(flagImg.isShown())
			flagImg.setVisibility(View.GONE);
		if(imageImg.isShown())
			imageImg.setVisibility(View.GONE);
		if(minTempLbl.isShown())
			minTempLbl.setVisibility(View.GONE);
		if(maxTempLbl.isShown())
			maxTempLbl.setVisibility(View.GONE);
		if(humidityLbl.isShown())
			humidityLbl.setVisibility(View.GONE);
		if(pressureLbl.isShown())
			pressureLbl.setVisibility(View.GONE);
		if(speedLbl.isShown())
			speedLbl.setVisibility(View.GONE);
		if(degreeLbl.isShown())
			degreeLbl.setVisibility(View.GONE);
	}
	
	public void makeProgressBarGone(){
		if(progressBarLayout.isShown())
			progressBarLayout.setVisibility(View.GONE);
		if(progressBar.isShown())
			progressBar.setVisibility(View.GONE);
	}
	
/*	public void makeProgressBarVisible(){
		if(!dateLbl.isShown())
			dateLbl.setVisibility(View.VISIBLE);
	}*/
	
	public void makeWidgetsVisible(){
		if(!dateLbl.isShown())
			dateLbl.setVisibility(View.VISIBLE);
		if(!cityLbl.isShown())
			cityLbl.setVisibility(View.VISIBLE);
		if(!nowTempLbl.isShown())
			nowTempLbl.setVisibility(View.VISIBLE);
		if(!minTempValueLbl.isShown())
			minTempValueLbl.setVisibility(View.VISIBLE);
		if(!maxTempValueLbl.isShown())
			maxTempValueLbl.setVisibility(View.VISIBLE);
		if(!humidityValueLbl.isShown())
			humidityValueLbl.setVisibility(View.VISIBLE);
		if(!pressureValueLbl.isShown())
			pressureValueLbl.setVisibility(View.VISIBLE);
		if(!speedValueLbl.isShown())
			speedValueLbl.setVisibility(View.VISIBLE);
		if(!degreeValueLbl.isShown())
			degreeValueLbl.setVisibility(View.VISIBLE);
		if(!weatherDescriptionLbl.isShown())
			weatherDescriptionLbl.setVisibility(View.VISIBLE);
		if(!flagImg.isShown())
			flagImg.setVisibility(View.VISIBLE);
		if(!imageImg.isShown())
			imageImg.setVisibility(View.VISIBLE);
		if(!minTempLbl.isShown())
			minTempLbl.setVisibility(View.VISIBLE);
		if(!maxTempLbl.isShown())
			maxTempLbl.setVisibility(View.VISIBLE);
		if(!humidityLbl.isShown())
			humidityLbl.setVisibility(View.VISIBLE);
		if(!pressureLbl.isShown())
			pressureLbl.setVisibility(View.VISIBLE);
		if(!speedLbl.isShown())
			speedLbl.setVisibility(View.VISIBLE);
		if(!degreeLbl.isShown())
			degreeLbl.setVisibility(View.VISIBLE);
	}
	
}
