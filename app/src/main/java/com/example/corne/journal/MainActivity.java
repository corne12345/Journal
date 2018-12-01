package com.example.corne.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.corne.journal.InputActivity;
import com.example.corne.journal.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void floatingButtonClick(View view) {
        Intent intent  = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }


    }
