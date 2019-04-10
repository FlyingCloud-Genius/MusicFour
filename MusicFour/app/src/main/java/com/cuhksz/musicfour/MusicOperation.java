package com.cuhksz.musicfour;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Map;

public class MusicOperation extends ListActivity {
    private static final String MUSICID = "musicID";
    private static Map<String, Object> music = new HashMap<String,Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int musicID = (int) getIntent().getExtras().get(MUSICID);
        for(Map<String, Object> map:Music.getMusics()){
            if ((int)map.get("musicID") == musicID){
                music = map;
            }
        }


        ArrayAdapter<String> musicOperationAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new String[]{"收藏", "评论", "下载", "分享", "歌曲信息", "添加到歌单", "MV", "设为铃声"}
        );

        ListView musicOperationList = getListView();
        musicOperationList.setAdapter(musicOperationAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (position == 0){}    //mark the music as like

        if (position == 1){}    //go to the comment interface

        if (position == 2){}    //go to the download interface

        if (position == 3){}    //go to the share interface

        if (position == 4){
            Intent intent = new Intent(MusicOperation.this,MusicInfoActivity.class);
            intent.putExtra(MUSICID,(int)music.get("musicID"));
            startActivity(intent);
        }    //go to the music information interface

        if (position == 5){}    //add the music into music sheet

        if (position == 6){}    //show the MV of the music

        if (position == 7){}    //set the music as ringtone

    }
}