package com.retrofitcache.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by cenktuzun on 09/09/15.
 */
public class Wind implements Serializable {

    private String speed;

    @SerializedName("deg")
    private String degreee;

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getDegreee() {
        return degreee;
    }

    public void setDegreee(String degreee) {
        this.degreee = degreee;
    }
}
