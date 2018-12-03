package com.example.corne.journal;

import java.io.Serializable;
import java.util.Date;

public class JournalEntry implements Serializable {
    private int id;
    private String title;
    private String content;
    private String mood;
    private String timestamp;

    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public String getTimestamp(){
        return timestamp;
    }
}
