package com.example.weatherapp.ui.Fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.App;
import com.example.weatherapp.common.Resource;
import com.example.weatherapp.data.Repositories.MainRepository;
import com.example.weatherapp.model.WeatherApp;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class WeatherViewModel extends ViewModel {
    public LiveData<Resource<WeatherApp>> liveData;
    private MainRepository mainRepository;

    @Inject
    public WeatherViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void getWeather() {
        liveData = mainRepository.getWeatherByCityName("Bishkek");
    }
}
