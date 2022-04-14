package com.krak.olympicmap.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class CustomAnimations {
    public static void showAccordion(View view){
        view.animate()
                .translationYBy(-view.getHeight()).translationY(0).setDuration(100)
                .alpha(1f)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.VISIBLE);
                        view.bringToFront();
                    }
                });
    }

    public static void hideAccordion(View view){
        view.animate()
                .translationY(view.getHeight())
                .alpha(0f)
                .setDuration(300)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        view.setVisibility(View.GONE);
                    }
                });
    }

    private static boolean isVisible(View view){
        return view.getVisibility() == View.VISIBLE;
    }

    public static void toggleAccordion(View view, View indicator){
        if (isVisible(view)){
            hideAccordion(view);
            indicator.setRotation(30);
        } else {
            showAccordion(view);
            indicator.setRotation(0);
        }
    }
}
