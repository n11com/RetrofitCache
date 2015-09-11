package com.retrofitcache.retrofit;

import android.content.Context;
import android.widget.Toast;

import com.retrofitcache.MainActivity;
import com.retrofitcache.MyApplication;
import com.retrofitcache.R;
import com.retrofitcache.offline.CacheManager;
import com.retrofitcache.response.BaseResponse;

import java.io.IOException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cenktuzun on 27/03/15.
 */
public abstract class RetrofitCallback<T> implements Callback<T> {

    private Context context;
    private Class<?> clazz;

    public abstract void onSuccess(T t, Response response);

    public abstract void onFailure(RetrofitError error);

    public RetrofitCallback(Class<?> clazz) {
        this.clazz = clazz;
        this.context = MyApplication.getContext();
    }

    @Override
    public void failure(RetrofitError error) {
        if (error.getKind() == RetrofitError.Kind.NETWORK) {
            if (CacheManager.isCacheManagerActive && clazz != null) {
                BaseResponse cachedResponse = CacheManager.getInstance().fetchResponseFromCache(clazz.getName());
                if (cachedResponse != null) {
                    onSuccess((T) cachedResponse, null);
                    Toast.makeText(context, context.getString(R.string.cache_offline_mode), Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        }
        onFailure(error);
    }

    @Override
    public void success(T t, Response response) {
        // Pretend like no internet connection
        // Comment out this control
        if (MainActivity.isOfflineMode) {
            failure(RetrofitError.networkError("customNetworkError", new IOException()));
            return;
        }
        if (CacheManager.isCacheManagerActive && clazz != null) {
            CacheManager.getInstance().saveResponse((BaseResponse) t);
        }
        onSuccess(t, response);
        Toast.makeText(context, context.getString(R.string.fetch_success), Toast.LENGTH_SHORT).show();
    }

}
