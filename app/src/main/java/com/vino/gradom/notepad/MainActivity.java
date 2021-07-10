package com.vino.gradom.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vino.gradom.notepad.adapter.NoteAdapter;
import com.vino.gradom.notepad.db.MyConstants;
import com.vino.gradom.notepad.db.MySqlManager;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout imageLayout;
    private ImageView articleImage;
    private FloatingActionButton editImage, deleteImage;
    private MySqlManager sqlManager;
    private EditText edTitle, edDescription;
    private RecyclerView noteListView;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
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