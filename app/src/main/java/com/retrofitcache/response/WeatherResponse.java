package com.retrofitcache.response;

import com.retrofitcache.model.Weather;

import java.util.List;

/**
 * Created by cenktuzun on 18/06/15.
 */
public class WeatherResponse extends BaseResponse {


    private String message;

    private int cnt;

    private List<Weather> list;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<Weather> getList() {
        return list;
    }

    public void setList(List<Weather> list) {
        this.list = list;
    }
}
