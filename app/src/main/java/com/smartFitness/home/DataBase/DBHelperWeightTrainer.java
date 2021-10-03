package com.smartFitness.home.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.EditText;

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
    public int updateWeightTrainer(EditText name, EditText address, EditText contactnumber, EditText workinghours, EditText about){
        SQLiteDatabase db= getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_NAME, String.valueOf(name));
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ADDRESS, String.valueOf(address));
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_CONTACTNUMBER, String.valueOf(contactnumber));
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_WORKINGHOURS, String.valueOf(workinghours));
        values.put(WeightTrainerMaster.WeightTrainer.COLUMN_NAME_ABOUT, String.valueOf(about));

        String sql = WeightTrainerMaster.WeightTrainer._ID+ " LIKE ?";
        String[]  selectionArgs = {};
        int count = db.update(WeightTrainerMaster.WeightTrainer.TABLE_NAME,values,sql ,selectionArgs);

       return count;
    }
    public void deleteWeightTrainer(String ID) {
        SQLiteDatabase db= getReadableDatabase();
        //deleting weightTrainer
        String sql = WeightTrainerMaster.WeightTrainer._ID + " LIKE ?";
        String[] selectionArgs = {ID};
        db.delete(WeightTrainerMaster.WeightTrainer.TABLE_NAME, sql ,selectionArgs );

    }



}

