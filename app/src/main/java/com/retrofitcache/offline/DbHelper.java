/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.retrofitcache.offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.Map;

/**
 * Created by cenktuzun on 16/09/14.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "retrofitcache.db";
    private SQLiteDatabase sqLiteDatabase;

    protected DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
        createTableIfNotExist();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(CacheEntry.SQL_DROP_CACHE_TABLE);
        createTableIfNotExist();
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public void createTableIfNotExist() {
        Cursor cursor = getDatabase().rawQuery(CacheEntry.SQL_CONTROL_TABLE, null);
        if (cursor.moveToFirst()) {
            return;
        }
        cursor.close();
        getDatabase().execSQL(CacheEntry.SQL_CREATE_CACHE_TABLE);
    }

    private ContentValues convertMapToContentValues(Map<String, String> params) {
        ContentValues contentValues = new ContentValues();
        for (String key : params.keySet()) {
            contentValues.put(key, params.get(key));
        }
        return contentValues;
    }


    protected Cursor read(String response) {
        return getDatabase().query(CacheEntry.TABLE_NAME,
                null, CacheEntry.COLUMN_RESPONSE + " = '" + response + "'", null,
                null, null, null);
    }


    protected void insert(Map<String, String> contentValues) {
        getDatabase().insert(CacheEntry.TABLE_NAME, null, convertMapToContentValues(contentValues));
    }

    private SQLiteDatabase getDatabase() {
        if (sqLiteDatabase == null) {
            sqLiteDatabase = getReadableDatabase();
        }
        return sqLiteDatabase;
    }

    public static final class CacheEntry implements BaseColumns {
        // Table name
        public static final String TABLE_NAME = "cache";
        // Columns
        public static final String COLUMN_CONTENT = "content";
        public static final String COLUMN_RESPONSE = "response";
        public static final String COLUMN_REQUEST = "request";
        // SQL commands
        private static final String SQL_CONTROL_TABLE = "SELECT name FROM sqlite_master WHERE type='table' AND name= '" + TABLE_NAME + "';";
        private static final String SQL_DROP_CACHE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private static final String SQL_CREATE_CACHE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                CacheEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CacheEntry.COLUMN_RESPONSE + " TEXT NOT NULL," +
                CacheEntry.COLUMN_CONTENT + " TEXT NOT NULL," +
                CacheEntry.COLUMN_REQUEST + " TEXT ,UNIQUE ( " + CacheEntry.COLUMN_RESPONSE + " ) ON CONFLICT REPLACE );";
    }

}
