package com.cuhksz.musicfour;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ListInfoActivity extends AppCompatActivity {
    private static TextView listName;
    private static TextView description;
    private static ImageButton back;
    private String userID;
    private String musicListID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_info);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        musicListID = intent.getStringExtra("musicListID");

        back = (ImageButton) findViewById(R.id.back);
        listName = (TextView) findViewById(R.id.listName);
        description = (TextView) findViewById(R.id.listDescription);

        //show the info from the database based on the musiclistid and userid
        //TODO

    }


    private void backOnClick(View view) {
        Intent intent = new Intent(this, MusicListActivity.class);
        intent.putExtra("userID", userID);
        intent.putExtra("musicListID", musicListID);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, MusicListActivity.class);
        intent.putExtra("userID", userID);
        intent.putExtra("musicListID", musicListID);
        startActivity(intent);
    }
}
