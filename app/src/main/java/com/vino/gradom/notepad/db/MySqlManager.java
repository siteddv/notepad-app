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

    public LinkedList<Note> getNotesFromDb(String searchText){
        LinkedList<Note> resultList = new LinkedList<Note>();
        String selection = MyConstants.TITLE + " LIKE ?";
        Cursor cursor = db.query(
                MyConstants.TABLE_NAME,
                null, selection,
                new String[]{"%"+searchText+"%"}, null,
                null, null);

        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(MyConstants.TITLE));
            String description = cursor.getString(cursor.getColumnIndex(MyConstants.DESCRIPTION));
            String imageURI = cursor.getString(cursor.getColumnIndex(MyConstants.IMAGE_URI));
            int id = cursor.getInt(cursor.getColumnIndex(MyConstants._ID));

            Note note = new Note();
            note.setTitle(title);
            note.setDescription(description);
            note.setImageURI(imageURI);
            note.setId(id);

            resultList.add(note);
        }

        cursor.close();
        return resultList;
    }

    public void deleteNoteById(int id){
        String selection = MyConstants._ID + "=" + id;
        db.delete(MyConstants.TABLE_NAME, selection, null);
    }

    public void updateNoteById(int id, String title, String description, String imageURI){
        String selection = MyConstants._ID + "=" + id;
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TITLE, title);
        cv.put(MyConstants.DESCRIPTION, description);
        cv.put(MyConstants.IMAGE_URI, imageURI);
        db.update(MyConstants.TABLE_NAME,cv, selection, null);
    }

    public void closeDb(){
        mySqlHelper.close();
    }

}
