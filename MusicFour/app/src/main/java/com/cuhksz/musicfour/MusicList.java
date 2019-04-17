package com.cuhksz.musicfour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MusicList {
    private ArrayList<HashMap<String, String>> list;

    public MusicList() {
        list = new ArrayList<>();
    }

    public void putItem(String musicListName, int songNum) {
        HashMap<String, String> map = new HashMap<>();
        map.put("List Name", musicListName);
        map.put("Song Number", "(" + Integer.toString(songNum) + ")");
        list.add(map);

    }

    public ArrayList<HashMap<String, String>> getList() {
        return list;
    }
}
