package com.cuhksz.musicfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class AlbumInfoActivity extends AppCompatActivity {

    private static final String ALBUMID = "albumID";
    private static final String USERID = "userID";
    private static Map<String, Object> album = new HashMap<String,Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_info);

        Album.buildAlbums();
        String userID = (String) getIntent().getExtras().get(USERID);

        String albumID = (String) getIntent().getExtras().get(ALBUMID);
        for(Map<String, Object> map:Album.getAlbums()){
            if (map.get("albumID").equals(albumID)){
                album = map;
            }
            else{
                System.out.println("Don't equal!!");
            }
        }

//        ImageView photo = (ImageView) findViewById(R.id.albumInfoPhoto);
        TextView albumName = (TextView) findViewById(R.id.albumInfoAlbum);
        TextView publisher = (TextView) findViewById(R.id.albumInfoPublisher);
        TextView publishDate = (TextView) findViewById(R.id.albumInfoPublishDate);
        TextView musician = (TextView) findViewById(R.id.albumInfoMusician);

//        photo.setImageResource((int)album.get("imageID"));
        albumName.setText((String)album.get("album"));
        publisher.setText((String)album.get("publisher"));
        publishDate.setText((String)album.get("publishDate"));
        musician.setText((String)album.get("musician"));
    }
}
