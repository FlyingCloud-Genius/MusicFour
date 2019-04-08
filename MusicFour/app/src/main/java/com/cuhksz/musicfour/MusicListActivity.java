package com.cuhksz.musicfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;

public class MusicListActivity extends AppCompatActivity {
    private static final String MUSICID = "musicID";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        SimpleAdapter adapter = new SimpleAdapter(this, Music.getMusics(), R.layout.music_list_item,
                new String[]{"music", "musician"}, new int[]{R.id.musicListItemMusicName, R.id.musicListItemMusicianName});

        ListView musicList = (ListView)  findViewById(R.id.musicList);
        musicList.setAdapter(adapter);
        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MusicListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        musicList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MusicListActivity.this, musicOperation.class);
                intent.putExtra(MUSICID, (int)Music.getMusics().get(i).get("musicID"));
                startActivity(intent);
                return false;
            }
        });
    }




}
