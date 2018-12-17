package com.example.corne.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Calendar;

public class InputActivity extends AppCompatActivity {

    private static final String TAG = "Input Activity";
    private Button mConfirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        // Set onclicklistener for confirmbutton
        mConfirmButton = (Button) findViewById(R.id.confirmationButton);
        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve all input information into strings
                TextView title = findViewById(R.id.editTitle);
                String titleString = title.getText().toString();

                TextView content = findViewById(R.id.editText);
                String contentString = content.getText().toString();

                RadioGroup mood = findViewById(R.id.radioGroup);
                int moodInt = mood.getCheckedRadioButtonId();
                RadioButton checkedMood = (RadioButton) findViewById(moodInt);
                String moodString = (String) checkedMood.getText().toString();

                // Create journal entry with the retrieved information and insert in database
                JournalEntry entry = new JournalEntry(titleString, contentString, moodString);
                EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
                db.insert(entry);

                // Create intent to go to MainActivity
                Intent intent = new Intent(InputActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
