package com.example.appbyadaya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

public class Game extends AppCompatActivity {
    private FrameLayout frame;
    private boolean started=false;
    private int scrHeight;
    private int scrWidth;
    GameManager myGameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        frame = findViewById(R.id.gameFrame);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        scrHeight=frame.getHeight();
        scrWidth=frame.getWidth();
        myGameManager = new GameManager(this, scrWidth,scrHeight);
        frame.addView(myGameManager);
    }

}