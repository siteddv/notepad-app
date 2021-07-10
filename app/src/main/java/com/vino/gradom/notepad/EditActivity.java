package com.vino.gradom.notepad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vino.gradom.notepad.db.MySqlManager;

public class EditActivity extends AppCompatActivity {

    private final int PICK_IMAGE_CODE = 200;
    private String imageURI = "empty";

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
        sqlManager.insertToDb(title, description, imageURI);
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
            articleImage.setImageResource(android.R.color.transparent);
            articleImage.setImageURI(data.getData());
            imageURI = data.getData().toString();
            return;
        }
        Toast.makeText(this, "Selected image is invalid",Toast.LENGTH_LONG).show();

    }

    public void onClickCloseImage(View view) {
        handleImageLayout();
    }

    public void onClickDeleteImage(View view) {
        imageURI = "empty";
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