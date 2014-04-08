package com.weazrapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherDescription implements Parcelable {

	private String id;
	private String main;
	private String description;
	private String icon;
	
	public WeatherDescription(){}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	@Override
	public String toString() {
		return "WeatherDescription [id=" + id + ", main=" + main
				+ ", description=" + description + ", icon=" + icon + "]";
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(main);
		dest.writeString(description);
		dest.writeString(icon);
	}
	public static final Parcelable.Creator<WeatherDescription> CREATOR =  new Parcelable.Creator<WeatherDescription>(){

		@Override
		public WeatherDescription createFromParcel(Parcel source) {
			return new WeatherDescription(source);
		}

		@Override
		public WeatherDescription[] newArray(int size) {
			return new WeatherDescription[size];
		}
		
	};
	private WeatherDescription(Parcel in){
		id = in.readString();
		main = in.readString();
		description = in.readString();
		icon = in.readString();
	}
	
	
}
