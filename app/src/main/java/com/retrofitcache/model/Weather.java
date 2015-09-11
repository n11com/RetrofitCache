package com.retrofitcache.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cenktuzun on 18/06/15.
 */
public class Weather implements Serializable {

    private Temp main;
    private List<WeatherDetail> weather;
    private Cloud clouds;
    private Wind wind;
    @SerializedName("dt_txt")
    private String dateText;

    public Temp getMain() {
        return main;
    }

    public void setMain(Temp main) {
        this.main = main;
    }

    public List<WeatherDetail> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDetail> weather) {
        this.weather = weather;
    }

    public Cloud getClouds() {
        return clouds;
    }

    public void setClouds(Cloud clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public String getDateText() {
        return dateText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }
}
