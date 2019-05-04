package com.cuhksz.musicfour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Music {
    private Map<String, Object> map = new HashMap<String,Object>();
    private static final ArrayList<Map<String, Object>> musics= new ArrayList<Map<String, Object>>();

    private Music(String musicID, String music, String musician){
        this.map.put("musicID", musicID);
        this.map.put("music",music);
        this.map.put("musician", musician);
    }

    private Music(String musicID, String music, String musician, int imageID){
        this.map.put("musicID", musicID);
        this.map.put("music",music);
        this.map.put("musician", musician);
        this.map.put("imageID", imageID);
    }

    public static void buildMusics(){
        musics.clear();
        ConnectMySql dataBase = new ConnectMySql();
        List<SpecialMusic> tmpList = new ArrayList<>();
        tmpList = dataBase.getWholeMusicList();
        for(SpecialMusic tmp:tmpList) {
            Music music = new Music(tmp.getMusicID(), tmp.getMusicName(), tmp.getMusicianName());
            musics.add(music.map);
        }
    }

    public static ArrayList<Map<String, Object>> getMusics(){
        return musics;
    }
}
