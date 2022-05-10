package com.example.weatherapp.ui.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.common.Resource;
import com.example.weatherapp.databinding.FragmentMainBinding;
import com.example.weatherapp.model.WeatherApp;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {

    FragmentMainBinding binding;
    NavController navController;
    private WeatherViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(WeatherViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.TV.setText("fdsf");
    }

    private void sendRequest() {
        viewModel.getWeather();
    }

    private void initViewModel() {
        viewModel.liveData.observe(getViewLifecycleOwner(), new Observer<Resource<WeatherApp>>() {
            @Override
            public void onChanged(Resource<WeatherApp> weatherAppResource) {
                switch (weatherAppResource.status) {
                    case SUCCESS:
                        initData(weatherAppResource.data);
                        break;
                    case ERROR:
                        Toast.makeText(requireContext(), "aaaa", Toast.LENGTH_SHORT).show();
                        break;
                    case LOADING:
                        break;
                }
            }
        });
    }

    private void initData(WeatherApp data) {
        binding.TV.setText(data.getMain().getTemp().toString());
    }
}