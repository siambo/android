package com.weazr.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.util.Log;

public class FormatBox {
	
	private static final String TAG = FormatBox.class.getSimpleName();
	
	public static String removeWhiteSpace(String str){
		String pattern = "[\\s]";
		String replace ="%20";
		
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(str);
		str = m.replaceAll(replace);
		return str;
	}
	
	public static String kelvinToCelsius(String kelvin){
		double temp = Float.parseFloat(kelvin) - 273.15;
		return String.valueOf(Math.round(temp));

	}
	
	public static String kelvinToFahrenheit(String kelvin){
		try{
			double temp = (9/5) * (Float.parseFloat(kelvin) - 273.15) +32;
			return String.valueOf(Math.round(temp));
		}catch(Exception e){
			Log.e(TAG, "Something serious happened here");
			return null;
		}
		
	}
	
	public static String getFormattedDate(String str){
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy hh:mm a", Locale.US);
		long tempDate = Long.parseLong(str)*1000;
		Date date = new Date(tempDate);
		return dateFormat.format(date);

	}
	
	public static String getFormattedDateWithoutTime(String str){
		DateFormat dateFormat = new SimpleDateFormat("EEE, MMM d, yyyy", Locale.US);
		long tempDate = Long.parseLong(str)*1000;
		Date date = new Date(tempDate);
		return dateFormat.format(date);
	}
	
}
