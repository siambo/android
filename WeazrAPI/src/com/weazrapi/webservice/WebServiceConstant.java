package com.weazrapi.webservice;

import android.util.Log;

public class WebServiceConstant {

	private static final String TAG = WebServiceConstant.class.getSimpleName();
	
	private static final String NOW_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static final String TEN_DAY_WEATHER_FORCAST_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
    private static final String NOW_WEATHER_URL_USING_COORDS = "http://api.openweathermap.org/data/2.5/weather?";
    
	
	public static String getNowWeatherUrl(String cityName, String countryCode){
		String temp = NOW_WEATHER_URL + cityName+","+countryCode;
		Log.d(TAG,"getNowWeatherUrl(): "+temp);
		return temp;
	}
    
    public static String getNowWeatherUrlUsingCoords(String lat, String lon){
    	String temp = NOW_WEATHER_URL_USING_COORDS+"lat="+lat+"&lon="+lon;
    	Log.d(TAG, "getNowWeatherUrlUsingCoords(): "+temp);
    	return temp;
    }
	
	public static String getTenDayWeatherUrl(String cityName, String countryCode){
		String temp = TEN_DAY_WEATHER_FORCAST_URL + cityName + ","+countryCode+"&cnt=10&mode=json";
		Log.d(TAG, "getTenDayWeatherUrl(): "+temp);
		return temp;
	}
}
