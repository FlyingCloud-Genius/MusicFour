package com.cuhksz.musicfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class AlbumInfoActivity extends AppCompatActivity {

    private static final String ALBUMID = "albumID";
    private static Map<String, Object> album = new HashMap<String,Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_info);

        int albumID = (int) getIntent().getExtras().get(ALBUMID);
        for(Map<String, Object> map:Album.getAlbums()){
            if ((int)map.get("albumID") == albumID){
                album = map;
            }
        }

        ImageView photo = (ImageView) findViewById(R.id.albumInfoPhoto);
        TextView musicName = (TextView) findViewById(R.id.albumInfoMusic);
        TextView musician = (TextView) findViewById(R.id.albumInfoMusician);

        photo.setImageResource((int)album.get("imageID"));
        musicName.setText((String)album.get("music"));
        musician.setText((String)album.get("musician"));
    }
}