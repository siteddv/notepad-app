package com.vino.gradom.notepad.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    public void insertToDb(String title, String description){
        ContentValues cv = new ContentValues();
        cv.put(MyConstants.TITLE, title);
        cv.put(MyConstants.DESCRIPTION, description);
        db.insert(MyConstants.TABLE_NAME, null, cv);
    }

    public LinkedList<String> getTitlesFromDb(){
        LinkedList<String> resultList = new LinkedList<String>();
        Cursor cursor = db.query(
                MyConstants.TABLE_NAME,
                null, null,
                null, null,
                null, null);

        while (cursor.moveToNext()){
            String title = cursor.getString(cursor.getColumnIndex(MyConstants.TITLE));
            resultList.add(title);
        }

        cursor.close();
        return resultList;
    }

    public void closeDb(){
        mySqlHelper.close();
    }

}
