package com.cuhksz.musicfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MusicListActivity extends AppCompatActivity {
    private static final String MUSICID = "musicID";
    private static final String USERID = "userID";
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        userID = (String) getIntent().getExtras().get(USERID);

        Music.buildMusics();

        SimpleAdapter adapter = new SimpleAdapter(this, Music.getMusics(), R.layout.music_list_item,
                new String[]{"music", "musician"}, new int[]{R.id.musicListItemMusicName, R.id.musicListItemMusicianName});

        ListView musicList = (ListView)  findViewById(R.id.musicList);
        musicList.setAdapter(adapter);
        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MusicListActivity.this, MusicActivity.class);
                intent.putExtra(USERID, userID);
                intent.putExtra(MUSICID, (String) Music.getMusics().get(i).get("musicID"));
                startActivity(intent);
            }
        });

        musicList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MusicListActivity.this, MusicOperation.class);
                intent.putExtra(USERID, userID);
                intent.putExtra(MUSICID, Music.getMusics().get(i).get("musicID").toString());
                System.out.println(Music.getMusics().get(i).get("musicID"));
                startActivity(intent);
                return true;
            }
        });
    }




}
