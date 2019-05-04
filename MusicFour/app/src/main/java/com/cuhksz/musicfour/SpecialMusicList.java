package com.cuhksz.musicfour;


public class SpecialMusicList {
    private String musicSheetName;
    private String musicSheetID;
    private int countMusicNum;

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
}
