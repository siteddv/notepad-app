package com.vino.gradom.notepad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vino.gradom.notepad.db.MyConstants;
import com.vino.gradom.notepad.db.MySqlManager;
import com.vino.gradom.notepad.model.Note;

import java.io.File;
import java.net.URI;

public class EditActivity extends AppCompatActivity {

    private final int PICK_IMAGE_CODE = 200;
    private String imagePath = "empty";

    private ConstraintLayout imageLayout;
    private ImageView articleImage;
    private FloatingActionButton editImage, deleteImage;
    private MySqlManager sqlManager;
    private EditText edTitle, edDescription;

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
        imageLayout = findViewById(R.id.imageLayout);
        articleImage = findViewById(R.id.articleImage);
        getInfoFromIntent();
    }

    private void getInfoFromIntent(){
        Intent intent = getIntent();
        if(intent==null) {
            return;
        }

        Note note = (Note) intent.getSerializableExtra(MyConstants.NOTE_INTENT);

        if(note == null){
            return;
        }

        String title = note.getTitle();
        edTitle.setText(title);

        String description = note.getDescription();
        edDescription.setText(description);

        imagePath = note.getImageURI();
        if(!imagePath.equals("empty")){
            articleImage.setImageURI(Uri.parse(imagePath));
        }

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
        sqlManager.insertToDb(title, description, imagePath);
        Toast.makeText(this, R.string.successful_adding, Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sqlManager.closeDb();
    }

    @SuppressWarnings("deprecation")
    public void onClickChangeImage(View view) {
        Intent chooser = new Intent(Intent.ACTION_PICK);
        chooser.setType("image/*");
        startActivityForResult(chooser, PICK_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE_CODE && data != null){
            imagePath = data.getData().getPath();
            articleImage.setImageURI(data.getData());
            return;
        }
        Toast.makeText(this, "Selected image is invalid",Toast.LENGTH_LONG).show();

    }

    public void onClickCloseImage(View view) {
        handleImageLayout();
    }

    public void onClickDeleteImage(View view) {
        imagePath = "empty";
        articleImage.setImageResource(R.drawable.ic_image);
    }

    public void onClickSetImage(View view) {
        handleImageLayout();
    }

    private void handleImageLayout(){
        if(imageLayout.getVisibility() == View.VISIBLE){
            imageLayout.setVisibility(View.GONE);
        }else{
            imageLayout.setVisibility(View.VISIBLE);
        }
    }
}