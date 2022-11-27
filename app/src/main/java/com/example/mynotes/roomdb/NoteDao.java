package com.example.mynotes.roomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     void insertNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM note_table")
     void deleteAllNotes();

    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getAllNotes();


     }
