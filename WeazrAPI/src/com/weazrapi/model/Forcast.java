package com.weazrapi.model;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class Forcast implements IModel, Parcelable {
	
	private String id;
	private String code;
	private String message;
	private City city;
	private String count;
	private List<Weather> weatherList = new ArrayList<Weather>();
	
	public Forcast(){
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public List<Weather> getWeatherList() {
		return weatherList;
	}
	public void setWeatherList(List<Weather> weather) {
		this.weatherList = weather;
	}
	@Override
	public String toString() {
		return "Forcast [code=" + code + ", message=" + message + ", city="
				+ city + ", count=" + count + ", weatherList=" + weatherList
				+ "]";
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(code);
		dest.writeString(message);
		dest.writeParcelable(city, flags);
		dest.writeString(count);
		dest.writeList(weatherList);
		
	}
	
	public static final Parcelable.Creator<Forcast> CREATOR = new Parcelable.Creator<Forcast>() {

		@Override
		public Forcast createFromParcel(Parcel source) {
			return new Forcast(source);
		}

		@Override
		public Forcast[] newArray(int size) {
			return new Forcast[size];
		}
	};
	
	protected Forcast(Parcel in){
		id = in.readString();
		code = in.readString();
		message = in.readString();
		city = (City)in.readParcelable(City.class.getClassLoader());
		count = in.readString();
		in.readList(weatherList, Weather.class.getClassLoader());
	}

}
