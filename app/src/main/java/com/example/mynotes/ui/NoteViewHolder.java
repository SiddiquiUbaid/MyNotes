package com.example.mynotes.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.R;

class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView noteItemTitle;
    private final TextView noteItemContent;
    private final ImageView noteItemDelete;

    private NoteViewHolder(View itemView) {
        super(itemView);
        noteItemTitle = itemView.findViewById(R.id.tv_title);
        noteItemContent = itemView.findViewById(R.id.tv_content);
        noteItemDelete = itemView.findViewById(R.id.btn_delete);
    }




    public void bind(String title, String content) {
        noteItemTitle.setText(title);
        noteItemContent.setText(content);
    }

    static NoteViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteViewHolder(view);
    }



}