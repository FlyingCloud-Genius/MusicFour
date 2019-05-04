package com.cuhksz.musicfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class popupActivity extends AppCompatActivity {
    private static EditText listName;
    private static EditText description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        listName = (EditText) findViewById(R.id.listName);
        description = (EditText) findViewById(R.id.description);

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

        }
    }
}
