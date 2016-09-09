package com.example.phantom.onlineshop.database;


import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = ModelDatabase.NAME, version = ModelDatabase.VERSION, foreignKeysSupported = true)
public class ModelDatabase {
    public static final String NAME = "Offers";
    public static final int VERSION = 1;
}