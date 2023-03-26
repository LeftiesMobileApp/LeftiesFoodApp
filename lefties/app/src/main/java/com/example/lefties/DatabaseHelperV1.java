
/*
HI EVERYONE, HERE ARE THE AGREED UPON DATABASE TABLE AND FIELDS

## Account_Table

- account_ID (PK)
- account_Name - ******************************is restaurant name or customer name******************************
- account_Type  `CUSTOMER`  `RESTAURANT`
- account_Email
- account_Phone
- account_Address
- account_City

## Restaurant_Table

- account_ID (FK)
- restaurant_Type `CHINESE` `INDIAN` `GREEK` `MEXICAN`

## Food_Table

- food_ID (PK)
- account_ID (FK) - *account id connected to the restaurant*
- food_Name
- food_Discount_Price
- food_Regular_Price
- food_Qty

## Cart_Table

(This table holds the order items)

- order_id (FK)
- food_ID (FK)
- food_Qty_Ordered
- checkout_Status `TRUE`

## Order_Table

- order_ID
- order_Status `PROCESSING` `COMPLETED` `CANCELLED`
- order_type `DELIVERY`, `PICKUP`
- order_Date
- order_Total


 */

package com.example.lefties;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// 0.
public class DatabaseHelperV1 extends SQLiteOpenHelper {

    final static  String  DATABASE_NAME = "Lefties.db";
    final static int DATABASE_VERSION = 3;
    // TABLE 1: Account
    final static String TABLE1_NAME = "Student_table";
    final static String T1COL_1 = "Id";
    final static String T1COL_2 = "name";
    final static String T1COL_3 = "student_id";
    final static String T1COL_4 = "mobile";
    final static String T1COL_5 = "course_id";


    // TABLE 2: Restaurant

    // TABLE 3: Food
    final static String TABLE3_NAME = "Food_table";
    final static String T3COL_1 = "food_id";
    final static String T3COL_2 = "account_id";
    final static String T3COL_3 = "food_name";
    final static String T3COL_4 = "food_regular_price";
    final static String T3COL_5 = "food_discounted_price";
    final static String T3COL_6 = "food_qty";



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

        String query3 = "CREATE TABLE " + TABLE3_NAME + "("
                + T3COL_1 + " INTEGER PRIMARY KEY, "
                + T3COL_2 + " INTEGER, "
                + T3COL_3 + " TEXT, "
                + T3COL_4 + " REAL,"
                + T3COL_5 + " REAL,"
                + T3COL_6 + " INTEGER"
                    +  ")";
        // execute query
        sqLiteDatabase.execSQL(query3);
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

    public Cursor viewFood(){
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE3_NAME;
        Cursor cursor = database.rawQuery(query, null); // this cursor allows
        return cursor;
    }

    public boolean createFoodItem(
            Integer accountId,
            String name,
            double discountPrice,
            double regularPrice,
            Integer qty
    ){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T3COL_2, accountId);
        values.put(T3COL_3, name);
        values.put(T3COL_4, discountPrice);
        values.put(T3COL_5, regularPrice);
        values.put(T3COL_6, qty);

        long l =  sqLiteDatabase.insert(TABLE3_NAME, null, values);
        if(l > 0){
            return true;
        }else{
            return false;
        }
    }

    public void seedFoodTable(){
        createFoodItem(1, "Tandoori Chicken", 30, 25, 3);
        createFoodItem(1, "Mamon", 4, 10, 16);
        createFoodItem(1, "Iced Coffee", 3, 8, 160);
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
