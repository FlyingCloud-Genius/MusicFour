package com.cuhksz.musicfour;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity{
    private ListView static_option;
    private ExpandableListView favorite;
    private ConstraintLayout bottom;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    homeOnSelect();
                    return true;
                case R.id.navigation_dashboard:

                    return true;
                case R.id.navigation_notifications:
                        notificationOnSelect();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //initial the home page


    }

    public void homeOnSelect() {
        //set item in the static options
        static_option = (ListView) findViewById(R.id.static_options);
        MusicList ml = new MusicList();
        ml.putItem("Local Music", 650);
        ml.putItem("Manage Download", 590);
        ml.putItem("Favorite Stations", 5);
        ml.putItem("Recent Play", 100);
        ml.putItem("FAVs", 4);
        SimpleAdapter SOadapter = new SimpleAdapter(this, ml.getList(), R.layout.music_list,
                new String[]{"List Name", "Song Number"},
                new int[]{R.id.listName, R.id.songNumber});
        static_option.setAdapter(SOadapter);

        
        //set items in the favorite lsit
        favorite = (ExpandableListView) findViewById(R.id.favorite);
        MusicList myList = new MusicList();
        myList.putItem("favorite", 123);
        myList.putItem("for homework", 55);
        myList.putItem("pure music", 100);

        MusicList mix = new MusicList();
        mix.putItem("westLife", 123);
        mix.putItem("100 for learning", 100);

        ArrayList<String> groupName = new ArrayList<>();
        groupName.add("my creation");
        groupName.add("Mixed");

        ArrayList<MusicList> total = new ArrayList<>();
        total.add(mix);
        total.add(myList);

        MusicGroup mg = new MusicGroup(groupName, total);

        favorite.setGroupIndicator(null);

        final MusicListAdapter MLadapter = new MusicListAdapter(this, mg);
        favorite.setAdapter(MLadapter);

        for (int i=0; i < mg.getGroupNames().size(); i++){
            favorite.expandGroup(i);
        }

        favorite.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);

                return false;
            }
        });

        favorite.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                return false;
            }
        });

        //set the music player at the bottom
        bottom = (ConstraintLayout) findViewById(R.id.bottom);
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                startActivity(intent);
            }
        });
    }

    public void notificationOnSelect() {
        Intent intent = new Intent(MainActivity.this, personalInfoActivity.class);
        startActivity(intent);
    }
}
