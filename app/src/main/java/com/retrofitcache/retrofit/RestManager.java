package com.retrofitcache.retrofit;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by cenktuzun on 27/03/15.
 */
public class RestManager {

    private static RestManager restManager;
    private RestAdapter restAdapter;
    public static final String SERVICE_URL = "http://api.openweathermap.org/data/2.5/forecast/";
    public static final boolean IS_DEV_MODE = true;
    public static final long TIMEOUT = 10;

    public static RestManager getInstance() {
        if (restManager == null) {
            restManager = new RestManager();
        }
        return restManager;
    }

    private RestManager() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(TIMEOUT, TimeUnit.SECONDS);
        RestAdapter.Builder builder = new RestAdapter.Builder().setEndpoint(SERVICE_URL).setClient(new OkClient(client));
        if (IS_DEV_MODE) {
            builder.setLogLevel(RestAdapter.LogLevel.FULL);
        }
        restAdapter = builder.build();
    }

    public <T> T getService(Class<T> tClass) {
        return restAdapter.create(tClass);
    }

}
