package com.krak.olympicmap.activity_main.fragments.selected;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.krak.olympicmap.databinding.MapFragmentBinding;
import com.krak.olympicmap.databinding.SelectedFragmentBinding;

public class SelectedFragment extends Fragment {
    private SelectedFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SelectedFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}
