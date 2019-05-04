package com.cuhksz.musicfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MusicInfoActivity extends AppCompatActivity {

    private static final String MUSICID = "musicID";
    private static Map<String, Object> music = new HashMap<String,Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_info);

        String musicID = getIntent().getStringExtra(MUSICID);
        if (musicID == null) {
            Log.i("4001: ", "null ID");
        }
        for(Map<String, Object> map:Music.getMusics()){
            Log.i("4001: musicID",musicID);
            if (map.get("musicID").equals(musicID)){
                music = map;
                Log.i("4001: ","music is set");
            }
        }

        ImageView photo = (ImageView) findViewById(R.id.musicInfoPhoto);
        TextView musicName = (TextView) findViewById(R.id.musicInfoMusic);
        TextView musician = (TextView) findViewById(R.id.musicInfoMusician);

        photo.setImageResource((int)music.get("imageID"));
        musicName.setText((String)music.get("music"));
        musician.setText((String)music.get("musician"));
    }
}
