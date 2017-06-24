package com.loutasae.tolet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.util.Log;


public class MyDBManager extends SQLiteOpenHelper {

    public static final String TAG = "Formal";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "flats.db";
    public static final String TABLE_NAME = "flatstable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_LOCALITY ="locality";
    public static final String  COLUMN_RENT = "rent";
    public static final String COLUMN_DATE = "fromdate";
    public static final String COLUMN_REMARKS = "remarks";

    SQLiteDatabase db;

    public MyDBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CITY + " TEXT, " +
                COLUMN_LOCALITY + " TEXT, " +
                COLUMN_RENT + " INTEGER, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_REMARKS + " TEXT " +
                " );";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void addnewflat(EachFlat flat){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CITY,flat.getCity());
        values.put(COLUMN_LOCALITY,flat.getLocality());
        values.put(COLUMN_RENT,flat.getRent());
        values.put(COLUMN_DATE, flat.getDate());
        values.put(COLUMN_REMARKS,flat.getRemarks());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.i(TAG,"Upload Done");
        //Toast.makeText(Context,"A new entry has been recorded...",Toast.LENGTH_LONG);
    }

    public void remove(String CITY){
        SQLiteDatabase db = getWritableDatabase();
        Log.i(TAG,"DB selected");
        String q = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_CITY + "=\"" + CITY + "\";";
        Log.i(TAG,"q started");
        db.execSQL(q);
        Log.i(TAG,"q ended");
        db.close();
    }

    public Cursor getallrows(){
        SQLiteDatabase db = getWritableDatabase();
        String q = "SELECT * FROM " + TABLE_NAME + " WHERE 1;";
        Cursor cursor = db.rawQuery(q,null);
        Log.i(TAG, "123");
        //Log.i(TAG, converttostring());
        Log.i(TAG, "456");
        return cursor;
    }


    public String converttostring(){
        SQLiteDatabase db = getWritableDatabase();
        Log.i(TAG,"DB selection Done");
        String q = "SELECT * FROM " + TABLE_NAME ;
        Log.i(TAG,"raw q starts");
        Cursor c = db.rawQuery(q,null);
        Log.i(TAG,"raw q Done, Cursor returned...");
        String retStr = "";
        c.moveToFirst();
        while(!c.isAfterLast()){
            retStr += c.getString(c.getColumnIndex(COLUMN_CITY));
            retStr += "\n";
            c.moveToNext();
        }
        db.close();
        return retStr;
    }

}
