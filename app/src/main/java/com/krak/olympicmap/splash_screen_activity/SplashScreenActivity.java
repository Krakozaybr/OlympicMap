package com.krak.olympicmap.splash_screen_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Intent;
import android.os.Bundle;

import com.krak.olympicmap.R;
import com.krak.olympicmap.activity_main.MainActivity;
import com.krak.olympicmap.databinding.SplashScreenActivityBinding;
import com.krak.olympicmap.name_activity.NameActivity;
import com.krak.olympicmap.utils.PreferenceManager;

public class SplashScreenActivity extends AppCompatActivity {

    private SplashScreenActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashScreenActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        startWaiting();
    }

    // Время вышло, нужно идти дальше
    private void goNext(){
        // Проверяем сохранено ли имя пользователя
        PreferenceManager manager = new PreferenceManager(this);
        Intent intent;
        if (manager.getName().isEmpty()){
            intent = new Intent(SplashScreenActivity.this, NameActivity.class);
        } else {
            intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        }
        startActivity(intent);
    }

    // Ждём 2.5 секунды заставки
    private void startWaiting() {
        addListener();
        // Чтобы экран не завис, ожидание выполняем в отдельном потоке
        new Thread(() -> {
            try{
                Thread.sleep(2500);
            } catch (InterruptedException ignored){
            } finally {
                listener.postValue(true);
            }
        }).start();
    }

    private MutableLiveData<Boolean> listener;

    // Добавляем слушатель к LiveData, чтобы по истечении времени вызвать goNext()
    private void addListener() {
        listener = new MutableLiveData<>();
        listener.observe(this, data -> goNext());
    }
}