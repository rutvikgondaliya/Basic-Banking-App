package com.rutvik.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9664801998,'Rutvik',10984.00,'gondaliyarutvik281@gmail.com','XXXXXXXXXXXX0066','SEBN0023456')");
        db.execSQL("insert into user_table values(7435920624,'Paresh',456.33,'paresh311@gmail.com','XXXXXXXXXXXX3201','GHTN0789457')");
        db.execSQL("insert into user_table values(9913100322,'Bharat',256.25,'ahujabharat099@gmail.com','XXXXXXXXXXXX8506','ABCN0004659')");
        db.execSQL("insert into user_table values(8945678916,'Vishal',7894.00,'vishal007@gmail.com','XXXXXXXXXXXX1235','SBIN0066064')");
        db.execSQL("insert into user_table values(5645789946,'Vatsal',984.00,'vatasal01@gmail.com','XXXXXXXXXXXX9845','XYZN0894569')");
        db.execSQL("insert into user_table values(4567893216,'Bhautik',2056.00,'bhautikbheda007@gmail.com','XXXXXXXXXXXX3366','ABCD0123456')");
        db.execSQL("insert into user_table values(1234564587,'Parth',12389.00,'parthvibhani23@gmail.com','XXXXXXXXXXXX8944','BOIN0023456')");
        db.execSQL("insert into user_table values(4567897941,'Amit',100.34,'amit45678@gmail.com','XXXXXXXXXXXX7833','SEBN0894564')");
        db.execSQL("insert into user_table values(1237894568,'Daxesh',1454.00,'daxeshitaliya01@gmail.com','XXXXXXXXXXXX4560','ABCN0023489')");
        db.execSQL("insert into user_table values(6678451998,'Aliya',5897.00,'aliyabhatt81@gmail.com','XXXXXXXXXXXX0457','IDFC0894716')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}