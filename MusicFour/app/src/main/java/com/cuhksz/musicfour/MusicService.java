package com.cuhksz.musicfour;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MusicService extends Service {
    private MediaPlayer player;
    private Timer timer;
    private String playingModule;

    public IBinder onBind(Intent intent) {
        return new MusicControl();
    }

    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        player.setOnCompletionListener(new MyOnCompletionListener());
    }

    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
        player = null;
    }

    public void play(String music, String playingModule) {
        if (player != null) {
            player.reset();
        }
        this.playingModule = playingModule;
        try {
            if (player == null) {
                player = new MediaPlayer();
            }

            player.setDataSource(music);
            player.prepare();
            player.start();
            addTimer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pausePlay() {
        if (player.isPlaying()) {
            player.pause();
        } else {
            player.start();
        }
    }

    public void stop() {
        player.stop();
        player.reset();
        player.release();
        player = null;
    }

    private class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {

        /**
         * Called when the end of a media source is reached during playback.
         *
         * @param mp the MediaPlayer that reached the end of the file
         */
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (!mp.isPlaying()) {
                stop();
                if (playingModule == "RandomSong") {
                    MainActivity.currentSongIndex = new Random().nextInt(MainActivity.musics.length);
                } // randomly play
                else if (playingModule == "SameSong") {

                } // play the same song
                else //play the next song again
                {
                    MainActivity.currentSongIndex += 1;
                    if (MainActivity.currentSongIndex == MainActivity.musics.length) {
                        MainActivity.currentSongIndex = 0;
                    }
                }
                String song = MainActivity.currentSong();
                MusicService.this.play(song, playingModule);

            }
        }
    }

    private class MusicControl extends Binder implements MusicInterface {
        public void play(String music, String playingModule) {
            MusicService.this.play(music, playingModule);
        }

        public void pausePlay() {
            MusicService.this.pausePlay();
        }

        public void stop() {
            MusicService.this.stop();
        }

        public void seekTo(int progress) {
            MusicService.this.seekTo(progress);
        }
    }

    public void seekTo(int progress) {
        player.seekTo(progress);
    }

    public void addTimer() {
        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    if (player != null) {
                        int duration = player.getDuration();
                        int currentPosition = player.getCurrentPosition();

                        Message message = MainActivity.handler.obtainMessage();

                        Bundle bundle = new Bundle();
                        bundle.putInt("duration", duration);
                        bundle.putInt("currentPosition", currentPosition);
                        message.setData(bundle);

                        MainActivity.handler.sendMessage(message);
                    }
                }
            }, 5, 500);
        }
    }
}
