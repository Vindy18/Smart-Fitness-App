package com.smartFitness.home.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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

    public boolean addNutritionist(String name, String location, String email, String mobileNumber, String description){
        //Get all date repository write mode
        SQLiteDatabase db = getWritableDatabase();

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

    public int updateNutritionist(String keyEmail,String name, String location, String email, String mobileNumber, String description){
        SQLiteDatabase db= getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_NAME,name);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION,location);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL,email);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER,mobileNumber);
        values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_DESCRIPTION,description);
        //values.put(NutritionistMaster.Nutritionists.COLUMN_NAME_PHOTO,photo);

        String sql = NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL + " LIKE ?";
        String[]  selectionArgs = {keyEmail};

        int count = db.update(NutritionistMaster.Nutritionists.TABLE_NAME,values, sql,selectionArgs);

        return count;
    }

    public void deleteNutritionists(String Email) {
        SQLiteDatabase db= getReadableDatabase();
        //deleting nutritionists
        String sql = NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL + " LIKE ?";
        String[] selectionArgs = {Email};
        db.delete(NutritionistMaster.Nutritionists.TABLE_NAME, sql ,selectionArgs );

    }


}
