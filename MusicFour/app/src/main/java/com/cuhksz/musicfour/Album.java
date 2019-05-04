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

    private Album(int albumID, String album, String publician, ArrayList<String> musicsID){
        this.map.put("albumID", albumID);
        this.map.put("album",album);
        this.map.put("publician", publician);
        this.map.put("musicsID", musicsID);
    }

    private Album(int albumID, String album, String publician, ArrayList<String> musicsID, int imageID){
        this.map.put("albumID", albumID);
        this.map.put("album",album);
        this.map.put("publician", publician);
        this.map.put("musicsID", musicsID);
        this.map.put("imageID", imageID);
    }

    public static void buildAlbum(){
        albums.clear();
        ArrayList<String> musics1 = new ArrayList<>();
        musics1.add("");
        Album music1 = new Album(00001, "剑灵原声音乐选辑", "大韩音乐公司", musics1);
        albums.add(music1.map);
        Album music2 = new Album(00002, "你的名字原声带", "索尼音乐", musics1);
        albums.add(music2.map);
        Album music3 = new Album(00003, "A Z aLIEz", "索尼音乐", musics1);
        albums.add(music3.map);
        Album music4 = new Album(00004, "ninelie", "索尼音乐", musics1);
        albums.add(music4.map);
    }

    public static ArrayList<Map<String, Object>> getMusics(){
        return albums;
    }
}
