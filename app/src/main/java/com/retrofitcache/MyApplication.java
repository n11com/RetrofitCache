package com.retrofitcache;

import android.app.Application;
import android.content.Context;

/**
 * Created by cenktuzun on 08/09/15.
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}