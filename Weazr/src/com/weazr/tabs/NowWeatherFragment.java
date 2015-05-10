package com.weazr.tabs;

import com.weazr.intent.manager.IIntentHandler;
import com.weazr.intent.manager.MainBroadcastReceiver;
import com.weazr.intent.manager.MainIntentFilter;
import com.weazr.main.R;
import com.weazr.utilities.FormatBox;
import com.weazrapi.ForcastListener;
import com.weazrapi.WeazrService;
import com.weazrapi.model.Forcast;
import com.weazrapi.model.NowForcast;
import com.weazrapi.model.Temperature;
import com.weazrapi.model.UserLocation;
import com.weazrapi.model.Weather;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class NowWeatherFragment extends Fragment implements ForcastListener {

	private static final String TAG = NowWeatherFragment.class.getSimpleName();
	

	private WeazrService weazrService;
	
	private NowForcast nowForcast;
	private UserLocation userLocation;
	
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
	
	public NowWeatherFragment(WeazrService weazrService){
		super();
		this.weazrService = weazrService;
	}
	
	public NowForcast getNowForcast() {
		return nowForcast;
	}

	public void setNowForcast(NowForcast nowForcast) {
		this.nowForcast = nowForcast;
	}
	
	
	public UserLocation getUserLocation() {
		userLocation = weazrService.getUserLocation();
		return userLocation; 
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
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		
		Log.i(TAG," NowWeatherForcast view being created");
		
		View rootView = inflater.inflate(R.layout.now_weather_fragment, container, false);
		progressBarLayout = (View)rootView.findViewById(R.id.progressBarLayout);
		minTempLbl  = (TextView)rootView.findViewById(R.id.minTempLbl);
		maxTempLbl  = (TextView)rootView.findViewById(R.id.maxTempLbl);
		humidityLbl = (TextView)rootView.findViewById(R.id.humidityLbl);
		pressureLbl = (TextView)rootView.findViewById(R.id.pressureLbl);
		speedLbl  = (TextView)rootView.findViewById(R.id.speedLbl);
		degreeLbl  = (TextView)rootView.findViewById(R.id.degreeLbl);
		
		dateLbl = (TextView)rootView.findViewById(R.id.dateLbl);
		cityLbl = (TextView)rootView.findViewById(R.id.cityLbl);
		
		nowTempLbl 	= (TextView)rootView.findViewById(R.id.nowTempLbl);
		minTempValueLbl = (TextView)rootView.findViewById(R.id.minTempValueLbl);
		maxTempValueLbl = (TextView)rootView.findViewById(R.id.maxTempValueLbl);
		humidityValueLbl = (TextView)rootView.findViewById(R.id.humidityValueLbl);
		pressureValueLbl = (TextView)rootView.findViewById(R.id.pressureValueLbl);
		speedValueLbl = (TextView)rootView.findViewById(R.id.speedValueLbl);
		degreeValueLbl 	= (TextView)rootView.findViewById(R.id.degreeValueLbl);
		weatherDescriptionLbl = (TextView)rootView.findViewById(R.id.weatherDescriptionLbl);
		flagImg = (ImageView)rootView.findViewById(R.id.flagImg);
		imageImg = (ImageView)rootView.findViewById(R.id.imageImg);
		progressBar = (ProgressBar)rootView.findViewById(R.id.loading_time_progress_bar);
		
		weazrService.setForcastListener(this);
		weazrService.getNowForcast();
		
		context.registerReceiver(new MainBroadcastReceiver(nowWeatherFragment), MainIntentFilter.getMainIntentFilter());
		return rootView;
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		makeAllWidgetsGone();
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
	
	public void makeProgressBarInvisible(){
		if(progressBarLayout.isShown())
			progressBarLayout.setVisibility(View.GONE);
		if(progressBar.isShown())
			progressBar.setVisibility(View.GONE);
	}
	
	
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
	
	public void displayForcast(Forcast forcast){
		Log.d(TAG,"handle(): handling intent");
		
		makeProgressBarInvisible();
		makeWidgetsVisible();
		
		Weather nowWeather = forcast.getWeatherList().get(0);
		
		String tempDate = nowWeather.getDate();
		String tempCity = forcast.getCity().getName();
		
		Temperature temperature = nowWeather.getTemperature();
		
		String tempNowTemp = FormatBox.kelvinToFahrenheit(temperature.getNowTemperature());
		String tempMinTemp = FormatBox.kelvinToFahrenheit(temperature.getMinTemperature());
		String tempMaxTemp = FormatBox.kelvinToFahrenheit(temperature.getMaxTemperature());
		
		String tempWeatherDescription = nowWeather.getWeatherDescription().getDescription();
		String tempHumidity = nowWeather.getHumidity();
		String tempPressure = nowWeather.getPressure();
		String tempSpeed = nowWeather.getSpeed();
		String tempDegree = nowWeather.getDegree();
		String tempWeatherIcon = nowWeather.getWeatherDescription().getIcon();
		
		String tempFlagImgIcon = getUserLocation().getCountryCode();

		
		dateLbl.setText(FormatBox.getFormattedDate(tempDate));
		
		cityLbl.setText(tempCity);
		nowTempLbl.setText(tempNowTemp);
		minTempLbl.setText(tempMinTemp);
		maxTempLbl.setText(tempMaxTemp);
		weatherDescriptionLbl.setText(tempWeatherDescription);
		humidityLbl.setText(tempHumidity);
		pressureLbl.setText(tempPressure);
		speedLbl.setText(tempSpeed);
		degreeLbl.setText(tempDegree);
		
		int weatherIcon = context.getResources().getIdentifier("x"+tempWeatherIcon, IIntentHandler.DRAWABLE_RT, IIntentHandler.R_PACKAGE);
		imageImg.setImageResource(weatherIcon);
		
		int flagIcon = context.getResources().getIdentifier(tempFlagImgIcon, IIntentHandler.DRAWABLE_RT, IIntentHandler.R_PACKAGE);
		flagImg.setImageResource(flagIcon);
	}

	@Override
	public void onForcastReceived(final Forcast forcast) {
		Log.i(TAG, "Forcast received: "+forcast.toString());

		this.getActivity().runOnUiThread(new Runnable(){
			@Override
			public void run() {
				displayForcast(forcast);
			}});
	}
}
