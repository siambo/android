package com.weazrapi.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


import com.weazrapi.model.City;
import com.weazrapi.model.Coordinate;
import com.weazrapi.model.Temperature;
import com.weazrapi.model.TenDayForcast;
import com.weazrapi.model.Weather;
import com.weazrapi.model.WeatherDescription;

public class TenDayForcastParser implements IParser {

	private static final String TAG = "TenDayForcastParser";
	
	private String json;
	private JSONObject jsonObject;
	private TenDayForcast tenDayForcast;
	
	public TenDayForcastParser(TenDayForcast tenDayForcast, String json){
		this.json=json;
		this.tenDayForcast = tenDayForcast;
		
		try{
			this.jsonObject = new JSONObject(json);
		}catch(JSONException e){
			Log.e(TAG, "JSONObject error TenDayForcastParser(x,y): "+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void parse() {

		City city = new City();
		Coordinate coordinate = new Coordinate();
		
		try{
			
			tenDayForcast.setCode(jsonObject.getString("cod"));
			tenDayForcast.setMessage(jsonObject.getString("message"));
			
			JSONObject cityObject = jsonObject.getJSONObject("city");
			city.setCityId(cityObject.getString("id"));
			city.setName(cityObject.getString("name"));
			
			JSONObject coordObject = cityObject.getJSONObject("coord");
			coordinate.setLatitude(coordObject.getString("lat"));
			coordinate.setLongitude(coordObject.getString("lon"));
			city.setCoordinate(coordinate);
			city.setPopulation(cityObject.getString("population"));
			
			tenDayForcast.setCity(city);
			tenDayForcast.setCount(jsonObject.getString("cnt"));
			
			JSONArray weazrArray = jsonObject.getJSONArray("list");
			int forcastCount = weazrArray.length();
			
			for(int i=0; i<forcastCount; i++){
				Temperature temperature = new Temperature();
				WeatherDescription weatherDescription = new WeatherDescription();
				Weather weather = new Weather();
				
				JSONObject innerObject = weazrArray.getJSONObject(i);
				
				weather.setDate(innerObject.getString("dt"));
				weather.setPressure(innerObject.getString("pressure"));
				weather.setHumidity(innerObject.getString("humidity"));
				weather.setSpeed(innerObject.getString("speed"));
				weather.setDegree(innerObject.getString("deg"));
				
				try{
					weather.setSnow(innerObject.getString("snow"));
				}catch(Exception e){
					weather.setSnow("-");
					Log.e(TAG,"error snow parse() "+e.getLocalizedMessage());
				}
				
				try{
					weather.setClouds(innerObject.getString("clouds"));
				}catch(Exception e){
					weather.setClouds("-");
					Log.e(TAG,"error clouds parse() "+e.getLocalizedMessage());
				}
				
				try{
					weather.setRain(innerObject.getString("rain"));
				}catch(Exception e){
					weather.setRain("-");
					Log.e(TAG,"error rain parse() "+e.getLocalizedMessage());
				}
				
				try{
					weather.setThunderstorm(innerObject.getString("thunderstorm"));
				}catch(Exception e){
					weather.setThunderstorm("-");
					Log.e(TAG,"error thunderstorm parse() "+e.getLocalizedMessage());
				}
				
				try{
					weather.setDrizzle(innerObject.getString("drizzle"));
				}catch(Exception e){
					weather.setDrizzle("-");
					Log.e(TAG,"error drizzle parse() "+e.getLocalizedMessage());
				}
				
				try{
					weather.setAtmosphere(innerObject.getString("atmosphere"));
				}catch(Exception e){
					weather.setAtmosphere("-");
					Log.e(TAG,"error atmosphere parse() "+e.getLocalizedMessage());
				}
				
				try{
					weather.setExtreme(innerObject.getString("extreme"));
				}catch(Exception e){
					weather.setExtreme("-");
					Log.e(TAG,"error extreme parse() "+e.getLocalizedMessage());
				}
				
				JSONObject tempObject = innerObject.getJSONObject("temp");
				temperature.setDayTemperature(tempObject.getString("day"));
				temperature.setMinTemperature(tempObject.getString("min"));
				temperature.setMaxTemperature(tempObject.getString("max"));
				temperature.setNightTemperature(tempObject.getString("night"));
				temperature.setEveningTemperature(tempObject.getString("eve"));
				temperature.setMorningTemperature(tempObject.getString("morn"));
				
				weather.setTemperature(temperature);
				
				JSONArray innerInnerObject = innerObject.getJSONArray("weather");
				JSONObject weatherDescriptionObject = innerInnerObject.getJSONObject(0);
				weatherDescription.setId(weatherDescriptionObject.getString("id"));
				weatherDescription.setMain(weatherDescriptionObject.getString("main"));
				weatherDescription.setDescription(weatherDescriptionObject.getString("description"));
				weatherDescription.setIcon(weatherDescriptionObject.getString("icon"));
				
				weather.setWeatherDescription(weatherDescription);
				
				tenDayForcast.getWeatherList().add(weather);
				
			}
			
		}catch(Exception e){
			Log.e(TAG,"outer try error parse():"+e.getLocalizedMessage());
		}
	}

}
