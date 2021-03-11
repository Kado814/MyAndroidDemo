package com.myapplication.myandroiddemo.activity;

import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.myapplication.myandroiddemo.R;

public class VectorDrawableActivity extends AppCompatActivity {

    private ImageView iv_vector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vector_drawable);
        iv_vector = findViewById(R.id.iv_vector);
        iv_vector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animatable animatable = (Animatable) iv_vector.getDrawable();
                animatable.start();
            }
        });
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Animatable animatable = (Animatable) item.getIcon();
                        animatable.start();
                        break;
                }
                return true;
            }
        });
    }

}