package com.smartFitness.home.DataBase;

import android.provider.BaseColumns;

public final class NutritionistMaster  {

    //constructor
    private NutritionistMaster () {}

    //Create Nutritionists Table Variables
    public static class Nutritionists implements BaseColumns {

        public static final String TABLE_NAME = "nutritionists";
        public static final String COLUMN_NAME_PHOTO = "photo";
        public static final String COLUMN_NAME_FIRSTNAME = "firstname";
        public static final String COLUMN_NAME_LASTNAME = "lastname";
        public static final String COLUMN_NAME_GENDER = "gender";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_MOBILENUMBER = "mobilenumber";

    }
}

