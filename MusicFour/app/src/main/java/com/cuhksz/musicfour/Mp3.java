package com.cuhksz.musicfour;

import android.util.Log;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.ID3v1;
import org.farng.mp3.lyrics3.AbstractLyrics3;

import java.io.File;
import java.io.IOException;

public class Mp3 {
    private String mp3Path;
    private String musicTitle;
    private String artist;
    private String publishYear;
    private String album;
    private String musicLyrics;

    public Mp3(String mp3Path) {
        this.mp3Path = mp3Path;
        getInfo();
    }

    private void getInfo() {
        try {
            MP3File file = new MP3File(mp3Path);
            AbstractID3v2 id3v2 = file.getID3v2Tag();
            ID3v1 id3v1 = file.getID3v1Tag();

            if (id3v2 != null) {
                album = id3v2.getAlbumTitle();//album
                musicTitle = id3v2.getSongTitle();//musicTitle
                artist = id3v2.getLeadArtist();//artist
                publishYear = id3v2.getYearReleased();//released year
            } else {
                album = id3v2.getAlbumTitle();//album
                musicTitle = id3v2.getSongTitle();//musicTitle
                artist = id3v2.getLeadArtist();//artist
                publishYear = id3v2.getYearReleased();//released year
            }

            AbstractLyrics3 lrc3Tag = file.getLyrics3Tag();
            if (lrc3Tag != null) {
                musicLyrics = lrc3Tag.getSongLyric();
                System.out.println(musicLyrics);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        }
    }

    public String getArtist() {
        return artist;
    }

    public String getAlbum() {
        return album;
    }

    public String getMusicTitle() {
        return musicTitle;
    }

    public String getMusicLyrics() {
        return musicLyrics;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public static void main(String[] args) {
        Mp3 mp3 = new Mp3("D:\\4001project\\MusicFour\\app\\src\\main\\res\\7AND5 - Remember.mp3");
        System.out.println(mp3.getAlbum());
        System.out.println(mp3.getArtist());
        System.out.println(mp3.getMusicLyrics());
        System.out.println(mp3.getMusicTitle());
        System.out.println(mp3.getPublishYear());
    }


}
