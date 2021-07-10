package com.vino.gradom.notepad.viewholder;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vino.gradom.notepad.EditActivity;
import com.vino.gradom.notepad.R;
import com.vino.gradom.notepad.db.MyConstants;
import com.vino.gradom.notepad.model.Note;

import java.util.ArrayList;

public class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tvTitle;
    private TextView tvDescription;
    private Context context;
    private ArrayList<Note> noteList;

    public NoteViewHolder(@NonNull View itemView, Context context, ArrayList<Note> noteList) {
        super(itemView);
        this.context = context;
        this.noteList = noteList;
        init();
    }

    private void init(){
        tvTitle = itemView.findViewById(R.id.tvTitle_main);
        tvDescription = itemView.findViewById(R.id.tvDescription_main);
        itemView.setOnClickListener(this);
    }

    public void setData(Note note){
        tvTitle.setText(note.getTitle());
        tvDescription.setText(note.getDescription());
    }

    @Override
    public void onClick(View v) {
        int id = getAdapterPosition();
        Intent intent = new Intent(context, EditActivity.class);
        intent.putExtra(MyConstants.NOTE_INTENT, noteList.get(id));
        context.startActivity(intent);
    }
}