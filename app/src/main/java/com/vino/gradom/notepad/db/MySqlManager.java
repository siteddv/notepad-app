package com.vino.gradom.notepad.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.vino.gradom.notepad.model.Note;

import java.util.LinkedList;

public class MySqlManager {
    private MySqlHelper mySqlHelper;
    private Context context;
    private SQLiteDatabase db;

    public MySqlManager(Context context) {
        this.context = context;
        mySqlHelper = new MySqlHelper(context);
    }

    public void openDb(){
        db = mySqlHelper.getReadableDatabase();
    }

    public void insertToDb(String title, String description, String imageURI){
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TITLE, title);
        cv.put(MyConstants.DESCRIPTION, description);
        cv.put(MyConstants.IMAGE_URI, imageURI);
        db.insert(MyConstants.TABLE_NAME, null, cv);
    }

    public LinkedList<Note> getNotesFromDb(){
        LinkedList<Note> resultList = new LinkedList<Note>();
        Cursor cursor = db.query(
                MyConstants.TABLE_NAME,
                null, null,
                null, null,
                null, null);

        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(MyConstants.TITLE));
            String description = cursor.getString(cursor.getColumnIndex(MyConstants.DESCRIPTION));

            Note note = new Note();
            note.setTitle(title);
            note.setDescription(description);

            resultList.add(note);
        }

        cursor.close();
        return resultList;
    }

    public void closeDb(){
        mySqlHelper.close();
    }

}
