package com.krak.olympicmap.activity_main.fragments.info;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.krak.olympicmap.R;
import com.krak.olympicmap.databinding.InfoFragmentBinding;
import com.krak.olympicmap.dialogs.MessageDialog;
import com.krak.olympicmap.utils.CustomAnimations;
import com.krak.olympicmap.utils.DataManager;
import com.krak.olympicmap.utils.PreferenceManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class InfoFragment extends Fragment {

    private static final String TAG = "InfoFragment";

    private InfoFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = InfoFragmentBinding.inflate(getLayoutInflater());
        addEventListeners();
        setInitialData();
        return binding.getRoot();
    }

    private void addEventListeners(){
        addAccordionEventListeners();
        addImageViewsEventListeners();
        binding.adviceCross.setOnClickListener(view -> {
            binding.adviceLayout.setVisibility(View.GONE);
        });
    }

    private void showInfo(int titleId, int textId){
        MessageDialog dialog = new MessageDialog(
                getString(titleId),
                getString(textId)
        );
        dialog.show(getParentFragmentManager(), "custom");
    }

    private void addImageViewsEventListeners(){
        binding.greecePresident.setOnClickListener(view -> {
            showInfo(R.string.greece_president, R.string.greece_president_info);
        });
        binding.mokPresident.setOnClickListener(view -> {
            showInfo(R.string.mok_president, R.string.mok_president_info);
        });
        binding.beijingImage.setOnClickListener(view -> {
            showInfo(R.string.beijing, R.string.beijing_info);
        });
        binding.chanzoiImage.setOnClickListener(view -> {
            showInfo(R.string.chanzoi, R.string.chanzoi_info);
        });
        binding.coinsImagesLayout.setOnClickListener(view -> {
            showInfo(R.string.coins, R.string.coins_info);
        });
    }

    private void addAccordionEventListeners(){
        binding.olympicFireTitleLayout.setOnClickListener(view -> {
            CustomAnimations.toggleAccordion(
                    binding.olympicFireContentLayout,
                    binding.olympicFireTriangle
            );
        });
        binding.volunteeringTitleLayout.setOnClickListener(view -> {
            CustomAnimations.toggleAccordion(
                    binding.volunteeringContentLayout,
                    binding.volunteeringTriangle
            );
        });
        binding.countriesTakePartTitleLayout.setOnClickListener(view -> {
            CustomAnimations.toggleAccordion(
                    binding.countriesTakePartContentLayout,
                    binding.countriesTakePartTriangle
            );
        });
        binding.medalsTitleLayout.setOnClickListener(view -> {
            CustomAnimations.toggleAccordion(
                    binding.medalsContentLayout,
                    binding.medalsTriangle
            );
        });
        binding.coinsTitleLayout.setOnClickListener(view -> {
            CustomAnimations.toggleAccordion(
                    binding.coinsContentLayout,
                    binding.coinsTriangle
            );
        });
    }

    private void setInitialData(){
        MutableLiveData<ArrayList<Medal>> medalsData = new MutableLiveData<>();
        DataManager dataManager = new DataManager(getActivity());
        new Thread(() -> medalsData.postValue(dataManager.loadMedals())).start();
        medalsData.observe(getActivity(), medals -> {
            MedalsAdapter adapter = new MedalsAdapter(getContext(), medals);
            binding.medalsList.setAdapter(adapter);
        });
        // Проверяем видимы ли подсказки
        PreferenceManager preferenceManager = new PreferenceManager((AppCompatActivity) getActivity());
        if (!preferenceManager.getAdvices()){
            binding.adviceLayout.setVisibility(View.GONE);
        }
    }
}
