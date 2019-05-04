package com.cuhksz.musicfour;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private ListView static_option;
    private ExpandableListView favorite;
    private ConstraintLayout bottom;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private String userID;

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
                case R.id.navigation_personalInfo:
                    notificationOnSelect();
                    return true;
                case R.id.navigation_search:
                    Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //initial the home page
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        homeOnSelect();

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
        static_option.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, MusicListActivity.class);
                intent.putExtra("userID", userID);
                //musicList ID
                //TODO
                //intent.putExtra("musicList", );
                startActivity(intent);
            }
        });

        
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
        groupName.add("Group: my creation");
        groupName.add("Group: Mixed");

        ArrayList<MusicList> total = new ArrayList<>();
        total.add(mix);
        total.add(myList);

        MusicGroup mg = new MusicGroup(groupName, total);

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
                Intent intent = new Intent(MainActivity.this, MusicListActivity.class);
                intent.putExtra("userID", userID);
                //musicList id
                //TODO
                //intent.putExtra("musicID",);
                startActivity(intent);
                return true;
            }
        });

        //set the music player at the bottom
        bottom = (ConstraintLayout) findViewById(R.id.bottom);
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                intent.putExtra("userID", userID);

                startActivity(intent);
            }
        });
    }

    public void notificationOnSelect() {
        drawerLayout.openDrawer(navigationView);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.personal_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
