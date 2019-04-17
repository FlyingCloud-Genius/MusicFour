package com.cuhksz.musicfour;


import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class MusicCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_comment);

        final CommentList commentList = new CommentList();

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.commentListView);
        final EditText commentToSend = (EditText) findViewById(R.id.commentToSend);
        Button send = (Button) findViewById(R.id.send);

        expandableListView.setGroupIndicator(null);

        final CommentAdapter adapter = new CommentAdapter(this, commentList);
        expandableListView.setAdapter(adapter);

        for (int i=0; i < commentList.size(); i++){
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
                    Comment comment = new Comment("英国佬", comment_to_send, null);
                    commentList.addComment(comment);
                    Toast.makeText(MusicCommentActivity.this,"发送成功", Toast.LENGTH_SHORT).show();
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MusicCommentActivity.this,"评论内容为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}