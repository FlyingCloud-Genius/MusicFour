package com.cuhksz.a4001project;

public interface MusicInterface {
    void play(String musicPath, String playingModule);

    void pausePlay();

    void stop();

    void seekTo(int progress);
}
