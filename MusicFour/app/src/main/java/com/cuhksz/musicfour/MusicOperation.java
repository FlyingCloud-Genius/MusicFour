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
    private String musicID;
    private String musicListID;
    private String userID;
    private String[] operationList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        musicID = (String) getIntent().getExtras().get(MUSICID);
        musicListID = (String) getIntent().getExtras().get("musicListID");
        userID = (String) getIntent().getExtras().get("userID");

        for(Map<String, Object> map:Music.getMusics()){
            if (map.get("musicID").equals(musicID)){
                music = map;
            }
        }

        if (musicListID != null){
            operationList = new String[]{"Like", "Comment", "Download", "Share", "Music information", "Add to music sheet", "Remove from music sheet"};
        }else{
            operationList = new String[]{"Like", "Comment", "Download", "Share", "Music information", "Add to music sheet"};
        }

        ArrayAdapter<String> musicOperationAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                operationList
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

        if (position == 3){
            Intent share_intent = new Intent();
            share_intent.setAction(Intent.ACTION_SEND);
            share_intent.setType("text/plain");
            share_intent.putExtra(Intent.EXTRA_SUBJECT, "好歌推荐： ");
            share_intent.putExtra(Intent.EXTRA_TEXT, (String)music.get("music"));
            share_intent = Intent.createChooser(share_intent, "分享");
            startActivity(share_intent);
        }    //go to the share interface

        if (position == 4){
            Intent info_intent = new Intent(MusicOperation.this,MusicInfoActivity.class);
            info_intent.putExtra(MUSICID,(String) music.get("musicID"));
            startActivity(info_intent);
        }    //go to the music information interface

        if (position == 5){
            Intent intent = new Intent(MusicOperation.this, AddMusicActivity.class);
            intent.putExtra(MUSICID,musicID);
            intent.putExtra("userID",userID);
            startActivity(intent);
        }   //add the music into music sheet

        if (position == 6){

        }  //remove the music from music sheet

    }
}
