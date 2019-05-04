package com.cuhksz.musicfour;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListOperation extends ListActivity {
    private String musicListID;
    private String userID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        musicListID = intent.getStringExtra("musicListID");
        userID = intent.getStringExtra("userID");
        ArrayAdapter<String> musicListOperationList = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new String[] {"歌单信息", "删除", "收藏", "播放"}
                );
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if (position == 0) {
            //show the music sheet information
            Intent intent = new Intent(this, ListInfoActivity.class);
            intent.putExtra("userID", userID);
            intent.putExtra("musicListID", musicListID);
            startActivity(intent);
        } else if (position == 1) {
            //delete the information from the database ad return to the origin interface
            //TODO

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("userID", userID);
            startActivity(intent);
        } else if (position == 2) {
//            //add this to the music sheets
//            //TODO
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.putExtra("userID", userID);
//            startActivity(intent);
        } else if (position == 3) {
            //start the listening to the list

        }
    }
}
