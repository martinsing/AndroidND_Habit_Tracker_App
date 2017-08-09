package com.vegetablecorp.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vegetablecorp.habittracker.HabitContract.HabitEntry;

public class HabitDatabase extends SQLiteOpenHelper {

    private HabitDatabase mHelper;

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "habits.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link HabitDatabase}.
     */
    public HabitDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * There exists a subclass of SQLiteOpenHelper that overrides onCreate() and onUpgrade().
     */

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the items table
        String SQL_CREATE_TABLE = "CREATE TABLE " + HabitEntry.TABLE_NAME + " ("
                + HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitEntry.COLUMN_EXERCISE + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_BRUSHTEETH + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_MEDICATION + " INTEGER NOT NULL DEFAULT 0, "
                + HabitEntry.COLUMN_FEELING + " STRING NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

    /**
     * All data insertion and reading should be done using direct method calls to the SQLite
     * database in the SQLiteOpenHelper class.
     */

    /**
     * There is a single read method that returns a Cursor object. It should get the data repository
     * in read and use the query() method to retrieve at least one column of data.
     */

    public Cursor read() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                HabitEntry.COLUMN_EXERCISE,
                HabitEntry.COLUMN_BRUSHTEETH,
                HabitEntry.COLUMN_MEDICATION,
                HabitEntry.COLUMN_FEELING,
        };

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        return cursor;
    }

    /**
     * Single insert method that adds at least two values of two different datatypes
     * e.g. INTEGER, STRING) into the database using a ContentValues object and the insert()
     * method.
     */
    public void insert(int exercise, int brushteeth, int medication, String feeling) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_EXERCISE, exercise);
        values.put(HabitEntry.COLUMN_BRUSHTEETH, brushteeth);
        values.put(HabitEntry.COLUMN_MEDICATION, medication);
        values.put(HabitEntry.COLUMN_FEELING, feeling);
        db.insert(HabitEntry.TABLE_NAME, null, values);
    }
}
