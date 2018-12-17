package com.example.corne.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import static com.example.corne.journal.R.*;

public class EntryAdapter extends ResourceCursorAdapter {
    EntryAdapter(Context context, Cursor cursor) {
        super(context, layout.entry_row, cursor);
    }

    @Override
    // create bindview to create the listview that is on screen
    public void bindView(View view, Context context, Cursor cursor) {
        TextView title = view.findViewById(id.title);
        title.setText(cursor.getString(cursor.getColumnIndex("title")));

        TextView mood = view.findViewById(id.Text_mood);
        String moodString = cursor.getString(cursor.getColumnIndex("mood")).toUpperCase();
        mood.setText(moodString);

        TextView date = view.findViewById(id.dateTime);
        date.setText(cursor.getString(cursor.getColumnIndex("timestamp")));

        int emoji = context.getResources().getIdentifier(moodString.toLowerCase(),"drawable", context.getPackageName());
        ImageView moodPic = view.findViewById(id.mood);
        moodPic.setImageResource(emoji);
    }



}
