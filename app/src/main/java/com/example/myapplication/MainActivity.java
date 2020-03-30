package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.database.DBHandler;

public class MainActivity extends AppCompatActivity {

        public static final String USER_ADDED = "User added successfully";
        public static final String USER_NOT_ADDED = "User can't be added";
        public static final String USER_FOUND = "Username & Password are correct";
        public static final String USER_NOT_FOUND = "User is not existing";
        public static final String USER_UPDATED = "User is updated successfully";
        public static final String USER_NOT_UPDATED = "User is not successfully updated";
        public static final String USER_DELETED = "User is deleted successfully";
        public static final String USER_NOT_DELETED = "User is not successfully deleted";



        EditText username, password;
        Button add, selectAll, signIn, delete, update;
        DBHandler userDb;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            userDb = new DBHandler(this);
            userDb.getReadableDatabase();

            username = findViewById(R.id.inputName);
            password = findViewById(R.id.inputPw);


            add = findViewById(R.id.button2);
            selectAll = findViewById(R.id.button);
            signIn = findViewById(R.id.button3);
            delete = findViewById(R.id.button5);
            update = findViewById(R.id.button4);

        }

        @Override
        protected void onResume() {
            super.onResume();

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long status = userDb.addInfo(username.getText().toString(), password.getText().toString());
                    if (status > 0) {
                        Toast.makeText(MainActivity.this, USER_ADDED, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, USER_NOT_ADDED, Toast.LENGTH_SHORT).show();
                    }


                }
            });

            selectAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    for (Object name : userDb.ReadAllInfo()) {

                        String value = (String) name;
                        Log.i("Username", value);
                    }
                }
            });

            signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (userDb.readInfo(username.getText().toString(), password.getText().toString())) {
                        Toast.makeText(MainActivity.this, USER_FOUND, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, USER_NOT_FOUND, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userDb.updateInfo(username.getText().toString(), password.getText().toString())) {
                        Toast.makeText(MainActivity.this, USER_UPDATED, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, USER_NOT_UPDATED, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userDb.deleteInfo(username.getText().toString())) {
                        Toast.makeText(MainActivity.this, USER_DELETED, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, USER_NOT_DELETED, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

