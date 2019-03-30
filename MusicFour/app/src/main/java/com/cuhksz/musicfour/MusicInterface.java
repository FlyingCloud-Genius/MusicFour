package com.cuhksz.musicfour;

public interface MusicInterface {
    void play(String musicPath, String playingModule);

    void pausePlay();

    void stop();

    void seekTo(int progress);
}

