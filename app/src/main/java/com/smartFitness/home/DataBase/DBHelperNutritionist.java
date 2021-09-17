package com.smartFitness.home.DataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperNutritionist extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "smartfitness.db";

    public DBHelperNutritionist(Context context)  { super(context, DATABASE_NAME, null, 1); }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE " + NutritionistMaster.Nutritionists.TABLE_NAME +" (" +
                NutritionistMaster.Nutritionists._ID + " INTEGER PRIMARY KEY," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_FIRSTNAME  + " TEXT NOT NULL," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_LASTNAME  + " TEXT NOT NULL," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_LOCATION + " TEXT NOT NULL," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_EMAIL  + " TEXT NOT NULL UNIQUE," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_MOBILENUMBER  + " TEXT NOT NULL," +
                NutritionistMaster.Nutritionists.COLUMN_NAME_PHOTO  + " BLOB )" ;

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
