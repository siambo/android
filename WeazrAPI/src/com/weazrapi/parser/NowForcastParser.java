package com.weazrapi.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.weazrapi.model.City;
import com.weazrapi.model.Coordinate;
import com.weazrapi.model.NowForcast;
import com.weazrapi.model.Temperature;
import com.weazrapi.model.Weather;
import com.weazrapi.model.WeatherDescription;

import android.util.Log;

public class NowForcastParser implements IParser{

	private static final String TAG = NowForcastParser.class.getSimpleName();
	
	@SuppressWarnings("unused")
	private String json;
	private JSONObject jsonObject;
	private NowForcast nowForcast;
	
	public NowForcastParser(NowForcast nowForcast,String json){
		this.json = json;
		this.nowForcast = nowForcast;
		
		try {
			this.jsonObject = new JSONObject(json);
		} catch (JSONException e) {
			Log.e(TAG,"JSONObject error NowForcastParser(x,y): "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	@Override
	public void parse(){
		
		City city = new City();
		Weather weather = new Weather();
		Coordinate coordinate = new Coordinate();
		Temperature temperature = new Temperature();
		WeatherDescription weatherDescription = new WeatherDescription();
		
		try {
			
			JSONObject coordObject = jsonObject.getJSONObject("coord");

			coordinate.setLatitude(coordObject.getString("lat"));
			coordinate.setLongitude(coordObject.getString("lon"));
			city.setCoordinate(coordinate);
			
			JSONObject sysObject = jsonObject.getJSONObject("sys");
			city.setCountry(sysObject.getString("country"));
			city.setSunrise(sysObject.getString("sunrise"));
			city.setSunset(sysObject.getString("sunset"));
			city.setName(jsonObject.getString("name"));

		    nowForcast.setCity(city);
		    
			JSONArray weatherDescriptionObject = jsonObject.getJSONArray("weather");
			for(int i=0;i<weatherDescriptionObject.length();i++){
				JSONObject innerObject = weatherDescriptionObject.getJSONObject(i);
				weatherDescription.setId(innerObject.getString("id"));
				weatherDescription.setMain(innerObject.getString("main"));
				weatherDescription.setDescription(innerObject.getString("description"));
				weatherDescription.setIcon(innerObject.getString("icon"));
				
				weather.setWeatherDescription(weatherDescription);
			}
			nowForcast.getWeatherList().add(weather);
			

			JSONObject mainObject = jsonObject.getJSONObject("main");
			temperature.setNowTemperature(mainObject.getString("temp"));
			temperature.setMinTemperature(mainObject.getString("temp_min"));
			temperature.setMaxTemperature(mainObject.getString("temp_max"));
			
			weather.setTemperature(temperature);
			weather.setHumidity(mainObject.getString("humidity"));
			weather.setPressure(mainObject.getString("pressure"));
			
			
			JSONObject windObject = jsonObject.getJSONObject("wind");
			weather.setSpeed(windObject.getString("speed"));
			weather.setDegree(windObject.getString("deg"));
			
			JSONObject cloudObject = jsonObject.getJSONObject("clouds");
			weather.setClouds(cloudObject.getString("all"));
			
			weather.setDate(jsonObject.getString("dt"));
			
			
			nowForcast.setId(jsonObject.getString("id"));
			nowForcast.setCode(jsonObject.getString("cod"));
			nowForcast.setBase(jsonObject.getString("base"));
			
		} catch (JSONException e) {
			Log.e(TAG, "error parse() "+e.getLocalizedMessage());
			e.printStackTrace();
		}
//		return nowForcast;
	}
}
