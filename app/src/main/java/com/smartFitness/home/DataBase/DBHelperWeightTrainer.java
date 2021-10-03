package com.smartFitness.home.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.webkit.WebChromeClient;
import android.widget.EditText;

import com.smartFitness.home.Model.Nutritionist;
import com.smartFitness.home.Model.WeightTrainer;

import java.util.ArrayList;
import java.util.List;

public class DBHelperWeightTrainer extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SmartFitness.db";

    public DBHelperWeightTrainer(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE " + WeightTrainerMaster.WeightTrainer.TABLE_NAME + " (" +
                WeightTrainerMaster.WeightTrainer._ID + " INTEGER PRIMARY KEY," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_LOCATION + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL  + " TEXT NOT NULL UNIQUE," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT + " TEXT," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_PHOTO + " BLOB )";

        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addWeightTrainer(String name, String location, String contactnumber, String email, String about) {

        //Get all date repository write mode
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME, name);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_LOCATION, location);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER, contactnumber);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL, email);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT, about);

        long newRowId = (db.insert(WeightTrainerMaster.WeightTrainer.TABLE_NAME, null, values));

        if (newRowId > 0) {
            return true;
        } else {
            return false;
        }

    }

    public int updateWeightTrainer(String keyEmail, String name, String location, String email,String contactnumber,String about){

        SQLiteDatabase db= getReadableDatabase();

        ContentValues values = new ContentValues();

        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME, name);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_LOCATION, location);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER, contactnumber);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL, email);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT, about);


        String sql = WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL + " LIKE ?";
        String[]  selectionArgs = {keyEmail};

        //update and return the row count
        int count = db.update(WeightTrainerMaster.WeightTrainer.TABLE_NAME,values, sql,selectionArgs);

        return count;
    }


    public void deleteWeightTrainer(String Email) {

        //Get all date repository read mode
        SQLiteDatabase db= getReadableDatabase();

        // Delete quarry
        String sql = WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL + " LIKE ?";
        String[] selectionArgs = {Email};
        db.delete(WeightTrainerMaster.WeightTrainer.TABLE_NAME, sql ,selectionArgs );
    }

    public List<WeightTrainer> getAllWeightTrainers() {

        //Get all date repository read mode
        SQLiteDatabase db = getReadableDatabase();

        // declare a array list
        List WeightTrainerList = new ArrayList();

        //SQl quarry for get all nutritionists
        String sql = "select * from weighttrainer";

        //Get row details to cursor object
        Cursor cursor = db.rawQuery(sql ,null);


        // Read data Row by Row (cursor in one line)
        if (cursor.moveToFirst()) {
            do {
                // create nutritionists
                WeightTrainer weightTrainer = new WeightTrainer ();

                // Get one nutritionist details
                weightTrainer.id = cursor.getInt(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer._ID));
                weightTrainer.name = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME));
                weightTrainer.location = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_LOCATION));
                weightTrainer.email = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL));
                weightTrainer.contactNumber = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER));
                weightTrainer.about = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT));

                // add nut Nutritionist to list
                WeightTrainerList.add(weightTrainer);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return WeightTrainerList;
    }

    public WeightTrainer  getWeightTrainers(String Email) {
        //Get all date repository write mode
        SQLiteDatabase db = getReadableDatabase();

        //Get all date repository write mode
        String sql = "select * from weighttrainer where email=?";
        Cursor cursor = db.rawQuery(sql , new String[]{Email});


        WeightTrainer weightTrainer = new WeightTrainer ();

        // Read data, I simplify cursor in one line
        if (cursor.moveToFirst()) {

            // Get imageData in byte[]. Easy, right?
            weightTrainer.id = cursor.getInt(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer._ID));
            weightTrainer.name = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME));
            weightTrainer.location = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_LOCATION));
            weightTrainer.email = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL));
            weightTrainer.contactNumber = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER));
            weightTrainer.about = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT));

        }

        cursor.close();
        db.close();

        return weightTrainer;
    }



}

