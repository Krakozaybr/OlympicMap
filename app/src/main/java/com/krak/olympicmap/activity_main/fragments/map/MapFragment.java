package com.krak.olympicmap.activity_main.fragments.map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.krak.olympicmap.R;
import com.krak.olympicmap.activity_main.fragments.map.clusters.ClustersManager;
import com.krak.olympicmap.databinding.MapFragmentBinding;
import com.krak.olympicmap.utils.CustomAnimations;
import com.krak.olympicmap.utils.PointsStorage;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class MapFragment extends Fragment {

    private ClustersManager clustersManager;
    private MapFragmentBinding binding;
    private MapView mapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = MapFragmentBinding.inflate(getLayoutInflater());
        mapView = binding.mapview;
        // Грузим информацию из countries.csv, чтобы использовать её в дальнейшем
        PointsStorage.loadData(getActivity());
        gotoBeijing();
        initManagers();
        addListeners();
        // Чтобы кнопка опций была видна
        binding.optionsBtn.bringToFront();
        return binding.getRoot();
    }

    private boolean clustersVisible = false;

    private void addListeners() {
        // Нажата кнопка "Опции"
        binding.optionsBtn.setOnClickListener(view -> CustomAnimations.toggleAccordion(binding.options));
        // Нажата кнопка "Показать/скрыть страны-участники"
        binding.toggleClustersBtn.setOnClickListener(view -> toggleClusters());
        // Нажата кнопка "Перейти к месту проведения Олимпийских игр"
        binding.gotoBeijingBtn.setOnClickListener(view -> gotoBeijing());
    }

    // Переносим карту к Пекину
    private void gotoBeijing() {
        mapView.getMap().move(
                new CameraPosition(PointsStorage.getBeijing(), 12.57f, 0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);
    }

    // Включаем/выключаем кластеризацию
    private void toggleClusters(){
        clustersVisible = !clustersVisible;
        if (!clustersVisible){
            clustersManager.hide();
            binding.toggleClustersBtn.setText(R.string.show_clusters);
        } else {
            clustersManager.show();
            binding.toggleClustersBtn.setText(R.string.hide_clusters);
        }
    }

    private void initManagers() {
        clustersManager = new ClustersManager(getActivity(), mapView);
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }
}
