package com.cuhksz.musicfour;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListInfoActivity extends AppCompatActivity {
    private List<String> tmpList = new ArrayList<>();
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

        ConnectMySql dataBase = new ConnectMySql();
        tmpList = dataBase.getMusicSheetInfo(userID, musicListID);
        listName.setText(tmpList.get(0));
        description.setText(tmpList.get(1));
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
