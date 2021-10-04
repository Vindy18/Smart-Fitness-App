package com.smartFitness.home.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smartFitness.home.Model.Admin;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //data base name
    public static final String DATABASE_NAME = "smartfitness.db";

    //Create Database
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Create table query
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + AdminMaster.Admins.TABLE_NAME +" (" +
                AdminMaster.Admins._ID + " INTEGER PRIMARY KEY," +
                AdminMaster.Admins.COLUMN_NAME_FIRSTNAME  + " TEXT NOT NULL," +
                AdminMaster.Admins.COLUMN_NAME_LASTNAME  + " TEXT NOT NULL," +
                AdminMaster.Admins.COLUMN_NAME_CITY  + " TEXT NOT NULL," +
                AdminMaster.Admins.COLUMN_NAME_EMAIL  + " TEXT NOT NULL UNIQUE," +
                AdminMaster.Admins.COLUMN_NAME_MOBILENUMBER  + " TEXT NOT NULL," +
                AdminMaster.Admins.COLUMN_NAME_PASSWORD  + " TEXT NOT NULL)" ;

        //Create above table
        db.execSQL(SQL_CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add addAdmin
    public boolean addAdmin(String firstName, String lastName, String city, String email, String mobileNumber, String password ){

        //Get all data repository write mode
        SQLiteDatabase db = getWritableDatabase();

        //declare " content values"
        ContentValues values = new ContentValues();

        // Assign all the parameters to "values" variables
        values.put(AdminMaster.Admins.COLUMN_NAME_FIRSTNAME,firstName);
        values.put(AdminMaster.Admins.COLUMN_NAME_LASTNAME,lastName);
        values.put(AdminMaster.Admins.COLUMN_NAME_CITY,city);
        values.put(AdminMaster.Admins.COLUMN_NAME_EMAIL,email);
        values.put(AdminMaster.Admins.COLUMN_NAME_MOBILENUMBER,mobileNumber);
        values.put(AdminMaster.Admins.COLUMN_NAME_PASSWORD,password);

        //insert(values)query to database
        long newRowId = (db.insert(AdminMaster.Admins.TABLE_NAME,null, values));

        //Check "newRowId" if success return greater than 0 value
        if(newRowId > 0){
            return true;
        }else{
            return false;
        }
    }

    public List readAllAdminInfo(String req){

        //Get all date repository write mode
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

        // Read data Row by Row (cursor in one line)
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

    //Get Admin method
    public Admin getAdmin(String Email) {

        //Get all date repository readable mode
        SQLiteDatabase db = getReadableDatabase();

        //Get nutritionist query
        String sql = "select * from admins where email=?";

        //Get row details to cursor object
        Cursor cursor = db.rawQuery(sql , new String[]{Email});

        //create Admin object
        Admin admin = new Admin();

        // //Read data fist match row details
        if (cursor.moveToFirst()) {

            //Get one nutritionist details
            admin.id = cursor.getInt(cursor.getColumnIndex(AdminMaster.Admins._ID));
            admin.firstName = cursor.getString(cursor.getColumnIndex(AdminMaster.Admins.COLUMN_NAME_FIRSTNAME));
            admin.lastName = cursor.getString(cursor.getColumnIndex(AdminMaster.Admins.COLUMN_NAME_LASTNAME));
            admin.city = cursor.getString(cursor.getColumnIndex(AdminMaster.Admins.COLUMN_NAME_CITY));
            admin.email = cursor.getString(cursor.getColumnIndex(AdminMaster.Admins.COLUMN_NAME_EMAIL));
            admin.mobileNumber = cursor.getString(cursor.getColumnIndex(AdminMaster.Admins.COLUMN_NAME_MOBILENUMBER));
            admin.Password = cursor.getString(cursor.getColumnIndex(AdminMaster.Admins.COLUMN_NAME_PASSWORD));
        }

        cursor.close();
        db.close();
        return admin;
    }


    //deleting Admin method
    public void deleteAdmin(String Email) {

        //Get all date repository readable mode
        SQLiteDatabase db= getReadableDatabase();

        //Delete query
        String sql = AdminMaster.Admins.COLUMN_NAME_EMAIL + " LIKE ?";
        String[] selectionArgs = {Email};
        db.delete(AdminMaster.Admins.TABLE_NAME, sql ,selectionArgs );

    }

    //update admin method
    public int updateAdmin(String keyEmail,String firstName, String lastName, String city, String email, String mobileNumber, String password){

        //Get all date repository readable mode
        SQLiteDatabase db= getReadableDatabase();

        //declare "content values" object
        ContentValues values = new ContentValues();

        //Assign all the parameter to "values" variable
        values.put(AdminMaster.Admins.COLUMN_NAME_FIRSTNAME,firstName);
        values.put(AdminMaster.Admins.COLUMN_NAME_LASTNAME,lastName);
        values.put(AdminMaster.Admins.COLUMN_NAME_CITY,city);
        values.put(AdminMaster.Admins.COLUMN_NAME_EMAIL,email);
        values.put(AdminMaster.Admins.COLUMN_NAME_MOBILENUMBER,mobileNumber);
        values.put(AdminMaster.Admins.COLUMN_NAME_PASSWORD,password);

        //sql query
        String sql = AdminMaster.Admins.COLUMN_NAME_EMAIL + " LIKE ?";
        String[]  selectionArgs = {keyEmail};

        //update and return the row count
        int count = db.update(AdminMaster.Admins.TABLE_NAME,values, sql,selectionArgs);

        return count;
    }

    //for debug
    public void delete() {
        SQLiteDatabase db= getReadableDatabase();
        db.execSQL("delete from "+ AdminMaster.Admins.TABLE_NAME);
    }
}
