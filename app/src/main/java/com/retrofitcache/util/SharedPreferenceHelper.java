package com.retrofitcache.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.retrofitcache.MyApplication;

/**
 * Created by cenktuzun on 09/09/15.
 */
public final class SharedPreferenceHelper {

    private static final String APP_SCOPE = "APP_SCOPE";

    public static boolean getBoolFromShared(String key) {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(APP_SCOPE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public static void putBoolToShared(String key, boolean value) {
        SharedPreferences.Editor editor = getEditorForWrite();
        editor.putBoolean(key, value);
        editor.commit();
    }

    private static SharedPreferences.Editor getEditorForWrite() {
        SharedPreferences sharedPreferences = MyApplication.getContext().getSharedPreferences(APP_SCOPE, Context.MODE_PRIVATE);
        return sharedPreferences.edit();
    }

}
