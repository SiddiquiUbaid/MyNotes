package com.example.mynotes.roomdb;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "content")
    private String content;


    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Note() {
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(@NonNull  String content) {
        this.content = content;
    }
}
