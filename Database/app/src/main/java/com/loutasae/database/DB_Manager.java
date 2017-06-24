package com.loutasae.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Button;

public class DB_Manager extends SQLiteOpenHelper {


    public static final String TAG = "Formal";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "product.db";
    public static final String TABLE_NAME = "productable";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";

    public DB_Manager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String q = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT " +
                " );";
        db.execSQL(q);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void addnewproduct(Product p){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,p.getName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();
        Log.i(TAG,"Upload Done");
        //Toast.makeText(Context,"A new entry has been recorded...",Toast.LENGTH_LONG);
    }

    public Cursor getallrows(){
        SQLiteDatabase db = getWritableDatabase();
        String q = "SELECT * FROM " + TABLE_NAME ;
        Cursor cursor = db.rawQuery(q,null);
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
            retStr += c.getString(c.getColumnIndex(COLUMN_NAME));
            retStr += "\n";
            c.moveToNext();
        }
        db.close();
        return retStr;
    }

    public void remove(String name){
        SQLiteDatabase db = getWritableDatabase();
        Log.i(TAG,"DB selected");
        String q = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=\"" + name + "\";";
        Log.i(TAG,"q started");
        db.execSQL(q);
        Log.i(TAG,"q ended");
        db.close();
    }

}
