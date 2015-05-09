package com.weazrapi.webservice;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URLEncoder;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class WebServiceDataImporter {
	
	private static final String TAG = WebServiceDataImporter.class.getSimpleName();
	
	private String dataLocation;
	
	public WebServiceDataImporter(String dataLocation){
		this.dataLocation = dataLocation;
	}
	
	public String getRemoteData(){
		StringBuilder strBuilder = new StringBuilder();
		try{
			Log.d(TAG, ":"+dataLocation);
			URI uri = new URI(dataLocation);
			HttpClient client = new DefaultHttpClient();
			HttpGet methodGet = new HttpGet(uri);
			HttpResponse response = client.execute(methodGet);
			
			InputStream isResponse = response.getEntity().getContent();
			InputStreamReader instream = new InputStreamReader(isResponse);
			BufferedReader responseBuffer = new BufferedReader(instream);
			
			String line = null;
			while((line = responseBuffer.readLine()) != null){
				strBuilder.append(line);
			}
	}catch(Exception e){
		Log.e(TAG, "error in getRemoteData() "+e.getLocalizedMessage());
	}
		return strBuilder.toString();
	}
}
