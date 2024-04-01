package com.example.appbyadaya;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MusicService extends Service {
    MediaPlayer myPlayer;

    public MusicService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myPlayer = MediaPlayer.create(this, R.raw.music_for_game);
        myPlayer.setLooping(true);
        myPlayer.setVolume(100,100);
        myPlayer.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myPlayer.stop();
        myPlayer.release();
    }
}
