package com.cuhksz.musicfour;


public class SpecialMusic {
    private String musicID;
    private String musicName;
    private String musicianName;

    public SpecialMusic(String musicID, String musicName, String musicianName) {
        this.musicID = musicID;
        this.musicName = musicName;
        this.musicianName = musicianName;
    }

    public String getMusicID() {
        return musicID;
    }

    public String getMusicName() {
        return musicName;
    }

    public String getMusicianName() {
        return musicianName;
    }

}
