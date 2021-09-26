package com.smartFitness.home.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelperWeightTrainer extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "smartfitness.db";

    public DBHelperWeightTrainer(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE " + NutritionistMaster.Nutritionists.TABLE_NAME + " (" +
                WeightTrainerMaster.WeightTrainer._ID + " INTEGER PRIMARY KEY," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ADDRESS + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_WORKINGHOURS + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_PHOTO + " BLOB )";
        db.execSQL(SQL_CREATE_ENTRIES);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addWeightTrainer(String name, String address, String contactnumber, String workinghours, String about) {

        //Get all date repository write mode
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME, name);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ADDRESS, address);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER, contactnumber);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_WORKINGHOURS, workinghours);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT, about);

        long newRowId = (db.insert(WeightTrainerMaster.WeightTrainer.TABLE_NAME, null, values));

        if (newRowId > 0) {
            return true;
        } else {
            return false;
        }

    }

    public void readAllWeightTrainerInfo(String req) {

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                WeightTrainerMaster.WeightTrainer._ID,
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME,
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ADDRESS,
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER,
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_WORKINGHOURS,
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT

        };

        //String sortOder = AdminMaster.Admins.COLUMN_NAME_FIRSTNAME+" ASC";

        Cursor cursor = db.query(
                WeightTrainerMaster.WeightTrainer.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List Name = new ArrayList();
        List Address = new ArrayList();
        List ContactNumber = new ArrayList();
        List WorkingHours = new ArrayList();
        List About = new ArrayList();

        while (cursor.moveToNext()) {

            String name = cursor.getString(cursor.getColumnIndexOrThrow(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME));
            String address = cursor.getString(cursor.getColumnIndexOrThrow(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ADDRESS));
            String contactnumber = cursor.getString(cursor.getColumnIndexOrThrow(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER));
            String workinghours = cursor.getString(cursor.getColumnIndexOrThrow(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_WORKINGHOURS));
            String about = cursor.getString(cursor.getColumnIndexOrThrow(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT));
        }
    };
}

