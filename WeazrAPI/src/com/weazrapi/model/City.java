package com.weazrapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class City implements Parcelable {
	
	private String cityId;
	private String name;
	private String country;
	private Coordinate coordinate;
	private String population;
	private String sunrise;
	private String sunset;
	
	public City(){}
	
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Coordinate getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}
	
	

	public String getSunrise() {
		return sunrise;
	}

	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", name=" + name + ", country="
				+ country + ", coordinate=" + coordinate + ", population="
				+ population + ", sunrise=" + sunrise + ", sunset=" + sunset
				+ "]";
	}
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(cityId);
		dest.writeString(name);
		dest.writeString(country);
		dest.writeParcelable(coordinate, flags);
		dest.writeString(population);
		dest.writeString(sunrise);
		dest.writeString(sunset);
		
	}

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {

		@Override
		public City createFromParcel(Parcel source) {
			return new City(source);
		}

		@Override
		public City[] newArray(int size) {
			return new City[size];
		}
	};
	
	private City(Parcel in){
		cityId = in.readString();
		name = in.readString();
		country = in.readString();
		coordinate = (Coordinate)in.readParcelable(Coordinate.class.getClassLoader());
		population = in.readString();
		sunrise = in.readString();
		sunset = in.readString();
	}
}
