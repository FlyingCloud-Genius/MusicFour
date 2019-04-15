package com.cuhksz.musicfour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mike on 2019/4/8.
 */

public class Music {
    private Map<String, Object> map = new HashMap<String,Object>();
    private static final ArrayList<Map<String, Object>> musics= new ArrayList<Map<String, Object>>();

    private Music(int musicID, String music, String musician){
        this.map.put("musicID", musicID);
        this.map.put("music",music);
        this.map.put("musician", musician);
    }

    private Music(int musicID, String music, String musician, int imageID){
        this.map.put("musicID", musicID);
        this.map.put("music",music);
        this.map.put("musician", musician);
        this.map.put("imageID", imageID);
    }

    public static ArrayList<Map<String,Object>> buildMusics(){
        Music music1 = new Music(00001, "We Are Young", "Fun.,Janelle Monae", R.drawable.music00001);
        musics.add(music1.map);
        Music music2 = new Music(00002, "青鸟", "生物股长", R.drawable.music00002);
        musics.add(music2.map);
        Music music3 = new Music(00003, "βίος", "小林未郁", R.drawable.music00003);
        musics.add(music3.map);
        Music music4 = new Music(00004, "ninelie", "Aimer, EGOIST", R.drawable.music00004);
        musics.add(music4.map);
        Music music5 = new Music(00005, "逆光", "陈芳雨、李紫婷、吴映香、刘德熙、陈语嫣", R.drawable.music00005);
        musics.add(music5.map);
        Music music6 = new Music(00006, "시련의 나날들","金致焕", R.drawable.music00006);
        musics.add(music6.map);
        return musics;
    }

    public static ArrayList<Map<String, Object>> getMusics(){
        return musics;
    }
}
