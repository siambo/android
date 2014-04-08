package com.weazrapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Coordinate implements Parcelable {
	
	private String latitude;
	private String longitude;
	
	public Coordinate(){}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Coordinate [latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(latitude);
		dest.writeString(longitude);
		
	}
	
	public static final Parcelable.Creator<Coordinate> CREATOR = new Parcelable.Creator<Coordinate>(){

		@Override
		public Coordinate createFromParcel(Parcel source) {
			return new Coordinate(source);
		}

		@Override
		public Coordinate[] newArray(int size) {
			return new Coordinate[size];
		}

	};
	
	private Coordinate(Parcel in){
		latitude = in.readString();
		longitude = in.readString();
	}
	
}
