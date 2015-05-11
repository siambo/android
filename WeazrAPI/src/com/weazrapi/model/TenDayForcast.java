package com.weazrapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class TenDayForcast extends Forcast{

	public TenDayForcast(){
		super();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
	}
	
	public static final Parcelable.Creator<TenDayForcast> CREATOR = new Parcelable.Creator<TenDayForcast>() {

		@Override
		public TenDayForcast createFromParcel(Parcel source) {
			return new TenDayForcast(source);
		}

		@Override
		public TenDayForcast[] newArray(int size) {
			return new TenDayForcast[size];
		}
	};
	

	private  TenDayForcast(Parcel in) {
		super(in);
	}
	
}
