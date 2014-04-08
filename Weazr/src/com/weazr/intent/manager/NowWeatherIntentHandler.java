package com.weazr.intent.manager;

import com.weazr.main.R;
import com.weazr.tabs.NowWeatherFragment;
import com.weazr.tabs.TenDayForcastFragment;
import com.weazr.utilities.FormatBox;
import com.weazrapi.model.NowForcast;
import com.weazrapi.model.Weather;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;


public class NowWeatherIntentHandler implements IIntentHandler {

	private static final String TAG = "NowWeatherIntentHandler";
	
	private NowWeatherFragment fragment;
	
	public NowWeatherIntentHandler(Fragment fragment){
		try{
			if(fragment instanceof NowWeatherFragment)
				this.fragment = (NowWeatherFragment)fragment;
		}catch(Exception e){
			Log.e(TAG, "error NowWeatherIntentHandler() "+e.getLocalizedMessage());
		}
	}
	
	@Override
	public void handle(Intent intent) {

		Log.d(TAG,"handle(): handling intent");
		Context context = fragment.getActivity().getApplicationContext();
		NowForcast nowForcast =(NowForcast)intent.getExtras().getParcelable("com.weazrapi.model.NowForcast");
		
		fragment.makeProgressBarGone();
		fragment.makeWidgetsVisible();
		
		Weather nowWeather = nowForcast.getWeatherList().get(0);
		
		String tempDate = nowWeather.getDate();
		String tempCity = nowForcast.getCity().getName();
		String tempNowTemp = FormatBox.kelvinToFahrenheit(nowWeather.getTemperature().getNowTemperature());
		String tempMinTemp = FormatBox.kelvinToFahrenheit(nowWeather.getTemperature().getMinTemperature());
		String tempMaxTemp = FormatBox.kelvinToFahrenheit(nowWeather.getTemperature().getMaxTemperature());
		String tempWeatherDescription = nowWeather.getWeatherDescription().getDescription();
		String tempHumidity = nowWeather.getHumidity();
		String tempPressure = nowWeather.getPressure();
		String tempSpeed = nowWeather.getSpeed();
		String tempDegree = nowWeather.getDegree();
		String tempWeatherIcon = nowWeather.getWeatherDescription().getIcon();
		String tempFlagImgIcon = fragment.getUserLocation().getCountryCode();
		
		fragment.getDateLbl().setText(FormatBox.getFormattedDate(tempDate));
		
		fragment.getCityLbl().setText(tempCity);
		fragment.getNowTempLbl().setText(tempNowTemp+"�");
		fragment.getMinTempLbl().setText(tempMinTemp+"�");
		fragment.getMaxTempLbl().setText(tempMaxTemp+"�");
		fragment.getWeatherDescriptionLbl().setText(tempWeatherDescription);
		fragment.getHumidityLbl().setText(tempHumidity);
		fragment.getPressureLbl().setText(tempPressure);
		fragment.getSpeedLbl().setText(tempSpeed);
		fragment.getDegreeLbl().setText(tempDegree);
		
		int weatherIcon = context.getResources().getIdentifier("x"+tempWeatherIcon, IIntentHandler.DRAWABLE_RT, IIntentHandler.R_PACKAGE);
		fragment.getImageImg().setImageResource(weatherIcon);
		
		int flagImg = context.getResources().getIdentifier(tempFlagImgIcon, IIntentHandler.DRAWABLE_RT, IIntentHandler.R_PACKAGE);
		fragment.getFlagImg().setImageResource(flagImg);
	}

}