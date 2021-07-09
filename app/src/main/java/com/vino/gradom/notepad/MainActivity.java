package com.vino.gradom.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vino.gradom.notepad.adapter.NoteAdapter;
import com.vino.gradom.notepad.db.MyConstants;
import com.vino.gradom.notepad.db.MySqlManager;

public class MainActivity extends Activity {

    private MySqlManager sqlManager;
    private EditText edTitle;
    private EditText edDescription;
    private RecyclerView noteListView;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        sqlManager = new MySqlManager(this);
        edTitle = findViewById(R.id.etTitle);
        edDescription = findViewById(R.id.etDescription);
        noteListView = findViewById(R.id.noteListView);
        noteAdapter = new NoteAdapter(this);
        noteListView.setLayoutManager(new LinearLayoutManager(this));
        noteListView.setAdapter(noteAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sqlManager.openDb();
        noteAdapter.updateAdapter(sqlManager.getNotesFromDb());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlManager.closeDb();
    }

    public void onClickAdd(View view) {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        startActivity(intent);
    }
}