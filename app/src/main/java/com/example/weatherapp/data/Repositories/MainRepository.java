package com.example.weatherapp.data.Repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.weatherapp.ui.Fragments.MainFragment;
import com.example.weatherapp.common.Resource;
import com.example.weatherapp.model.WeatherApp;
import com.example.weatherapp.remote.WeatherApi;

import javax.inject.Inject;

import dagger.Provides;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private WeatherApi api;

    @Inject
    public MainRepository(WeatherApi api) {
        this.api = api;
    }
    public MutableLiveData<Resource<WeatherApp>> getWeatherByCityName(String city){
        MutableLiveData<Resource<WeatherApp>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getTemp("Bishkek", "c93f8cc9beb1256bb74e844e646f1ae4", "metric").enqueue(new Callback<WeatherApp>() {
            @Override
            public void onResponse(Call<WeatherApp> call, Response<WeatherApp> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(Resource.success(response.body()));
                } else {
                    liveData.setValue(Resource.error(response.message(), null));
                    Log.e("ERROR IS: ", response.message());

                }
            }

                @Override
                public void onFailure (Call <WeatherApp> call, Throwable t){
                    liveData.setValue(Resource.error(t.getLocalizedMessage(), null));
                    Log.e("ERROR IS: ", t.getLocalizedMessage());
                }
            });
        return liveData;
    }
}