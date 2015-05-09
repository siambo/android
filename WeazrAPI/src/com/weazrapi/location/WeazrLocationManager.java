package com.weazrapi.location;

import java.util.List;
import java.util.Locale;
import com.weazrapi.model.UserLocation;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class WeazrLocationManager extends AbstractLocationService{
	
	private static final String TAG = WeazrLocationManager.class.getSimpleName();
	
	private Context context;
	private UserLocation userLocation;
	private Location location;
	private boolean isGPSEnabled = false;
	private LocationManager locationManager;
	
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1000;
	private static final long MIN_TIME_GAP_BTWN_UPDATES = 1000*60*1;
	
	
	public WeazrLocationManager(Context context){
		this.context = context;
		this.userLocation = new UserLocation();
	}
	
	private UserLocation processLocation(Location location){

		Geocoder geocoder = new Geocoder(context, Locale.getDefault());
		try{
			
			List<Address> addressList  = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
			
			Address address = addressList.get(0);
			userLocation.setCityName(address.getLocality());
			userLocation.setCountryName(address.getCountryName());
			userLocation.setCountryCode(address.getCountryCode());
			userLocation.setLatCoordinate(String.valueOf(address.getLatitude()));
			userLocation.setLonCoordinate(String.valueOf(address.getLongitude()));
			Log.d(TAG,": "+userLocation.getCityName()+" "+userLocation.getCountryName()+" "+userLocation.getCountryCode()+" "+address.getLatitude()+" "+address.getLongitude());
			
			return userLocation;
		}catch(Exception e){
			Log.e(TAG,"processLocation(): "+e.getLocalizedMessage());
			e.printStackTrace();
			return null;
		}
	}

	public UserLocation getLocation(){
		try{
			locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

			if(isGPSEnabled){
				if(location == null){
					locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME_GAP_BTWN_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES,this);
						if(locationManager != null){
							location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							  if(location != null){
								return processLocation(location);
						      }
						}
				}
			 }else{
				 Log.w(TAG,"readLocation(): Location not available");
				 return null;
			}
			return null;
		}catch(Exception e){
			Log.e(TAG, "getLocation(): "+e.getLocalizedMessage());
			e.printStackTrace();
			return null;
		}
	}

}

