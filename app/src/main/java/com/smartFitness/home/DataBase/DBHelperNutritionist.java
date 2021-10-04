package com.smartFitness.home.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.smartFitness.home.Model.Nutritionist;

import java.util.ArrayList;
import java.util.List;

public class DBHelperNutritionist extends SQLiteOpenHelper {

    //Assign Database Name
    public static final String DATABASE_NAME = "smartFitness.db";

    //Create Database
    public DBHelperNutritionist(Context context)  { super(context, DATABASE_NAME, null, 1); }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table query
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + NutritionistMaster.Nutritionists.TABLE_NAME +" (" +
                NutritionistMaster.Nutritionists._ID + " INTEGER PRIMARY KEY," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_NAME  + " TEXT NOT NULL," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION + " TEXT NOT NULL," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL  + " TEXT NOT NULL UNIQUE," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER  + " TEXT NOT NULL," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION  + " TEXT," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_PHOTO  + " BLOB )" ;

        //Create above table
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add Nutritionist
    public boolean addNutritionist(String name, String location, String email, String mobileNumber, String description){

        //Get all data repository write mode
        SQLiteDatabase db = getWritableDatabase();

        //declare " content values"
        ContentValues values = new ContentValues();

        // Assign all the parameters to "values" variables
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_NAME,name);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION,location);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL,email);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER,mobileNumber);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION,description);
        //values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_PHOTO,photo);

        //insert(values)query to database
        long  newRowId = (db.insert(NutritionistMaster.Nutritionists.TABLE_NAME, null, values));

        //Check "newRowId" if success return greater than 0 value
        if(newRowId > 0){
            return true;
        }else{
            return false;
        }

    }

    //Get Nutritionist method
    public Nutritionist getNutritionist(String Email) {

        //Get all date repository readable mode
        SQLiteDatabase db = getReadableDatabase();

        //Get nutritionist query
        String sql = "select * from nutritionists where email=?";

        //Get row details to cursor object
        Cursor cursor = db.rawQuery(sql , new String[]{Email});

        //create nutritionists object
        Nutritionist nutritionist = new Nutritionist ();

        //Read data fist match row details
        if (cursor.moveToFirst()) {

            //Get one nutritionist details
            nutritionist.id = cursor.getInt(cursor.getColumnIndex(NutritionistMaster.Nutritionists._ID));
            nutritionist.name = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_NAME));
            nutritionist.location = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION));
            nutritionist.email = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL));
            nutritionist.mobileNumber = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER));
            nutritionist.description = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION));
        }

        cursor.close();
        db.close();

        return nutritionist;
    }

    //update Nutritionist method
    public int updateNutritionist(String keyEmail,String name, String location, String email, String mobileNumber, String description){

        //Get all date repository readable mode
        SQLiteDatabase db= getReadableDatabase();

        //declare "content values" object
        ContentValues values = new ContentValues();

        //Assign all the parameter to "values" variable
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_NAME,name);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION,location);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL,email);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER,mobileNumber);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION,description);
        //values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_PHOTO,photo);

        //sql query
        String sql = NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL + " LIKE ?";
        String[]  selectionArgs = {keyEmail};

        //update and return the row count
        int count = db.update(NutritionistMaster.Nutritionists.TABLE_NAME,values, sql,selectionArgs);

        return count;
    }

    //deleting nutritionists method
    public void deleteNutritionists(String Email) {

        //Get all date repository readable mode
        SQLiteDatabase db= getReadableDatabase();

        // Delete query
        String sql = NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL + " LIKE ?";
        String[] selectionArgs = {Email};
        db.delete(NutritionistMaster.Nutritionists.TABLE_NAME, sql ,selectionArgs );
    }

    //Get all nutritionists
    public List<Nutritionist> getAllNutritionists() {

        //Get all date repository readable mode
        SQLiteDatabase db = getReadableDatabase();

        //declare an array list object
        List NutritionistList = new ArrayList();

        //SQl query for get all nutritionists
        String sql = "select * from nutritionists";

        //Get row details to cursor object
        Cursor cursor = db.rawQuery(sql ,null);


        //Read data Row by Row (cursor in one line)
            if (cursor.moveToFirst()) {
                do {
                    //create nutritionists object
                    Nutritionist nutritionist = new Nutritionist ();

                    //Get one nutritionist details
                    nutritionist.id = cursor.getInt(cursor.getColumnIndex(NutritionistMaster.Nutritionists._ID));
                    nutritionist.name = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_NAME));
                    nutritionist.location = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION));
                    nutritionist.email = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL));
                    nutritionist.mobileNumber = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER));
                    nutritionist.description = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION));

                    //add Nutritionist to list
                    NutritionistList.add(nutritionist);

                }while (cursor.moveToNext());
            }

        cursor.close();
        db.close();

        return NutritionistList;
    }
}
