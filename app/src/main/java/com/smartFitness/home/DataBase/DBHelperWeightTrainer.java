package com.smartFitness.home.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelperWeightTrainer extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "smartfitness.db";

    public DBHelperWeightTrainer(Context context)  { super(context, DATABASE_NAME, null, 1); }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE " + NutritionistMaster.Nutritionists.TABLE_NAME +" (" +
                WeightTrainerMaster.WeightTrainer._ID + " INTEGER PRIMARY KEY," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME+ " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ADDRESS+ " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER+ " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_WORKINGHOURS+ " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_PHOTO  + " BLOB )" ;
        db.execSQL(SQL_CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
