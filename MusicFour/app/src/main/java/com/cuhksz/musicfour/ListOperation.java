package com.cuhksz.musicfour;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;

public class ListOperation extends ListActivity {
    private static String listID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String musicListID = intent.getStringExtra("musicListID");

        ArrayAdapter<String> musicListOperationList = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                new String[] {"",}
                );
    }
}
