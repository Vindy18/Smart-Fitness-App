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

    //Assign Database Name
    public static final String DATABASE_NAME = "SmartFitness.db";

    //Create Database
    public DBHelperWeightTrainer(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create table query
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + WeightTrainerMaster.WeightTrainer.TABLE_NAME + " (" +
                WeightTrainerMaster.WeightTrainer._ID + " INTEGER PRIMARY KEY," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_LOCATION + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL  + " TEXT NOT NULL UNIQUE," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER + " TEXT NOT NULL," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT + " TEXT," +
                WeightTrainerMaster.WeightTrainer.COLUMN_NAME_PHOTO + " BLOB )";

        //Create above table
        db.execSQL(SQL_CREATE_ENTRIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add Weight Trainer
    public boolean addWeightTrainer(String name, String location, String contactnumber, String email, String about) {

        //Get all data repository write mode
        SQLiteDatabase db = getWritableDatabase();

        //declare "content values"
        ContentValues values = new ContentValues();

        //Assign all the parameters to "values" variables
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME, name);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_LOCATION, location);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER, contactnumber);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL, email);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT, about);

        //insert(values)query to database
        long newRowId = (db.insert(WeightTrainerMaster.WeightTrainer.TABLE_NAME, null, values));

        //Check "newRowId" if success return greater than 0 value
        if (newRowId > 0) {
            return true;
        } else {
            return false;
        }

    }

    //update Weight Trainer method
    public int updateWeightTrainer(String keyEmail, String name, String location, String email,String contactnumber,String about){

        //Get all date repository readable mode
        SQLiteDatabase db= getReadableDatabase();

        // declare "content values" object
        ContentValues values = new ContentValues();

        // Assign all the parameter to "values" variable
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME, name);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_LOCATION, location);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER, contactnumber);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL, email);
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT, about);

        //sql query
        String sql = WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL + " LIKE ?";
        String[]  selectionArgs = {keyEmail};

        //update and return the row count
        int count = db.update(WeightTrainerMaster.WeightTrainer.TABLE_NAME,values, sql,selectionArgs);

        return count;
    }


    //deleting WeightTrainer method
    public void deleteWeightTrainer(String Email) {

        //Get all date repository readable mode
        SQLiteDatabase db= getReadableDatabase();

        // Delete query
        String sql = WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL + " LIKE ?";
        String[] selectionArgs = {Email};
        db.delete(WeightTrainerMaster.WeightTrainer.TABLE_NAME, sql ,selectionArgs );
    }

    //Get all Weight Trainers
    public List<WeightTrainer> getAllWeightTrainers() {

        //Get all date repository readable mode
        SQLiteDatabase db = getReadableDatabase();

        //declare an array list object
        List WeightTrainerList = new ArrayList();

        //SQl query for get all weight trainer
        String sql = "select * from weighttrainer";

        //Get row details to cursor object
        Cursor cursor = db.rawQuery(sql ,null);


        //Read data Row by Row (cursor in one line)
        if (cursor.moveToFirst()) {
            do {
                //create Weight Trainer
                WeightTrainer weightTrainer = new WeightTrainer ();

                // Get one Weight Trainer details
                weightTrainer.id = cursor.getInt(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer._ID));
                weightTrainer.name = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME));
                weightTrainer.location = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_LOCATION));
                weightTrainer.email = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_EMAIL));
                weightTrainer.contactNumber = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER));
                weightTrainer.about = cursor.getString(cursor.getColumnIndex(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT));

                // add Weight Trainer to list
                WeightTrainerList.add(weightTrainer);

            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return WeightTrainerList;
    }

    //Get Weight Trainer method
    public WeightTrainer  getWeightTrainers(String Email) {

        //Get all date repository readable mode
        SQLiteDatabase db = getReadableDatabase();

        //Get Weight Trainer query
        String sql = "select * from weighttrainer where email=?";

        //Get row details to cursor object
        Cursor cursor = db.rawQuery(sql , new String[]{Email});

        //create Weight Trainer object
        WeightTrainer weightTrainer = new WeightTrainer ();

        //Read data fist match row details
        if (cursor.moveToFirst()) {

            //Get one Weight Trainer details
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

