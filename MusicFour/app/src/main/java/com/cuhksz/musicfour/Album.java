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

    private Album(int albumID, String album, String publician){
        this.map.put("albumID", albumID);
        this.map.put("album",album);
        this.map.put("publician", publician);
    }

    private Album(int albumID, String album, String publician, int imageID){
        this.map.put("albumID", albumID);
        this.map.put("album",album);
        this.map.put("publician", publician);
        this.map.put("imageID", imageID);
    }

    public static void buildAlbum(){
        albums.clear();
        ArrayList<String> musics1 = new ArrayList<>();
        Album album1 = new Album(00001, "剑灵原声音乐选辑", "大韩音乐公司", R.drawable.music00001);
        albums.add(album1.map);
        Album album2 = new Album(00002, "你的名字原声带", "索尼音乐", R.drawable.music00001);
        albums.add(album2.map);
        Album album3 = new Album(00003, "A Z aLIEz", "索尼音乐", R.drawable.music00001);
        albums.add(album3.map);
        Album album4 = new Album(00004, "ninelie", "索尼音乐", R.drawable.music00001);
        albums.add(album4.map);
    }

    public static ArrayList<Map<String, Object>> getAlbums(){
        return albums;
    }
}
