package com.example.appbyadaya;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageButton;

public class MusicManager {
    private static Intent musicServiceIntent;
    private static boolean musicOn;
    private static Context context;

    MusicManager(Context cnt){
        musicServiceIntent = new Intent(cnt, MusicService.class);
        musicOn = false;
        context = cnt;
    }

    MusicManager(){
    }

    public void startMusic(){
        context.startService(musicServiceIntent);
        musicOn = true;
    }

    public  void stopMusic(){
        context.stopService(musicServiceIntent);
        musicOn = false;
    }

    public boolean getMusicOn(){
        return musicOn;
    }

    public void toggleMusic(ImageButton bt){
        if(musicOn)
        {
            stopMusic();
            bt.setImageResource(R.drawable.off);
            musicOn=false;
        }
        else
        {
            startMusic();
            bt.setImageResource(R.drawable.on);
            musicOn=true;
        }
    }
    public void setButtonIcon(ImageButton bt){
        if(musicOn == true)
        {
            bt.setImageResource(R.drawable.on);
        }
        else
        {
            bt.setImageResource(R.drawable.off);
        }
    }
}
