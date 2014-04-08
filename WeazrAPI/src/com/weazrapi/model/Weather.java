package com.weazrapi.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Weather implements IModel, Parcelable {

	private String date;
	private Temperature temperature;
	private String pressure;
	private String humidity;
	private WeatherDescription weatherDescription;
	private String speed;
	private String degree;
	private String snow;
	private String rain;
	private String thunderstorm;
	private String drizzle;
	private String atmosphere;
	private String clouds;
	private String extreme;
	
	public Weather(){
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Temperature getTemperature() {
		return temperature;
	}
	public void setTemperature(Temperature temperature) {
		this.temperature = temperature;
	}
	public String getPressure() {
		return pressure;
	}
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public WeatherDescription getWeatherDescription() {
		return weatherDescription;
	}
	public void setWeatherDescription(WeatherDescription weatherDescription) {
		this.weatherDescription = weatherDescription;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getSnow() {
		return snow;
	}
	public void setSnow(String snow) {
		this.snow = snow;
	}
	public String getRain() {
		return rain;
	}
	public void setRain(String rain) {
		this.rain = rain;
	}
	public String getThunderstorm() {
		return thunderstorm;
	}
	public void setThunderstorm(String thunderstorm) {
		this.thunderstorm = thunderstorm;
	}
	public String getDrizzle() {
		return drizzle;
	}
	public void setDrizzle(String drizzle) {
		this.drizzle = drizzle;
	}
	public String getAtmosphere() {
		return atmosphere;
	}
	public void setAtmosphere(String atmosphere) {
		this.atmosphere = atmosphere;
	}
	public String getClouds() {
		return clouds;
	}
	public void setClouds(String clouds) {
		this.clouds = clouds;
	}
	public String getExtreme() {
		return extreme;
	}
	public void setExtreme(String extreme) {
		this.extreme = extreme;
	}

	@Override
	public String toString() {
		return "Weather [date=" + date + ", temperature=" + temperature
				+ ", pressure=" + pressure + ", humidity=" + humidity
				+ ", weatherDescription=" + weatherDescription + ", speed="
				+ speed + ", degree=" + degree + ", snow=" + snow + ", rain="
				+ rain + ", thunderstorm=" + thunderstorm + ", drizzle="
				+ drizzle + ", atmosphere=" + atmosphere + ", clouds=" + clouds
				+ ", extreme=" + extreme + "]";
	}

	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(date);
		dest.writeParcelable(temperature, flags);
		dest.writeString(pressure);
		dest.writeString(humidity);
		dest.writeParcelable(weatherDescription, flags);
		dest.writeString(speed);
		dest.writeString(degree);
		dest.writeString(snow);
		dest.writeString(rain);
		dest.writeString(thunderstorm);
		dest.writeString(drizzle);
		dest.writeString(atmosphere);
		dest.writeString(clouds);
		dest.writeString(extreme);
	}
	
	public static final Parcelable.Creator<Weather> CREATOR = new Parcelable.Creator<Weather>() {

		@Override
		public Weather createFromParcel(Parcel source) {
			return new Weather(source);
		}

		@Override
		public Weather[] newArray(int size) {
			return new Weather[size];
		}
	};
	
	private Weather(Parcel in){
		date = in.readString();
		temperature = (Temperature)in.readParcelable(Temperature.class.getClassLoader());
		pressure = in.readString();
		humidity = in.readString();
		weatherDescription = (WeatherDescription)in.readParcelable(WeatherDescription.class.getClassLoader());
		speed = in.readString();
		degree = in.readString();
		snow = in.readString();
		rain = in.readString();
		thunderstorm = in.readString();
		drizzle = in.readString();
		atmosphere = in.readString();
		clouds = in.readString();
		extreme = in.readString();
	}

}
