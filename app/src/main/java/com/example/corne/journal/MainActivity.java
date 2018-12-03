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
        db = EntryDatabase.getInstance(getApplicationContext());
        adapter = new EntryAdapter(MainActivity.this, db.selectAll());
        ListView entryList = findViewById(R.id.entriesList);
        entryList.setAdapter(adapter);

        entryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) parent.getItemAtPosition(position);

                String title = cursor.getString(cursor.getColumnIndex("title"));
                String content = cursor.getString(cursor.getColumnIndex("content"));
                String mood = cursor.getString(cursor.getColumnIndex("mood"));
                String timestamp = cursor.getString(cursor.getColumnIndex("timestamp"));
                JournalEntry entry = new JournalEntry(title, content, mood);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("timestamp", timestamp);
                intent.putExtra("clickedEntry", entry);
                startActivity(intent);
            }
        });
        entryList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db = EntryDatabase.getInstance(getApplicationContext());
                int i = position;
                Cursor clickedJournal = (Cursor) parent.getItemAtPosition(i);
                int ID_index = clickedJournal.getColumnIndex("_id");
                long journal_ID = clickedJournal.getInt(ID_index);
                db.delete(journal_ID);
                updateData(db);
                return true;
            }
        });

    }
    @Override
    public void onResume() {
        super.onResume();
        updateData(db);
    }

    private void updateData(EntryDatabase db){
        adapter.swapCursor(db.selectAll());
    }

    public void floatingButtonClick(View view) {
        Intent intent  = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }


    }
