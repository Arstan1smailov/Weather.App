package com.example.weatherapp;

import android.app.Application;

import com.example.weatherapp.data.Repositories.MainRepository;
import com.example.weatherapp.remote.RetrofitClient;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class App extends Application {
}
