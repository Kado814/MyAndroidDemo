package com.myapplication.myandroiddemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.fragment.BlankFragment;

public class FragmentInitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_init);
        if (savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.layout_top, new BlankFragment("顶部的Fragment", "test"));//fragment重建的时候会有异常
            transaction.add(R.id.layout_bottom, BlankFragment.newInstance("底部的Fragment", "test"));
            transaction.commit();
        }
    }
}