package com.example.corne.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import java.io.Serializable;


import static android.os.Build.ID;

public class EntryDatabase extends SQLiteOpenHelper implements Serializable {

    private static EntryDatabase instance;
    public SQLiteDatabase db;

    private EntryDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table journalEntries ( _id INTEGER PRIMARY KEY AUTOINCREMENT, title String," +
                "content String, mood String, timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP)");

//        Create test entry1
        ContentValues test = new ContentValues();
        test.put("title", "inleiding");
        test.put("content", "dit is een test voor de app journal");
        test.put("mood", "happy");
        db.insert("journalEntries", null, test);

//        Create test entry2
        ContentValues test1 = new ContentValues();
        test1.put("title", "test1");
        test1.put("content", "Dit is een andere test voor de app journal");
        test1.put("mood", "sad");
        db.insert("journalEntries", null, test1);

        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + "journalEntries");
        onCreate(db);
    }

    public static EntryDatabase getInstance (Context context) {
        if (instance != null) {
            return instance;
        } else {
            instance = new EntryDatabase(context, "journalEntries", null, 1);
            return instance;
        }
    }

    public Cursor selectAll(){
        db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM journalEntries", null);
        return cursor;
    }

    public void insert(JournalEntry entry){
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM journalEntries", null);
        ContentValues values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("content", entry.getContent());
        values.put("mood", entry.getMood());
        db.insert("journalEntries", null, values);
    }

    public void delete (long id){
        String idString = Long.toString(id);
        SQLiteDatabase db = getWritableDatabase();
        db.delete("journalEntries","_id = ?", new String[]{idString});
    }
}
