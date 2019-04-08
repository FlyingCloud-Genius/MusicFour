package com.cuhksz.musicfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        int musicID = (int) getIntent().getExtras().get(MUSICID);
        for(Map<String, Object> map:Music.getMusics()){
            if ((int)map.get("musicID") == musicID){
                music = map;
            }
        }

        ImageView photo = (ImageView) findViewById(R.id.musicInfoPhoto);
        TextView name = (TextView) findViewById(R.id.musicInfoName);
        TextView introduction = (TextView) findViewById(R.id.musicInfoIntroduction);

        photo.setImageResource((int)music.get("imageID"));
        name.setText((String)music.get("music"));
    }
}
