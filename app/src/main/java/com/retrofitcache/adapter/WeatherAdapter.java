package com.retrofitcache.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.retrofitcache.R;
import com.retrofitcache.model.Weather;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by cenktuzun on 18/06/15.
 */
public class WeatherAdapter extends BaseAdapter {

    private Context context;
    private List<Weather> list;
    private ViewHolder viewHolder;
    private static final String WEATHER_CONDITION_ICON_PREFIX = "http://openweathermap.org/img/w/";
    private static final String WEATHER_CONDITION_ICON_FINISHER = ".png";

    public WeatherAdapter(Context context, List<Weather> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.weather_row_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.dateTextView = (TextView) convertView.findViewById(R.id.dateTextView);
            viewHolder.tempTextView = (TextView) convertView.findViewById(R.id.tempTextView);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Weather weather = list.get(position);
        viewHolder.dateTextView.setText(weather.getDateText());
        viewHolder.tempTextView.setText(weather.getMain().getTemperature() + "\u2103");
        Picasso.with(context).load(getWeatherConditionIconPath(weather)).into(viewHolder.icon);
        return convertView;
    }

    static class ViewHolder {
        TextView tempTextView;
        TextView dateTextView;
        ImageView icon;
    }

    private String getWeatherConditionIconPath(Weather weather) {
        StringBuilder builder = new StringBuilder(WEATHER_CONDITION_ICON_PREFIX);
        return builder.append(weather.getWeather().get(0).getIcon()).append(WEATHER_CONDITION_ICON_FINISHER).toString();
    }

}
