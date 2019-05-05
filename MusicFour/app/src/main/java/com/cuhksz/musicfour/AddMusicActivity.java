package com.cuhksz.musicfour;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddMusicActivity extends ListActivity {
    private static final String MUSICID = "musicID";
    private static Map<String, Object> music = new HashMap<String,Object>();
    private String musicID;
    private String musicListID;
    private String userID;
    private String[] targetList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        musicID = (String) getIntent().getExtras().get(MUSICID);
        musicListID = (String) getIntent().getExtras().get("musicListID");
        userID = (String) getIntent().getExtras().get("userID");


        ConnectMySql dataBase = new ConnectMySql();
        List<SpecialMusicList> tmpList = dataBase.getWholeMusicSheetList(userID);
        targetList = new String[tmpList.size()];
        for (int i=0; i<tmpList.size(); i++){
            targetList[i] = tmpList.get(i).getMusicSheetName();
        }

        ArrayAdapter<String> musicOperationAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                targetList
        );

        ListView musicOperationList = getListView();
        musicOperationList.setAdapter(musicOperationAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        ConnectMySql dataBase = new ConnectMySql();
        Log.i("4001: end: ", musicID);
        dataBase.insertMusic(targetList[position], musicID);
        Intent intent = new Intent(AddMusicActivity.this, MainActivity.class);
        intent.putExtra("userID",userID);
        startActivity(intent);
    }
}
