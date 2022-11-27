package com.example.mynotes.ui;



import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mynotes.R;
import com.example.mynotes.roomdb.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NewNoteListAdapter extends RecyclerView.Adapter<NewNoteListAdapter.ViewHolder> {
    Context context;
    ArrayList<Note> noteList = new ArrayList<>();



    private FirebaseFirestore mstore;
    String uid;
    FirebaseAuth fAuth;


    private OnDeleteListener mOnDeleteListener;

    public NewNoteListAdapter(Context applicationContext,
                           OnDeleteListener onDeleteListener ) {

        this.context=applicationContext;
        this.mOnDeleteListener = onDeleteListener;

    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull   ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.note_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view, mOnDeleteListener);

        viewHolder.noteItemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDeleteListener.onDeleteClick(noteList.get(viewHolder.getAdapterPosition()));
            }
        });


        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {


        Note current = noteList.get(position);
        holder.bind(current.getTitle(), current.getContent());

        /*
        holder.drmimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mOnDeleteListener.onDeleteClick(new Note());

            }
        });




        mstore=FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
        uid = fAuth.getUid();

         */







    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView noteItemTitle;
        private final TextView noteItemContent;
        private final ImageView noteItemDelete;
        OnDeleteListener onDeleteListener;

        public ViewHolder(@NonNull View itemView, OnDeleteListener mOnDeleteListener) {
            super(itemView);

            noteItemTitle = itemView.findViewById(R.id.tv_title);
            noteItemContent = itemView.findViewById(R.id.tv_content);
            noteItemDelete = itemView.findViewById(R.id.btn_delete);
        }


        public void bind(String title, String content) {
            noteItemTitle.setText(title);
            noteItemContent.setText(content);
        }

        @Override
        public void onClick(View view) {

        }

        /*
        @Override
        public void onClick(View view) {

            // invoking item click listener
//            onHotelListener.onWishListClick(getAdapterPosition());



        }

         */


    }


    public void updateList(List<Note> newList){
        noteList.clear();
        noteList.addAll(newList);
        notifyDataSetChanged();
    }


    public static class WordDiff extends DiffUtil.ItemCallback<Note> {

        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }




    /*
    public void removeItem(int position){
        dharamshalaList.remove(position);
        notifyItemRemoved(position);
        // notifyItemRangeChanged(position, WishListAdapter.this.dharamshalaList.size());
        notifyDataSetChanged();

    }

     */




    public interface OnDeleteListener{
        void onDeleteClick(Note note);
    }
}