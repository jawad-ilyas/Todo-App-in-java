package com.example.bottomnaviagtion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DbManager extends SQLiteOpenHelper {

    private static final String  DB_NAME = "TODO";
    private static final int DB_VERSION = 10; // Increment the version number

    private static final String Tb_MYTODO = "MYTODO";
    private static final String Tb_DELCOUNT = "DELETE_COUNT";
    private static final String CL_TITLE = "TITLE";
    private static final String CL_DESC = "DESCRIPTION";
    private static final String CL_ID = "ID";
    private static final String CL_DEL_ID = "DEL_ID";
    private static final String CL_DEL_COUNT = "DEL_COUNT";
    public DbManager(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // table needs into databse

        String query = "CREATE TABLE " + Tb_MYTODO + " (" +
                CL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CL_TITLE + " TEXT NOT NULL, " +
                CL_DESC + " TEXT NOT NULL)";
        String query2 = "CREATE TABLE " + Tb_DELCOUNT + " (" +
                CL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CL_DEL_COUNT + " INTEGER)";




        db.execSQL(query);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("Database", "Old Version: " + oldVersion + ", New Version: " + newVersion);

        if (oldVersion < 5) {
            db.execSQL("DROP TABLE IF EXISTS " + Tb_MYTODO);
            db.execSQL("DROP TABLE IF EXISTS " + Tb_DELCOUNT);
            Log.d("Database", "Tables dropped");

            onCreate(db); // Recreate the table with the new schema
        }
    }


    public long addRecords(String title , String desc)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(CL_TITLE, title);
        cv.put(CL_DESC, desc);
        double res = db.insert(Tb_MYTODO, null , cv);
        Log.d("result" , "Total Records : " + res);
        if(res == -1)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
    public long addDelete()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv  = new ContentValues();
        cv.put(CL_DEL_COUNT, 0);

        double res = db.insert(Tb_DELCOUNT, null , cv);
        Log.d("result" , "Total Records : " + res);
        if(res == -1)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    public Cursor readRecords(){

        Cursor cursor ;

        String query = "SELECT * FROM " + Tb_MYTODO;

        SQLiteDatabase db;

        db = this.getReadableDatabase();

        cursor = db.rawQuery(query , null);

        return  cursor;
    }

    public Cursor searchRecords(String value){
        Cursor cursor;

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + Tb_MYTODO + " WHERE " + CL_TITLE + " = ?";

        cursor = db.rawQuery(query, new String[]{value});

        return cursor;
    }

    public int getTotalRecordsCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + Tb_MYTODO, null);
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }

        db.close();
        return count;
    }

    public void DeleteTask(int id){


        SQLiteDatabase db = this.getWritableDatabase();

        String query = "DELETE FROM " + Tb_MYTODO + " WHERE " + CL_ID + " = " + id;

        db.execSQL(query);
    }

    public void updateDeleteCount() {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE " + Tb_DELCOUNT + " SET " + CL_DEL_COUNT + " = " + CL_DEL_COUNT + " + 1";

        Log.d("result", "Total Records for query: " + query);

        // Execute the update query
        db.execSQL(query);


    }



    public int getTotalDeleteCount() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;

        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + Tb_DELCOUNT, null);
        if (cursor != null && cursor.moveToFirst()) {
            count = cursor.getInt(0);
            cursor.close();
        }

        // Close the cursor (if it's not already closed)
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }

        // Don't close the database here; return the count first
        return count;
    }


}
