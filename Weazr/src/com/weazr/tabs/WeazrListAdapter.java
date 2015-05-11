package com.weazr.tabs;

import java.util.List;

import com.weazr.main.R;
import com.weazr.utilities.FormatBox;
import com.weazr.utils.Utils;
import com.weazrapi.model.Weather;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeazrListAdapter extends BaseAdapter {

	private Fragment fragment;
	private List<Weather> weazrList;
    private static LayoutInflater inflater=null;

    public WeazrListAdapter(Fragment fragment, List<Weather> weazrList) {
        this.fragment = fragment;
        this.weazrList = weazrList;
        inflater = (LayoutInflater)fragment.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
	@Override
	public int getCount() {
		return weazrList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View view=convertView;
        if(convertView==null)
            view = inflater.inflate(R.layout.list_row, null);
 
        TextView dayTempLbl = (TextView)view.findViewById(R.id.dayTempLbl); 
        TextView weatherDescriptionLbl = (TextView)view.findViewById(R.id.weatherDescriptionLbl); 
        TextView dateLbl = (TextView)view.findViewById(R.id.dateLbl); 
        ImageView weatherIconImg=(ImageView)view.findViewById(R.id.weatherIconImg); 

        Weather weazr = weazrList.get(position);
 
        dayTempLbl.setText(FormatBox.kelvinToFahrenheit(weazr.getTemperature().getDayTemperature())+"�");
        weatherDescriptionLbl.setText(weazr.getWeatherDescription().getDescription());
        dateLbl.setText(FormatBox.getFormattedDateWithoutTime(weazr.getDate()));
       
        int weatherIcon = fragment.getActivity().getResources().getIdentifier("x"+weazr.getWeatherDescription().getIcon(), Utils.DRAWABLE_RT, Utils.R_PACKAGE);
        weatherIconImg.setImageResource(weatherIcon);

        return view;
	}

}
