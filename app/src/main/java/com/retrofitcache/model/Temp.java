package com.retrofitcache.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cenktuzun on 18/06/15.
 */
public class Temp implements Serializable {

    @SerializedName("temp")
    private String temperature;
    @SerializedName("temp_min")
    private String minTemperature;
    @SerializedName("temp_max")
    private String maxTemperature;
    @SerializedName("sea_level")
    private String seaLevel;
    @SerializedName("grnd_level")
    private String groundLevel;
    @SerializedName("temp_kf")
    private String tempKf;
    private String pressure;
    private String humidity;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public String getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(String seaLevel) {
        this.seaLevel = seaLevel;
    }

    public String getGroundLevel() {
        return groundLevel;
    }

    public void setGroundLevel(String groundLevel) {
        this.groundLevel = groundLevel;
    }

    public String getTempKf() {
        return tempKf;
    }

    public void setTempKf(String tempKf) {
        this.tempKf = tempKf;
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
}
