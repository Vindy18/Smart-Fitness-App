package com.smartFitness.home.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //data base name
    public static final String DATABASE_NAME = "smartfitness.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE " + AdminMaster.Admins.TABLE_NAME +" (" +
                AdminMaster.Admins._ID + " INTEGER PRIMARY KEY," +
                AdminMaster.Admins.COLUMN_NAME_FIRSTNAME  + " TEXT NOT NULL," +
                AdminMaster.Admins.COLUMN_NAME_LASTNAME  + " TEXT NOT NULL," +
                AdminMaster.Admins.COLUMN_NAME_CITY  + " TEXT NOT NULL," +
                AdminMaster.Admins.COLUMN_NAME_EMAIL  + " TEXT NOT NULL UNIQUE," +
                AdminMaster.Admins.COLUMN_NAME_MOBILENUMBER  + " TEXT NOT NULL," +
                AdminMaster.Admins.COLUMN_NAME_PASSWORD  + " TEXT NOT NULL UNIQUE)" ;

        db.execSQL(SQL_CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addAdmin(String firstName, String lastName, String city, String email, String mobileNumber, String password ){
        //Get all date repository write mode
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AdminMaster.Admins.COLUMN_NAME_FIRSTNAME,firstName);
        values.put(AdminMaster.Admins.COLUMN_NAME_LASTNAME,lastName);
        values.put(AdminMaster.Admins.COLUMN_NAME_CITY,city);
        values.put(AdminMaster.Admins.COLUMN_NAME_EMAIL,email);
        values.put(AdminMaster.Admins.COLUMN_NAME_MOBILENUMBER,mobileNumber);
        values.put(AdminMaster.Admins.COLUMN_NAME_PASSWORD,password);

        long newRowId = (db.insert(AdminMaster.Admins.TABLE_NAME,null, values));

        if(newRowId > 0){
            return true;
        }else{
            return false;
        }

    }

    public List readAllAdminInfo(String req){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                AdminMaster.Admins._ID,
                AdminMaster.Admins.COLUMN_NAME_FIRSTNAME,
                AdminMaster.Admins.COLUMN_NAME_LASTNAME,
                AdminMaster.Admins.COLUMN_NAME_CITY,
                AdminMaster.Admins.COLUMN_NAME_EMAIL,
                AdminMaster.Admins.COLUMN_NAME_MOBILENUMBER,
                AdminMaster.Admins.COLUMN_NAME_PASSWORD
        };

        //String sortOder = AdminMaster.Admins.COLUMN_NAME_FIRSTNAME+" ASC";

        Cursor cursor = db.query(
                AdminMaster.Admins.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List firstNames = new ArrayList();
        List lastNames = new ArrayList();
        List citys = new ArrayList();
        List emails = new ArrayList();
        List mobileNumbers = new ArrayList();
        List passwords = new ArrayList();

        while (cursor.moveToNext()){

            String firstName = cursor.getString(cursor.getColumnIndexOrThrow(AdminMaster.Admins.COLUMN_NAME_FIRSTNAME));
            String lastName = cursor.getString(cursor.getColumnIndexOrThrow(AdminMaster.Admins.COLUMN_NAME_LASTNAME));
            String city = cursor.getString(cursor.getColumnIndexOrThrow(AdminMaster.Admins.COLUMN_NAME_CITY));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(AdminMaster.Admins.COLUMN_NAME_EMAIL));
            String mobileNumber = cursor.getString(cursor.getColumnIndexOrThrow(AdminMaster.Admins.COLUMN_NAME_MOBILENUMBER));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(AdminMaster.Admins.COLUMN_NAME_PASSWORD));

            emails.add(email);
            passwords.add(password);


        }
        cursor.close();

        if(req == "email"){
            return emails;
        }else if(req == "Password"){
            return passwords;
        }else{
            return null;
        }
    }

    public void delete() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting rows
        sqLiteDatabase.delete(AdminMaster.Admins.TABLE_NAME, null, null);
        sqLiteDatabase.close();
    }


}
