package com.retrofitcache;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.retrofitcache.adapter.WeatherAdapter;
import com.retrofitcache.model.Weather;
import com.retrofitcache.offline.CacheManager;
import com.retrofitcache.response.WeatherResponse;
import com.retrofitcache.retrofit.BaseService;
import com.retrofitcache.retrofit.RestManager;
import com.retrofitcache.retrofit.RetrofitCallback;
import com.retrofitcache.util.SharedPreferenceHelper;

import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by cenktuzun on 08/09/15.
 */
public class MainActivity extends ActionBarActivity {

    // Request params
    private static final String PARAM_CITY = "izmir";
    private static final String PARAM_MODE = "json";
    private static final String PARAM_UNITS = "metric";
    private static final String PARAM_COUNTER = "35";

    // Offline mode params
    private static final String IS_OFFLINE_MODE = "isOfflineMode";
    public static boolean isOfflineMode;

    // Views and adapter
    private ListView listView;
    private ProgressBar progressBar;
    private SwitchCompat switchButton;
    private WeatherAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeCacheManager();
        initializeViews();
        fillViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_help) {
            showHelpDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeCacheManager() {
        CacheManager.getInstance().initializeCacheManager(this);
    }

    private void initializeViews() {
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        final TextView switchText = (TextView) findViewById(R.id.switchText);
        switchButton = (SwitchCompat) findViewById(R.id.switchButton);
        ImageView refreshImageView = (ImageView) findViewById(R.id.refreshImageView);
        //Click Handlers
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Weather weather = (Weather) adapter.getItem(position);
                Toast.makeText(MyApplication.getContext(), weather.getWeather().get(0).getMain(), Toast.LENGTH_SHORT).show();
            }
        });
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String switchTextValue = isChecked ? getResources().getString(R.string.online_mode) : getResources().getString(R.string.offline_mode);
                switchText.setText(switchTextValue);
                isOfflineMode = !isChecked;
                saveOfflineModeStatus(isChecked);
            }
        });
        refreshImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillViews();
            }
        });
    }

    private void fillViews() {
        showProgressBar();
        BaseService service = RestManager.getInstance().getService(BaseService.class);
        service.hourly(PARAM_CITY, PARAM_MODE, PARAM_UNITS, PARAM_COUNTER, new RetrofitCallback<WeatherResponse>(WeatherResponse.class) {
            @Override
            public void onSuccess(WeatherResponse baseResponse, Response response) {
                refreshWeatherList(baseResponse.getList());
                hideProgressBar();
            }

            public void onFailure(RetrofitError error) {
                hideProgressBar();
            }
        });
        controlOfflineMode();
    }

    private void refreshWeatherList(List<Weather> list) {
        adapter = new WeatherAdapter(this, list);
        listView.setAdapter(adapter);
    }

    private void controlOfflineMode() {
        switchButton.setChecked(!SharedPreferenceHelper.getBoolFromShared(IS_OFFLINE_MODE));
    }

    private void saveOfflineModeStatus(boolean isChecked) {
        SharedPreferenceHelper.putBoolToShared(IS_OFFLINE_MODE, !isChecked);
    }

    private void showHelpDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(getString(R.string.app_name));
        dialog.setMessage(getString(R.string.help_message));
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

}
