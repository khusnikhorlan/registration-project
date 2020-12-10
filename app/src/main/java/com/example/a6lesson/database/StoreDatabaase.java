package com.example.a6lesson.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreDatabaase  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "lessonDatabase.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_USERS = "Users";

    public static final String COLOUMN_USER_NAME = "username";
    public static final String COLOUMN_USER_EMAIL = "user_email";
    public static final String COLOUMN_USER_PASSWORD = "user_password";

    Context context;

    public StoreDatabaase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE_USERS + "("+
                COLOUMN_USER_NAME + "TEXT, " +
                COLOUMN_USER_EMAIL + "TEXT, " +
                COLOUMN_USER_PASSWORD + "TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        onCreate(db);
    }
}
