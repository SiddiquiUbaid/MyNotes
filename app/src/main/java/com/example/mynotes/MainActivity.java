package com.example.mynotes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mynotes.roomdb.Note;
import com.example.mynotes.ui.NewNoteListAdapter;
import com.example.mynotes.ui.NoteListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewNoteListAdapter.OnDeleteListener {
    private NoteViewModel mNoteViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        //final NoteListAdapter adapter = new NoteListAdapter(new NoteListAdapter.WordDiff());
        NewNoteListAdapter adapter = new NewNoteListAdapter(this,  this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mNoteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);

        // Update the cached copy of the words in the adapter.


        // Create the observer which updates the UI.
        final Observer<List<Note>> listObserver = new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.updateList(notes);
            }


        };

        mNoteViewModel.getAllNotes().observe(this, listObserver);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });





    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String title = data.getStringExtra(NewNoteActivity.TITLE);
            String content = data.getStringExtra(NewNoteActivity.CONTENT);

            Note note = new Note(title, content);
            mNoteViewModel.insert(note);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onDeleteClick(Note note) {

        mNoteViewModel.delete(note);

    }

    public void submitData(View view){

    }

}