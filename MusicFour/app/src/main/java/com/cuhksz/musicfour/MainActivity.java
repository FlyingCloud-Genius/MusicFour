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
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
//    private ListView static_option;
    private static ExpandableListView favorite;
    private static ConstraintLayout bottom;
    private static DrawerLayout drawerLayout;
    private static NavigationView navigationView;
    private String userID;
    private static Button addGroup;
    ArrayList<MusicList> total;

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
                    intent.putExtra("UserID", userID);
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

        addGroup = (Button) findViewById(R.id.addGroup);
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
//        static_option = (ListView) findViewById(R.id.static_options);
//        MusicList ml = new MusicList();
//        ml.putItem("Local Music", 650);
//        ml.putItem("Manage Download", 590);
//        ml.putItem("Favorite Stations", 5);
//        ml.putItem("Recent Play", 100);
//        ml.putItem("FAVs", 4);
//        SimpleAdapter SOadapter = new SimpleAdapter(this, ml.getList(), R.layout.music_list,
//                new String[]{"List Name", "Song Number"},
//                new int[]{R.id.listName, R.id.songNumber});
//        static_option.setAdapter(SOadapter);
//        static_option.setOnItemClickListener(new ListView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(MainActivity.this, MusicListActivity.class);
//                intent.putExtra("userID", userID);
//                //musicList ID
//                //TODO
//                //intent.putExtra("musicList", );
//                startActivity(intent);
//            }
//        });

        
        //set items in the favorite lsit
        favorite = (ExpandableListView) findViewById(R.id.favorite);
        ConnectMySql dataBase = new ConnectMySql();
        List<SpecialMusicList> tmpList = new ArrayList<>();
        tmpList = dataBase.getWholeMusicSheetList(userID);

        MusicList myList = new MusicList();
        for(SpecialMusicList tmp:tmpList) {
            myList.putItem(tmp.getMusicSheetName(), tmp.getCountMusicNum());
        }

        ArrayList<String> groupName = new ArrayList<>();
        groupName.add("Group: My Creation");

        ArrayList<MusicList> total = new ArrayList<>();
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
                ConnectMySql dataBase = new ConnectMySql();
                List<SpecialMusicList> tmpList = dataBase.getWholeMusicSheetList(userID);
                intent.putExtra("userID", userID);
                intent.putExtra("musicListID", tmpList.get(i1).getMusicSheetID());
                intent.putStringArrayListExtra("musicsID", tmpList.get(i1).getMusicInclude());
                startActivity(intent);
                return true;
            }
        });

        favorite.setOnItemLongClickListener(new ExpandableListView.OnItemLongClickListener(){

            /**
             * Callback method to be invoked when an item in this view has been
             * clicked and held.
             * <p>
             * Implementers can call getItemAtPosition(position) if they need to access
             * the data associated with the selected item.
             *
             * @param parent   The AbsListView where the click happened
             * @param view     The view within the AbsListView that was clicked
             * @param position The position of the view in the list
             * @param id       The row id of the item that was clicked
             * @return true if the callback consumed the long click, false otherwise
             */
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ListOperationActivity.class);
                intent.putExtra("userID", userID);
                ConnectMySql dataBase = new ConnectMySql();
                List<SpecialMusicList> tmpList = dataBase.getWholeMusicSheetList(userID);
                intent.putExtra("userID", userID);
                intent.putExtra("musicListID", tmpList.get(position-1).getMusicSheetID());
                intent.putStringArrayListExtra("musicsID", tmpList.get(position-1).getMusicInclude());
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
                ConnectMySql dataBase = new ConnectMySql();
                List<SpecialMusicList> tmpList = dataBase.getWholeMusicSheetList(userID);
                for (int i=0; i<tmpList.size(); i++){
                    if(!tmpList.get(i).equals(null)){
                        for (int j=0; j<tmpList.get(i).getMusicInclude().size(); j++){
                            if (!tmpList.get(i).getMusicInclude().get(j).equals(null)){
                                Log.i("4001: sheetID: ",tmpList.get(i).getMusicSheetID());
                                intent.putExtra("musicListID", tmpList.get(i).getMusicSheetID());
                                intent.putExtra("musicID", tmpList.get(i).getMusicInclude().get(j));
                                break;
                            }
                        }
                        break;
                    }
                }
                intent.putExtra("userID", userID);
                startActivity(intent);
            }
        });
    }

    public void addList(View view) {
        Intent intent = new Intent(MainActivity.this, popupActivity.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
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
