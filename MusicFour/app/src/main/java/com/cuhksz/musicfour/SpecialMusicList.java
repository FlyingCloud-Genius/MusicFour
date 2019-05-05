package com.cuhksz.musicfour;


import java.util.ArrayList;
import java.util.List;

public class SpecialMusicList {
    private String musicSheetName;
    private String musicSheetID;
    private int countMusicNum;
    private ArrayList<String> musicInclude = new ArrayList<>();

    public SpecialMusicList(String musicSheetID, String musicSheetName){
        this.musicSheetID = musicSheetID;
        this.musicSheetName = musicSheetName;
        this.countMusicNum = 0;
    }

    public String getMusicSheetName() {
        return musicSheetName;
    }

    public String getMusicSheetID() {
        return musicSheetID;
    }

    public int getCountMusicNum() {
        return countMusicNum;
    }

    public void setCountMusicNum(int countMusicNum) {
        this.countMusicNum = countMusicNum;
    }

    public ArrayList<String> getMusicInclude() {
        return musicInclude;
    }

    public void addMusicInclude(String musicID) {
        this.musicInclude.add(musicID);
    }
}
