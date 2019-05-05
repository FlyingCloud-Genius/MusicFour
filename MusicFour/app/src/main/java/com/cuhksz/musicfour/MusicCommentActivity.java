package com.cuhksz.musicfour;


import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MusicCommentActivity extends AppCompatActivity {
    private String userID;
    private static final String USERID = "userID";
    private String musicID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_comment);

        userID = (String)getIntent().getExtras().get(USERID);
        musicID = (String)getIntent().getExtras().get("musicID");

        final CommentList commentList = new CommentList();
        commentList.buildCommentList();

        CommentList musicCommentList = new CommentList();

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.commentListView);
        final EditText commentToSend = (EditText) findViewById(R.id.commentToSend);
        Button send = (Button) findViewById(R.id.send);

        expandableListView.setGroupIndicator(null);

        for (Comment cmt:commentList.getCommentList()){
            Log.i("4001:","in loops");
            System.out.println(musicID);
            System.out.println(cmt.getMusicID());
            if (cmt.getMusicID().equals(musicID)){
                Log.i("4001:",cmt.getComment());
                musicCommentList.addComment(cmt);
            }
        }


        final CommentAdapter adapter = new CommentAdapter(this, musicCommentList);
        expandableListView.setAdapter(adapter);

        for (int i=0; i < musicCommentList.size(); i++){
            expandableListView.expandGroup(i);
        }

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                BottomSheetDialog dialog = new BottomSheetDialog(MusicCommentActivity.this);

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                return false;
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String comment_to_send = (String) commentToSend.getText().toString();
                if (!TextUtils.isEmpty(comment_to_send)) {
                    Date present = new Date();
                    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
                    String commentID = "C" + dateFormat.format(present);
                    ConnectMySql dataBase = new ConnectMySql();
                    dataBase.insertComment(commentID, comment_to_send, userID, musicID, null);
                    Toast.makeText(MusicCommentActivity.this,"发送成功", Toast.LENGTH_SHORT).show();
                    commentToSend.getText().clear();
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MusicCommentActivity.this,"评论内容为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}