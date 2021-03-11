package com.myapplication.myandroiddemo.activity;

import android.animation.Animator;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.myapplication.myandroiddemo.R;

public class LottieActivity extends AppCompatActivity {

    private LottieAnimationView animation_view;
    private LottieAnimationView animation_view_demo;
    private boolean checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        animation_view = findViewById(R.id.animation_view);
        animation_view_demo = findViewById(R.id.animation_view_demo);

        animation_view.setAnimation("data.json");
        animation_view_demo.setAnimation("demo.json");

        animation_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!animation_view.isAnimating()) {
                    animation_view.playAnimation();
                    checked = !checked;
                }
            }
        });
        animation_view_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!animation_view_demo.isAnimating()) {
                    animation_view_demo.playAnimation();
                    animation_view_demo.loop(true);
                }
            }
        });
        animation_view.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (checked) {
                    animation_view.setFrame(0);
                } else {
                    animation_view.setFrame(20);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}