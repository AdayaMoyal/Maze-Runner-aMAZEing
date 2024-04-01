package com.example.appbyadaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SettingBeforeGame extends AppCompatActivity {

    public void toGameScreen(View v){
        Intent toGameIntent = new Intent(this, Game.class);
        startActivity(toGameIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_before_game);
    }
}