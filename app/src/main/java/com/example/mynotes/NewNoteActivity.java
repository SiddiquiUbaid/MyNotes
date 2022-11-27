package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.mynotes.roomdb.Note;

import java.util.ArrayList;

public class NewNoteActivity extends AppCompatActivity {



    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    private EditText mEditTitle, mEditeContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);


        mEditTitle = findViewById(R.id.edit_title);
        mEditeContent = findViewById(R.id.edit_content);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditTitle.getText()) && TextUtils.isEmpty(mEditeContent.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                Note note = new Note(mEditTitle.getText().toString(), mEditeContent.getText().toString());

                replyIntent.putExtra(TITLE, note.getTitle());
                replyIntent.putExtra(CONTENT, note.getContent());
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });



    }
}