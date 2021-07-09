package com.vino.gradom.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.vino.gradom.notepad.db.MySqlManager;

public class MainActivity extends Activity {

    private MySqlManager sqlManager;
    private EditText edTitle;
    private EditText edDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlManager = new MySqlManager(this);
        edTitle = findViewById(R.id.etTitle);
        edDescription = findViewById(R.id.etDescription);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sqlManager.openDb();
    }

    public void onSaveButton(View view) {
        String title = edTitle.getText().toString();
        String description = edDescription.getText().toString();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlManager.closeDb();
    }
}