package com.example.u5778016.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by u5721067 on 20/04/16.
 */
public class HistoryDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "history_db";
    private static final String HIS_TABLE_NAME = "history";
    private static final String HIS_ID = "_id";
    private static final String HIS_FORMULA = "title";
    private static final String HIS_RESULT = "content";
    private static final String HIS_CREATED = "created";

    private static final String[] HIS_PROJECTION = new String[] {HIS_ID, HIS_FORMULA, HIS_RESULT, HIS_CREATED};

    public HistoryDB (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + HIS_TABLE_NAME + " (" +
                HIS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + HIS_FORMULA +
                " TEXT," + HIS_RESULT + " TEXT," + HIS_CREATED + " INTEGER" + ");";
        db.execSQL(createNoteTableSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + HIS_TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    // Add new history
    public int addHis(History history) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(HIS_FORMULA, history.getFormula());
        values.put(HIS_RESULT, history.getResult());
        values.put(HIS_CREATED, history.getCreated());
        // Insert to database
        int Ids = (int) db.insert(HIS_TABLE_NAME, null, values);
        // Close the database
        db.close();
        return Ids;
    }

    // Get one history
    public History getHis(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(HIS_TABLE_NAME, HIS_PROJECTION, HIS_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        History history = new History(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
        return history;
    }

    // Get all history
    public Cursor getAllHisCursor() {
        String selectQuery = "SELECT * FROM " + HIS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
}
