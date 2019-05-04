package com.cuhksz.musicfour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mike on 2019/4/8.
 */

public class Album {
    private Map<String, Object> map = new HashMap<String,Object>();
    private static final ArrayList<Map<String, Object>> albums= new ArrayList<Map<String, Object>>();

    private Album(String albumID, String album, String publisherID, String publisher, String publishDate, String musicianID, String musician){
        this.map.put("albumID", albumID);
        this.map.put("album",album);
        this.map.put("publisherID", publisherID);
        this.map.put("publisher", publisher);
        this.map.put("publishDate", publishDate);
        this.map.put("musicianID", musicianID);
        this.map.put("musician", musician);
    }

    private Album(String albumID, String album, String publisherID, String publisher, String publishDate, String musicianID, String musician, int imageID){
        this.map.put("albumID", albumID);
        this.map.put("album",album);
        this.map.put("publisherID", publisherID);
        this.map.put("publisher", publisher);
        this.map.put("publishDate", publishDate);
        this.map.put("musicianID", musicianID);
        this.map.put("musician", musician);
        this.map.put("imageID", imageID);
    }

    public static void buildAlbums(){
        albums.clear();
        Album album1 = new Album("00001", "剑灵原声音乐选辑", "00001", "大韩音乐公司", "2017-08-10","00001", "金致逸",R.drawable.music00001);
        albums.add(album1.map);

    }

    public static ArrayList<Map<String, Object>> getAlbums(){
        return albums;
    }
}
