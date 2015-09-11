package com.retrofitcache.offline;

import android.app.Activity;
import android.database.Cursor;

import com.retrofitcache.response.BaseResponse;

/**
 * Created by cenktuzun on 16/09/14.
 */
public class CacheManager {

    public static boolean isCacheManagerActive = true;
    private DbHelper dbHelper;
    private static CacheManager cacheManager;

    public void initializeCacheManager(Activity activity) {
        dbHelper = new DbHelper(activity);
    }

    public static CacheManager getInstance() {
        if (cacheManager == null) {
            cacheManager = new CacheManager();
        }
        return cacheManager;
    }

    public void saveResponse(BaseResponse response) {
        dbHelper.insert(CacheHelper.prepareContentValuesMap(response));
    }

    public BaseResponse fetchResponseFromCache(String response) {
        Cursor cursor = dbHelper.read(response);
        boolean isEmptyControl = cursor.moveToFirst();
        if (!isEmptyControl) {
            return null;
        }
        int columnIndex = cursor.getColumnIndex(DbHelper.CacheEntry.COLUMN_CONTENT);
        String jsonContent = cursor.getString(columnIndex);
        Class<?> classType;
        cursor.close();
        try {
            classType = Class.forName(response);
            return CacheHelper.convertJsonToRelevantResponse(jsonContent, classType);
        } catch (Exception e) {
            //Log.e("RetrofitCache", getClass().getSimpleName(), e);
        }
        return null;
    }

}
