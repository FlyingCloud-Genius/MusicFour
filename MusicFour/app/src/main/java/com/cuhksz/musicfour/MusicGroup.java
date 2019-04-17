package com.cuhksz.musicfour;

import java.util.ArrayList;

public class MusicGroup {
    private ArrayList<String> groupNames = new ArrayList<>();
    private ArrayList<MusicList> musicLists;

    public MusicGroup(ArrayList<String> groupNames, ArrayList<MusicList> musicLists) {
        for (int i = 0; i < groupNames.size(); i++) {
            String groupName = groupNames.remove(i);
            groupNames.add(i, groupName);
            this.groupNames.add(i, groupName + "("+ Integer.toString(musicLists.get(i).getList().size()) + ")");;
        }
        this.musicLists = musicLists;
    }

    public ArrayList<String> getGroupNames() {
        return groupNames;
    }

    public ArrayList<MusicList> getMusicLists() {
        return musicLists;
    }
}
