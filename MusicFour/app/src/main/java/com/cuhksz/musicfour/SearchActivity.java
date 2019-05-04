package com.cuhksz.musicfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

public class SearchActivity extends AppCompatActivity {
    private static final String MUSICID = "musicID";
    private static final String USERID = "userID";
    private String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        userID = (String) getIntent().getExtras().get(USERID);
        Music.buildMusics();

        SimpleAdapter adapter = new SimpleAdapter(this, Music.getMusics(), R.layout.music_list_item,
                new String[]{"music", "musician"}, new int[]{R.id.musicListItemMusicName, R.id.musicListItemMusicianName});

        final ListView searchResult = (ListView)  findViewById(R.id.searchResult);
        searchResult.setVisibility(View.INVISIBLE);
        searchResult.setAdapter(adapter);
        searchResult.setTextFilterEnabled(true);
        searchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchActivity.this, MusicActivity.class);
                intent.putExtra(USERID,userID);
                intent.putExtra(MUSICID, (String) Music.getMusics().get(i).get("musicID"));
                startActivity(intent);
            }
        });

        searchResult.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SearchActivity.this, MusicOperation.class);
                intent.putExtra(USERID,userID);
                intent.putExtra(MUSICID, (String) Music.getMusics().get(i).get("musicID"));
                startActivity(intent);
                return true;
            }
        });

        SearchView searchView = (SearchView) findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (!TextUtils.isEmpty(s)){
                    searchResult.setFilterText(s);
                    searchResult.setVisibility(View.VISIBLE);
                }else{
                    searchResult.clearTextFilter();
                    searchResult.setVisibility(View.INVISIBLE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (!TextUtils.isEmpty(s)){
                    searchResult.setFilterText(s);
                    searchResult.setVisibility(View.VISIBLE);
                }else{
                    searchResult.clearTextFilter();
                    searchResult.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
    }
}
