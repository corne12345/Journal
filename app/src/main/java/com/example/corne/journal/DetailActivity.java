package com.example.corne.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get intent and retrieve clicked entry
        Intent intent = getIntent();
        JournalEntry retrievedEntry = (JournalEntry) intent.getSerializableExtra("clickedEntry");
        String timestampString = (String) intent.getSerializableExtra("timestamp");

        // Connect clicked entry to all textviews, displaying the parameters
        TextView title = (TextView) findViewById(R.id.title);
        String titleString = retrievedEntry.getTitle();
        title.setText(titleString);

        TextView timestamp = (TextView) findViewById(R.id.timestamp);
        timestamp.setText(timestampString);

        TextView mood = (TextView) findViewById(R.id.mood);
        String moodString = retrievedEntry.getMood().toUpperCase();
        mood.setText(moodString);

        TextView content = (TextView) findViewById(R.id.content);
        String contentString = retrievedEntry.getContent();
        content.setText(contentString);

    }
}
