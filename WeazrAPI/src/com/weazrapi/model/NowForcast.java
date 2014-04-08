package com.weazrapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class NowForcast extends Forcast {
	
	private String base;

	public NowForcast() {
		super();

	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	@Override
	public String toString() {
		return "NowForcast [base=" + base + "]" + super.toString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(base);
	}
	
	public static final Parcelable.Creator<NowForcast> CREATOR = new Parcelable.Creator<NowForcast>() {

		@Override
		public NowForcast createFromParcel(Parcel source) {
			return new NowForcast(source);
		}

		@Override
		public NowForcast[] newArray(int size) {
			return new NowForcast[size];
		}
	};

	private  NowForcast(Parcel in) {
		super(in);
		base = in.readString();
	}
	
    
	
}
