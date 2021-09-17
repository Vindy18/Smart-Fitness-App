package com.smartFitness.home.DataBase;

import android.provider.BaseColumns;

public final class AdminMaster {

    //constructor
    private AdminMaster () {}

    //Create Admin Table Variables
    public static class Admins implements BaseColumns{

        public static final String TABLE_NAME = "admins";
        public static final String COLUMN_NAME_FIRSTNAME = "firstname";
        public static final String COLUMN_NAME_LASTNAME = "lastname";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_MOBILENUMBER = "mobilenumber";
        public static final String COLUMN_NAME_PASSWORD = "Password";

    }

}
