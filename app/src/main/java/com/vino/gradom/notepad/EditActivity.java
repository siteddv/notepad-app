package com.vino.gradom.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vino.gradom.notepad.db.MySqlManager;

public class EditActivity extends Activity {

    private MySqlManager sqlManager;
    private EditText edTitle;
    private EditText edDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
    }

    private void init(){
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
        if(title.equals("") || description.equals("")){
            Toast.makeText(this, R.string.incorrect_title_or_description, Toast.LENGTH_LONG).show();
            return;
        }
        sqlManager.insertToDb(title, description);
        Toast.makeText(this, R.string.successful_adding, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlManager.closeDb();
    }
}