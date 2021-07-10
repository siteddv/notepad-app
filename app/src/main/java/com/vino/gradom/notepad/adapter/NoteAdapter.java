package com.vino.gradom.notepad.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vino.gradom.notepad.EditActivity;
import com.vino.gradom.notepad.R;
import com.vino.gradom.notepad.db.MyConstants;
import com.vino.gradom.notepad.model.Note;
import com.vino.gradom.notepad.viewholder.NoteViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder> {

    private Context context;
    private ArrayList<Note> noteList;

    public NoteAdapter(Context context) {
        this.context = context;
        noteList = new ArrayList<>();
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_card_view, parent, false);
        NoteViewHolder viewHolder = new NoteViewHolder(view, context, noteList);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.setData(noteList.get(position));
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public void updateAdapter(LinkedList<Note> newNoteList){
        noteList.clear();
        noteList.addAll(newNoteList);
        notifyDataSetChanged();
    }
}
