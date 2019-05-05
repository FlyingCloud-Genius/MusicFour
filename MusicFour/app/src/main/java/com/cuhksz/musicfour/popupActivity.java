package com.cuhksz.musicfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class popupActivity extends AppCompatActivity {
    private static EditText listName;
    private static EditText description;
    private static Button save;
    private static Button cancel;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        listName = (EditText) findViewById(R.id.listName);
        description = (EditText) findViewById(R.id.description);
        save = (Button) findViewById(R.id.save);
        cancel = (Button) findViewById(R.id.cancel);

        MyOnClickListener myOnClickListener = new MyOnClickListener();
        save.setOnClickListener(myOnClickListener);
        cancel.setOnClickListener(myOnClickListener);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class MyOnClickListener implements View.OnClickListener {

        /**
         * Called when a view has been clicked.
         *
         * @param v The view that was clicked.
         */
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.save:
                    Date present = new Date();
                    SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
                    String n = "MS" + dateFormat.format(present);
                    String name = listName.getText().toString();
                    String content = description.getText().toString();
                    ConnectMySql conn = new ConnectMySql();
                    conn.insertMusicSheet(n, name, content, userID);

                    Intent intent_save = new Intent(popupActivity.this, MainActivity.class);
                    intent_save.putExtra("userID", userID);
                    startActivity(intent_save);
                    break;
                case R.id.cancel:
                    Intent intent_cancel = new Intent(popupActivity.this, MainActivity.class);
                    intent_cancel.putExtra("userID", userID);
                    startActivity(intent_cancel);
                    break;
            }
        }
    }
}
