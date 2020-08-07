package com.myapplication.myandroiddemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.myapplication.myandroiddemo.R;

public class MyGame2048Activity extends AppCompatActivity {

    private int score = 0;
    private TextView tvScore;
    private static MyGame2048Activity mainActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_game2048);

        tvScore = (TextView) findViewById(R.id.tv_score);
    }

    public void clearScore() {
        score = 0;
        showScore();
    }

    public void showScore() {
        tvScore.setText(score + "");
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }
}
