package com.vino.gradom.notepad.db;

public class MyConstants {
    public static final String DB_NAME = "my.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "my_table";
    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";

    public static final String CREATE_TABLE_INSTRUCTION =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + " (" + _ID +" INTEGER PRIMARY KEY, "
            + TITLE + " TEXT, "
            + DESCRIPTION + " TEXT)";
    public static final String DROP_TABLE_INSTRUCTION =
            "DROP TABLE IS EXISTS " + TABLE_NAME;
}
