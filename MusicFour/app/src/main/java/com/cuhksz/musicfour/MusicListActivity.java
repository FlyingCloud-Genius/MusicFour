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
    private static final String MUSICLISTID = "musicListID";
    private ArrayList<Map<String, Object>> targetMusics = new ArrayList<>();
    private String userID;
    private String musicListID;
    private ArrayList<String> musicsID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);

        userID = (String) getIntent().getExtras().get(USERID);
        musicsID = getIntent().getStringArrayListExtra("musicsID");
        musicListID = (String) getIntent().getExtras().get(MUSICLISTID);

        for (String s:musicsID){
            Log.i("4001: musicsID: ", s);
        }

        Music.buildMusics();

        for (Map<String,Object> music:Music.getMusics()){
            String id = (String) music.get("musicID");
            for (String i:musicsID){
                if (id.equals(i)){
                    targetMusics.add(music);
                    break;
                }
            }
        }

        SimpleAdapter adapter = new SimpleAdapter(this, targetMusics, R.layout.music_list_item,
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
                intent.putExtra(MUSICLISTID, musicListID);
                intent.putExtra(MUSICID, targetMusics.get(i).get("musicID").toString());
                System.out.println(targetMusics.get(i).get("musicID"));
                startActivity(intent);
                return true;
            }
        });
    }
}
