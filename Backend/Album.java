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

    public Album(String albumID, String album, String publisherID, String publisher, String publishDate, String musicianID, String musician){
        this.map.put("albumID", albumID);
        this.map.put("album",album);
        this.map.put("publisherID", publisherID);
        this.map.put("publisher", publisher);
        this.map.put("publishDate", publishDate);
        this.map.put("musicianID", musicianID);
        this.map.put("musician", musician);
    }

    public Album(String albumID, String album, String publisherID, String publisher, String publishDate, String musicianID, String musician, int imageID){
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

        ConnectMySql connector = new ConnectMySql();
        List<Album> albumInfoList = connector.getAlbumInfo();
        for (int i = 0; i < albumInfoList.size(); i++){
            albums.add(albumInfoList.get(i).map);
        }
    }

    public static ArrayList<Map<String, Object>> getAlbums(){
        return albums;
    }
}