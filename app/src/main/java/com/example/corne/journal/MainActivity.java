package com.example.corne.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.corne.journal.InputActivity;
import com.example.corne.journal.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load SQLite Database and create adapter to load listView
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(MainActivity.this, db.selectAll());
        ListView entryList = findViewById(R.id.entriesList);
        entryList.setAdapter(adapter);

        // Create OnItemClickListener for listview
        entryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Identify what entry is clicked and load its parameters to create the entry
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String mood = cursor.getString(cursor.getColumnIndex("mood"));
                String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                JournalEntry entry = new JournalEntry(title, content, mood);

                // Make an intent and give it the timestamp and content as extras
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("timestamp", timestamp);
                intent.putExtra("clickedEntry", entry);
                startActivity(intent);
            }
        });

        // Create listener for longclick
        entryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                // load database and find the item in the database
                db = EntryDatabase.getInstance(getApplicationContext());
                int i = position;
                Cursor clickedJournal = (Cursor) parent.getItemAtPosition(i);
                int ID_index = clickedJournal.getColumnIndex("_id");
                long journal_ID = clickedJournal.getInt(ID_index);

                // Delete the entry and update the database to show the result
                db.delete(journal_ID);
                updateData(db);
                return true;
            }
        });

    }
    @Override
    // Always update the database to immediately see newly added/removed items
    public void onResume() {
        super.onResume();
        updateData(db);
    }

    // Select the entire database, when updateData is selected
    private void updateData(EntryDatabase db){
        adapter.swapCursor(db.selectAll());
    }

    // Start InputActivity when FAB is clicked
    public void floatingButtonClick(View view) {
        Intent intent  = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }


    }
