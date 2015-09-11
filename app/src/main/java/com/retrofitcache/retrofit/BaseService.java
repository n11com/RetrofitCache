package com.retrofitcache.retrofit;

import com.retrofitcache.response.BaseResponse;
import com.retrofitcache.response.WeatherResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by cenktuzun on 27/03/15.
 */
public interface BaseService {

    @GET("/hourly")
    void hourly(@Query("q") String city, @Query("mode") String mode, @Query("units") String units, @Query("cnt") String count, Callback<WeatherResponse> callback);

}
