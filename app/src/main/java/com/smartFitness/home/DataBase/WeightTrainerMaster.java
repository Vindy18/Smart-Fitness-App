package com.smartFitness.home.DataBase;

import android.provider.BaseColumns;

public final class WeightTrainerMaster {



    //constructor
    private WeightTrainerMaster() {
    }

    //Create WeightTrainer Table Variables
    public static class WeightTrainer implements BaseColumns {

        public static final String TABLE_NAME = "weighttrainer";
        public static final String COLUMN_NAME_PHOTO = "photo";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_LOCATION = "location";
        public static final String COLUMN_NAME_CONTACTNUMBER = "contactnumber";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_ABOUT = "about";


        }
    }

