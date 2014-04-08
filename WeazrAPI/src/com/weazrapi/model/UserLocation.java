package com.weazrapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserLocation implements Parcelable {

	private String cityName;
	private String countryName;
	private String countryCode;
	private String latCoordinate;
	private String lonCoordinate;
	
	public UserLocation(){
		
	}
	
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	

	public String getLatCoordinate() {
		return latCoordinate;
	}

	public void setLatCoordinate(String latCoordinate) {
		this.latCoordinate = latCoordinate;
	}

	public String getLonCoordinate() {
		return lonCoordinate;
	}

	public void setLonCoordinate(String lonCoordinate) {
		this.lonCoordinate = lonCoordinate;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(cityName);
		dest.writeString(countryName);
		dest.writeString(countryCode);
		dest.writeString(latCoordinate);
		dest.writeString(lonCoordinate);
	}
	
	public static final Parcelable.Creator<UserLocation> CREATOR = new Parcelable.Creator<UserLocation>() {

		@Override
		public UserLocation createFromParcel(Parcel source) {
			return new UserLocation(source);
		}

		@Override
		public UserLocation[] newArray(int size) {
			return new UserLocation[size];
		}
	};
	
	private UserLocation(Parcel in){
		cityName = in.readString();
		countryName = in.readString();
		countryCode = in.readString();
		latCoordinate = in.readString();
		lonCoordinate = in.readString();
	}

}
