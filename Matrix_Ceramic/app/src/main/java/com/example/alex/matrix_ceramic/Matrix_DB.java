package com.example.alex.matrix_ceramic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex on 10.05.2019.
 */
public class Matrix_DB {
    public static final String TABLE_NAME = "matrix";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String TYPE = "name";
    public static final String PART = "part";
    public static final String INVENTORY = "inventory";
    public static final String WEIGHT = "weight";
    public static final String RACK = "rack";
    public static final String CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT, " +
            TYPE + " TEXT, " +
            PART + " TEXT, " +
            INVENTORY + " INTEGER, " +
            WEIGHT + " INTEGER, " +
            RACK + " INTEGER)";


    public class DBHelper extends SQLiteOpenHelper{

        public static final String DATABASE = "product";
        public static final int VERSION = 1;

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, DATABASE, factory, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
