package com.example.passwordmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PasswordManager.db";
    public static final String TABLE_NAME = "passwords";
    public static final String KEY_ID = "_id";
    public static final String KEY_SERVICE = "service";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_NOTES = "notes";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_SERVICE + " TEXT," +
                KEY_LOGIN + " TEXT," +
                KEY_PASSWORD + " TEXT," +
                KEY_NOTES + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertPassword(String service, String login, String password, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_SERVICE, service);
        contentValues.put(KEY_LOGIN, login);
        contentValues.put(KEY_PASSWORD, password);
        contentValues.put(KEY_NOTES, notes);
        db.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor getAllPasswords() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}
