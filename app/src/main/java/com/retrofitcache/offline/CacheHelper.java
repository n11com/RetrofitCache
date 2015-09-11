package com.retrofitcache.offline;

import com.google.gson.Gson;
import com.retrofitcache.response.BaseResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cenktuzun on 16/09/14.
 */
public class CacheHelper {

    private static String convertObjectToJson(Object object) {
        Gson gson = new Gson();
        String jsonRepresentation = gson.toJson(object);
        return jsonRepresentation;
    }

    public static BaseResponse convertJsonToRelevantResponse(String json, Class<?> clazz) {
        Gson gson = new Gson();
        return (BaseResponse) gson.fromJson(json, clazz);
    }

    public static Map<String, String> prepareContentValuesMap(BaseResponse response) {
        Map<String, String> contentValues = new HashMap<String, String>();
        contentValues.put(DbHelper.CacheEntry.COLUMN_CONTENT, convertObjectToJson(response));
        contentValues.put(DbHelper.CacheEntry.COLUMN_RESPONSE, response.getClass().getName());
        return contentValues;
    }

}



