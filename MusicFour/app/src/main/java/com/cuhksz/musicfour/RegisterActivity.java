package com.cuhksz.musicfour;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onClickRegister(View view){
        EditText inputEmail = (EditText) findViewById(R.id.inputEmail);
        EditText inputPassword1 = (EditText) findViewById(R.id.inputPassword1);
        EditText inputPassword2 = (EditText) findViewById(R.id.inputPassword2);
        EditText inputName = (EditText) findViewById(R.id.inputName);
        Spinner inputGender = (Spinner) findViewById(R.id.inputGender);
        EditText inputBirthday = (EditText) findViewById(R.id.inputBirthday);


        String email = inputEmail.getText().toString();
        String password1 = inputPassword1.getText().toString();
        String password2 = inputPassword2.getText().toString();
        String name = inputName.getText().toString();
        String gender = inputGender.getSelectedItem().toString();
        String birthday = inputBirthday.getText().toString();

        if (!password1.equals(password2)){
            Toast.makeText(RegisterActivity.this,"passwords are not the same", Toast.LENGTH_SHORT).show();
        }else {
            Log.i("4001: ",name);
            Log.i("4001: ",gender);
            Log.i("4001: ",birthday);
        }
    }


}