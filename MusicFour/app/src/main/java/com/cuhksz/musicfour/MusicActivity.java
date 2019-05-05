package com.cuhksz.musicfour;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MusicActivity extends Activity {
    MyServiceConn conn;
    Intent intent;
    MusicInterface mi;

    private static SeekBar seekbar;
    private static TextView currentTime;
    private static TextView totalTime;
    private static Button play;
    private static Button pauseplay;
    private static Button module;
    public static final String musicPath = "/mnt/sdcard/Music/";
    public static final String photoPath = "/mnt/sdcard/Pictures/";
    public static List<String> musics;// get the play music list
    public static List<String> playList;
    public static List<String> musicIDs;
    public static List<String> localMusics;
    public static int currentSongIndex = 0;
    public String playingModule; // next same random

    private static final String USERID = "userID";
    private String userID;
    private String musicID;
    private String musicListID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        //asking for authority
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_CONTACT = 101;
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            //check the permission
            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    //apllying for permission
                    ActivityCompat.requestPermissions(this ,permissions, 127);
                }
            }
        }

        userID = (String) getIntent().getExtras().get(USERID);
        musicID = (String) getIntent().getExtras().get("musicID");
        musicListID = (String) getIntent().getExtras().get("musicListID");
        musics = getMusicNames(musicListID);
        musicIDs = getMusicIDs(musicListID);
        localMusics = GetFiles.getAllFileName(musicPath,"mp3");
        for (int i = 0; i < localMusics.size(); i++) {
            localMusics.set(i, localMusics.get(i).substring(0, localMusics.get(i).indexOf(".")));
        }
        for (int i = 0; i < musicIDs.size(); i++) {
            if (!localMusics.contains(musics.get(i))) {
                Download download = new Download();
                download.downloadMp3(musicIDs.get(i), musicPath, photoPath);
            }
        }
        playList = new ArrayList<>();
        for (String i : musics) {
            playList.add(musicPath + i + ".mp3");
        }

        currentTime = (TextView) findViewById(R.id.current_time);
        totalTime = (TextView) findViewById(R.id.total_time);
        play = (Button) findViewById(R.id.start);
        pauseplay = (Button) findViewById(R.id.pauseplay);
        module = (Button) findViewById(R.id.module);
        MyOnClickListener listener =  new MyOnClickListener();
        play.setOnClickListener(listener);
        pauseplay.setOnClickListener(listener);
        module.setOnClickListener(listener);
        module.setText("next");
        playingModule = "next";

        //intent object
        intent = new Intent(this, MusicService.class);

        startService(intent);

        conn = new MyServiceConn();

        bindService(intent, conn, BIND_AUTO_CREATE);

        seekbar = (SeekBar) findViewById(R.id.sb);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();

                mi.seekTo(progress);
            }
        });


    }

    public static Handler handler = new Handler() {
        public void handleMessage(Message message) {
            Bundle bundle = message.getData();
            int duration = bundle.getInt("duration");
            int currentPosition = bundle.getInt("currentPosition");


            seekbar.setMax(duration);
            seekbar.setProgress(currentPosition);

            int minute = duration / 1000 / 60;
            int second = duration / 1000 % 60;

            String strMinute = null;
            String strSecond = null;

            if (minute < 10) strMinute = "0" + minute;
            else strMinute = Integer.toString(minute);

            if (second < 10) strSecond = "0" + second;
            else strSecond = Integer.toString(second);

            totalTime.setText(strMinute + ":" + strSecond);

            minute = currentPosition / 1000 / 60;
            second = currentPosition / 1000 % 60;

            if (minute < 10) strMinute = "0" + minute;
            else strMinute = Integer.toString(minute);

            if (second < 10) strSecond = "0" + second;
            else strSecond = Integer.toString(second);

            currentTime.setText(strMinute + ":" + strSecond);
        }
    };


    private class MyOnClickListener implements View.OnClickListener {
        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.pauseplay:
                    pausePlay();
                    break;
                case R.id.start:
                    mi.play(playList.get(currentSongIndex), playingModule);
                    break;
                case R.id.module:
                    if (module.getText() == "next") {
                        module.setText("random");
                        playingModule = "random";
                    } else if (module.getText() == "random") {
                        module.setText("same");
                        playingModule = "same";
                    } else {
                        module.setText("next");
                        playingModule = "next";

                    }
            }
        }
    }

    public void pausePlay() {
        mi.pausePlay();
    }

    public void previousSong(View view) {
        if (playingModule == "random") {
            int previousSongIndex = currentSongIndex;
            randomIndex();
            while (currentSongIndex == previousSongIndex) {
                randomIndex();
            }
        } else {
            currentSongIndex -= 1;
            if (currentSongIndex == -1) {
                currentSongIndex = playList.size() - 1;
            }
        }
        mi.stop();
        mi.play(playList.get(currentSongIndex), playingModule);
    }

    public void nextSong(View view) {
        if (playingModule == "next") {
            nextIndex();
        }else if (playingModule == "same") {
            nextIndex();
        }else {
            int previousSongIndex = currentSongIndex;
            randomIndex();
            while (currentSongIndex == previousSongIndex) {
                randomIndex();
            }
        }
        mi.stop();
        mi.play(playList.get(currentSongIndex), playingModule);
    }

    //get the next playing song index
    private void nextIndex() {
        currentSongIndex += 1;
        if (currentSongIndex == playList.size()) {
            currentSongIndex = 0;
        }
    }

    //get the index of the next playing song index
    public void randomIndex() {
        currentSongIndex = new Random().nextInt(playList.size());
    }

    public void exit(View view) {
        this.moveTaskToBack(true);
    }

    public void onDestroy() {
        super.onDestroy();
        unbindService(conn);
    }

    class MyServiceConn implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //get median object
            mi = (MusicInterface) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name){

        }
    }

    public static String currentSong() {
        return playList.get(currentSongIndex);
    }

