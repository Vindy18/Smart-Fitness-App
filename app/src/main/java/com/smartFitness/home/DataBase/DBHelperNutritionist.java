package com.smartFitness.home.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.smartFitness.home.Model.Admin;
import com.smartFitness.home.Model.Nutritionists;

public class DBHelperNutritionist extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "smartFitness.db";

    public DBHelperNutritionist(Context context)  { super(context, DATABASE_NAME, null, 1); }


    @Override
    public void onCreate(SQLiteDatabase db) {

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
    public Nutritionists getNutritionist(String Email) {
        //Get all date repository write mode
        SQLiteDatabase db = getReadableDatabase();

        //Get all date repository write mode
        String sql = "select * from nutritionists where email=?";
        Cursor cursor = db.rawQuery(sql , new String[]{Email});

        // create nutritionists
        Nutritionists nutritionists = new Nutritionists ();

        // Read data, I simplify cursor in one line
        if (cursor.moveToFirst()) {

            // Get imageData in byte[]. Easy, right?
            nutritionists.id = cursor.getInt(cursor.getColumnIndex(NutritionistMaster.Nutritionists._ID));
            nutritionists.name = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_NAME));
            nutritionists.location = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION));
            nutritionists.email = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL));
            nutritionists.mobileNumber = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER));
            nutritionists.description = cursor.getString(cursor.getColumnIndex(NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION));
        }

        cursor.close();
        db.close();

        return nutritionists;
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
}
