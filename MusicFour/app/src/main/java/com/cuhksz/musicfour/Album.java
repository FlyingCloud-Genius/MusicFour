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
        Music music1 = new Album(00001, "剑灵原声音乐选辑", "Fun.,Janelle Monae", R.drawable.music00001);
        albums.add(music1.map);
        Music music2 = new Album(00002, "你的名字原声带", "生物股长", R.drawable.music00002);
        albums.add(music2.map);
        Music music3 = new Album(00003, "A Z aLIEz", "小林未郁", R.drawable.music00003);
        albums.add(music3.map);
        Music music4 = new Album(00004, "ninelie", "Aimer, EGOIST", R.drawable.music00004);
        albums.add(music4.map);
        Music music5 = new Album(00005, "逆光", "陈芳雨、李紫婷、吴映香、刘德熙、陈语嫣", R.drawable.music00005);
        albums.add(music5.map);
        Music music6 = new Album(00006, "시련의 나날들","金致焕", R.drawable.music00006);
        albums.add(music6.map);
    }

    public static ArrayList<Map<String, Object>> getMusics(){
        return musics;
    }
}
