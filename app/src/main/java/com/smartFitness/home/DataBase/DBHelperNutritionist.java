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

    public static final String DATABASE_NAME = "smartFitness.db";

    public DBHelperNutritionist(Context context)  { super(context, DATABASE_NAME, null, 1); }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a table
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + NutritionistMaster.Nutritionists.TABLE_NAME +" (" +
                NutritionistMaster.Nutritionists._ID + " INTEGER PRIMARY KEY," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_NAME  + " TEXT NOT NULL," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION + " TEXT NOT NULL," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL  + " TEXT NOT NULL UNIQUE," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER  + " TEXT NOT NULL," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION  + " TEXT," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_PHOTO  + " BLOB )" ;

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add Nutritionist
    public boolean addNutritionist(String name, String location, String email, String mobileNumber, String description){
        //Get all date repository write mode
        SQLiteDatabase db = getWritableDatabase();

        //declare "values"
        ContentValues values = new ContentValues();

        // Assign all the parameter to "values" variable
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_NAME,name);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION,location);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL,email);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER,mobileNumber);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION,description);
        //values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_PHOTO,photo);

        //insert quarry
        long  newRowId = (db.insert(NutritionistMaster.Nutritionists.TABLE_NAME, null, values));

        //Check "newRowId" if success return grater than 0 value
        if(newRowId > 0){
            return true;
        }else{
            return false;
        }

    }

    //Get Nutritionist
    public Nutritionist getNutritionist(String Email) {
        //Get all date repository write mode
        SQLiteDatabase db = getReadableDatabase();

        //Get all date repository write mode
        String sql = "select * from nutritionists where email=?";
        Cursor cursor = db.rawQuery(sql , new String[]{Email});

        // create nutritionists
        Nutritionist nutritionist = new Nutritionist ();

        // Read data fist match row details
        if (cursor.moveToFirst()) {

            // Get one nutritionist details
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

    //update Nutritionist
    public int updateNutritionist(String keyEmail,String name, String location, String email, String mobileNumber, String description){
        //Get all date repository read mode
        SQLiteDatabase db= getReadableDatabase();

        // declare "values"
        ContentValues values = new ContentValues();

        // Assign all the parameter to "values" variable
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_NAME,name);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION,location);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL,email);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER,mobileNumber);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION,description);
        //values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_PHOTO,photo);

        String sql = NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL + " LIKE ?";
        String[]  selectionArgs = {keyEmail};

        //update and return the row count
        int count = db.update(NutritionistMaster.Nutritionists.TABLE_NAME,values, sql,selectionArgs);

        return count;
    }

    //deleting nutritionists
    public void deleteNutritionists(String Email) {

        //Get all date repository read mode
        SQLiteDatabase db= getReadableDatabase();

        // Delete quarry
        String sql = NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL + " LIKE ?";
        String[] selectionArgs = {Email};
        db.delete(NutritionistMaster.Nutritionists.TABLE_NAME, sql ,selectionArgs );
    }

    //Get all nutritionists
    public List<Nutritionist> getAllNutritionists() {

        //Get all date repository read mode
        SQLiteDatabase db = getReadableDatabase();

        // declare a array list
        List NutritionistList = new ArrayList();

        //SQl quarry for get all nutritionists
        String sql = "select * from nutritionists";

        //Get row details to cursor object
        Cursor cursor = db.rawQuery(sql ,null);


        // Read data Row by Row (cursor in one line)
            if (cursor.moveToFirst()) {
                do {
                    // create nutritionists
                    Nutritionist nutritionist = new Nutritionist ();

                    // Get one nutritionist details
                    nutritionist.id = cursor.getInt(cursor.getColumnIndex(NutritionistMaster.Nutritionists._ID));
                    nutritionist.name = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_NAME));
                    nutritionist.location = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION));
                    nutritionist.email = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL));
                    nutritionist.mobileNumber = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER));
                    nutritionist.description = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION));

                    // add nut Nutritionist to list
                    NutritionistList.add(nutritionist);

                }while (cursor.moveToNext());
            }

        cursor.close();
        db.close();

        return NutritionistList;
    }
}
