package com.cuhksz.musicfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.*;

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

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
        String userID = "U" + dateFormat.format(date);

        if (!password1.equals(password2)){
            Toast.makeText(RegisterActivity.this,"passwords are not the same", Toast.LENGTH_SHORT).show();
        }else if (!Pattern.matches("[0-9]{4}\\-[0-9]{2}\\-[0-9]{2}", birthday)){
            Toast.makeText(RegisterActivity.this, "invalid birthday style", Toast.LENGTH_SHORT).show();
        }else if (email.equals("") || name.equals("")) {
            Toast.makeText(RegisterActivity.this, "you should fill in all the blanks", Toast.LENGTH_SHORT).show();
        }else{
            ConnectMySql connector = new ConnectMySql();
            connector.insertRegistry(email, password1, name, gender, birthday, userID);
            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
            intent.putExtra("userID",email);
            startActivity(intent);
        }
    }


}
