package com.weazrapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Temperature implements Parcelable{
	
	private String nowTemperature;
	private String dayTemperature;
	private String minTemperature;
	private String maxTemperature;
	private String nightTemperature;
	private String eveningTemperature;
	private String morningTemperature;
	
	public Temperature(){}
	
	public String getNowTemperature() {
		return nowTemperature;
	}
	public void setNowTemperature(String nowTemperature) {
		this.nowTemperature = nowTemperature;
	}
	public String getDayTemperature() {
		return dayTemperature;
	}
	public void setDayTemperature(String dayTemperature) {
		this.dayTemperature = dayTemperature;
	}
	public String getMinTemperature() {
		return minTemperature;
	}
	public void setMinTemperature(String minTemperature) {
		this.minTemperature = minTemperature;
	}
	public String getMaxTemperature() {
		return maxTemperature;
	}
	public void setMaxTemperature(String maxTemperature) {
		this.maxTemperature = maxTemperature;
	}
	public String getNightTemperature() {
		return nightTemperature;
	}
	public void setNightTemperature(String nightTemperature) {
		this.nightTemperature = nightTemperature;
	}
	public String getEveningTemperature() {
		return eveningTemperature;
	}
	public void setEveningTemperature(String eveningTemperature) {
		this.eveningTemperature = eveningTemperature;
	}
	public String getMorningTemperature() {
		return morningTemperature;
	}
	public void setMorningTemperature(String morningTemperature) {
		this.morningTemperature = morningTemperature;
	}
	@Override
	public String toString() {
		return "Temperature [nowTemperature=" + nowTemperature
				+ ", dayTemperature=" + dayTemperature + ", minTemperature="
				+ minTemperature + ", maxTemperature=" + maxTemperature
				+ ", nightTemperature=" + nightTemperature
				+ ", eveningTemperature=" + eveningTemperature
				+ ", morningTemperature=" + morningTemperature + "]";
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nowTemperature);
		dest.writeString(dayTemperature);
		dest.writeString(minTemperature);
		dest.writeString(maxTemperature);
		dest.writeString(nightTemperature);
		dest.writeString(eveningTemperature);
		dest.writeString(morningTemperature);
	}

	public static final Parcelable.Creator<Temperature> CREATOR = new Parcelable.Creator<Temperature>(){

		@Override
		public Temperature createFromParcel(Parcel source) {
			return new Temperature(source);
		}

		@Override
		public Temperature[] newArray(int size) {
			return new Temperature[size];
		}
		
	};
	
	private Temperature(Parcel in){
		nowTemperature = in.readString();
		dayTemperature = in.readString();
		minTemperature = in.readString();
		maxTemperature = in.readString();
		nightTemperature = in.readString();
		eveningTemperature = in.readString();
		morningTemperature = in.readString();
	}
	
}
