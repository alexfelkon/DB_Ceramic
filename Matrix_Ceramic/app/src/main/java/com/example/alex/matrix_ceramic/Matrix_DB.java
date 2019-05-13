package com.example.alex.matrix_ceramic;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alex on 10.05.2019.
 */
public class Matrix_DB {
    public static final String DATABASE = "product";
    public static final int VERSION = 1;
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
            RACK + " INTEGER);";

    private final Context context;

    public Matrix_DB(Context context) {
        this.context = context;
    }
    private DBHelper dbHelper;

    private SQLiteDatabase sqLiteDatabase;

    //открыть подключение
    public void open(){
        dbHelper = new DBHelper(context, DATABASE, null, VERSION);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    //закрыть подключение
    public void close(){
        if (dbHelper!=null)dbHelper.close();
    }

    //Добавить запись в таблицу
    public void add(String name, String type, String part, String sInventory, String sWeight, String sRack){
        ContentValues cv = new ContentValues();
        int inventory, weight, rack;

        inventory = (sInventory.equals(""))? 0: Integer.parseInt(sInventory);
        weight = (sWeight.equals(""))? 0 : Integer.parseInt(sWeight);
        rack = (sRack.equals(""))? 0 :Integer.parseInt(sRack);

        cv.put(INVENTORY,inventory);
        cv.put(NAME, name);
        cv.put(TYPE, type);
        cv.put(PART, part);
        cv.put(WEIGHT, weight);
        cv.put(RACK, rack);
        sqLiteDatabase.insert(TABLE_NAME,null,cv);

    }



    public class DBHelper extends SQLiteOpenHelper{

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