//    public void onClickToMusicList(View view) {
//        Intent intentToMusicList = new Intent(MusicActivity.this, MusicListActivity.class);
//        ConnectMySql dataBase = new ConnectMySql();
//        List<SpecialMusicList> tmpList = dataBase.getWholeMusicSheetList(userID);
//        for (int i=0; i<tmpList.size(); i++){
//            if (tmpList.get(i).getMusicSheetID().equals(musicListID)){
//                Log.i("4001: iteratorID: ",tmpList.get(i).getMusicSheetID());
//                intent.putStringArrayListExtra("musicsID", tmpList.get(i).getMusicInclude());
//                break;
//            }
//        }
//        intentToMusicList.putExtra("musicID",musicID);
//        intentToMusicList.putExtra(USERID,userID);
//        startActivity(intentToMusicList);
//    }

    public SpecialMusicList getMusicList(String musicListID){
        ConnectMySql dataBase = new ConnectMySql();
        List<SpecialMusicList> tmpList = dataBase.getWholeMusicSheetList(userID);
        for (SpecialMusicList i : tmpList) {
            if (i.getMusicSheetID().equals(musicListID)) {
                return i;
            }
        }
        return null;
    }

    public List<String> getMusicIDs (String listID) {
        SpecialMusicList musicList = getMusicList(listID);
        List<String> musicIDs = musicList.getMusicInclude();
        return musicIDs;
    }

    public List<String> getMusicNames(String listID) {
        ConnectMySql dataBase = new ConnectMySql();
        List<SpecialMusic> tmpList = dataBase.getWholeMusicList();
        List<String> IDs = new ArrayList<>();
        List<String> names = new ArrayList<>();
        for (SpecialMusic i : tmpList) {
            IDs.add(i.getMusicID());
        }
        for (SpecialMusic i : tmpList) {
            names.add(i.getMusicName());
        }
        SpecialMusicList musicList = getMusicList(listID);
        List<String> musicIDs = musicList.getMusicInclude();
        List<String> musicNames = new ArrayList<>();
        for (int i = 0; i < IDs.size(); i++) {
            if (musicIDs.contains(IDs.get(i))) {
                musicNames.add(names.get(i));
            }
        }
        return musicNames;
    }

    public void onClickToComment(View view) {
        Intent intentToComment = new Intent(MusicActivity.this, MusicCommentActivity.class);
        intentToComment.putExtra("musicID",musicID);
        intentToComment.putExtra(USERID,userID);
        startActivity(intentToComment);
    }

    public void onClickToAlbum(View view) {
        Intent intentToAlbum = new Intent(MusicActivity.this, AlbumInfoActivity.class);
        intentToAlbum.putExtra("albumID","1");
        intentToAlbum.putExtra(USERID,userID);
        startActivity(intentToAlbum);
    }

}