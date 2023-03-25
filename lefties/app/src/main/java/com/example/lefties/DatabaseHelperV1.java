package com.example.lefties;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// 0.
public class DatabaseHelperV1 extends SQLiteOpenHelper {

    final static  String  DATABASE_NAME = "Information.db";
    final static int DATABASE_VERSION = 6;
    final static String TABLE1_NAME = "Student_table";
    final static String T1COL_1 = "Id";
    final static String T1COL_2 = "name";
    final static String T1COL_3 = "student_id";
    final static String T1COL_4 = "mobile";
    final static String T1COL_5 = "course_id";


    // 1. this is automatically generated using alt + enter
    public DatabaseHelperV1(@Nullable Context context){  // removed all params except context
        super(context, DATABASE_NAME, null, DATABASE_VERSION); // factory us if ur using a specific class
        // super creates constructor using parent class
        SQLiteDatabase database = this.getWritableDatabase(); // when object/db? is created I will have access to read or write to that; Opens db for writing or reading
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        // 2.
        // write queries to create table
        // this will be auto generated id
//        String query = String.format("CREATE TABLE %s(%sINTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT,%s TEXT", TABLE1_NAME, T1COL_1, T1COL_2, T1COL_3, T1COL_4, T1COL_5);
        String query = "CREATE TABLE " + TABLE1_NAME + "(" + T1COL_1 + " INTEGER PRIMARY KEY, "  + T1COL_2 + " TEXT, "  + T1COL_3 + " TEXT, "  + T1COL_4 + " TEXT,"  + T1COL_5 + " TEXT)";


//        String query = "CREATE TABLE " + TABLE1_NAME + "("
//                + T1COL_1 + "INTEGER PRIMARY KEY, " // this will be auto generated id
//                + T1COL_2 + " TEXT, "
//                + T1COL_3 + " TEXT, "
//                + T1COL_4 + " TEXT,"
//                + T1COL_5 + " TEXT" ;
        // execute query
        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // 3.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        onCreate(sqLiteDatabase);
    }

    // 4.
    public boolean addData(String name, String studentId, String mobile, String courseId){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues(); // will save values in key value pair
        values.put(T1COL_2, name);
        values.put(T1COL_3, studentId);
        values.put(T1COL_4, mobile);
        values.put(T1COL_5, courseId);

        long l =  sqLiteDatabase.insert(TABLE1_NAME, null, values); // returns long value ; null is used when we are sure there are no null values; sql does not allow us to insert empty rows otherwise will cause exception; if you are not sure that data is empty, change null into column?

        if(l > 0){
            // if returned value is positive = success
            return true;
        }else{
            return false;
        }
    }

    // 6.
    // after adding view button
    public Cursor viewData(){
        SQLiteDatabase database = this.getReadableDatabase();
        // 6.1.
        String query = "SELECT * FROM " + TABLE1_NAME;
        Cursor cursor = database.rawQuery(query, null); // this cursor allows to retrieve data row by row;

        // 8.
//        String query = "SELECT * FROM " + TABLE1_NAME + "WHERE ID = ?";
//        Cursor cursor = database.rawQuery(query, new String[2]); // `?` is replaced by selection arg
        return cursor;
    }

    // 10.
    public boolean deleteRec(int id){
        SQLiteDatabase sqlite = this.getWritableDatabase();
        int d = sqlite.delete(TABLE1_NAME, "Id = ?",
                new String[]{Integer.toString(id)}
        ); // this return string of positive or negative depending on success

        return d > 0;
    }

    // 11.
    public boolean updateRec(int id, String c){
        SQLiteDatabase sqlite = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T1COL_4, c);
        int d = sqlite.update(TABLE1_NAME, contentValues, "Id = ?",
                new String[]{Integer.toString(id)}
        );
        return d > 0;
    }

}
