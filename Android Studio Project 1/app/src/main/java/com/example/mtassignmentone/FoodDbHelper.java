package com.example.mtassignmentone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;

public class FoodDbHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "foods";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_IMAGE_RESOURCE = "image_resource";
    public static final String COLUMN_DATE = "date";

    public FoodDbHelper(FoodListActivity context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" +
                COLUMN_ID + " integer primary key, " +
                COLUMN_TITLE + " text, " +
                COLUMN_IMAGE_RESOURCE + " integer, " +
                COLUMN_DATE + " text)" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Food> getAllFoods() {
        ArrayList<Food> foodList = new ArrayList<Food>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            Food event = new Food(
                    res.getString(res.getColumnIndex(COLUMN_TITLE)),
                    res.getInt(res.getColumnIndex(COLUMN_IMAGE_RESOURCE)),
                    new Date(res.getString(res.getColumnIndex(COLUMN_DATE)) ));
            foodList.add(event);
            res.moveToNext();
        }
        return foodList;
    }

    public boolean insertFood(Food food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, food.getTitle());
        contentValues.put(COLUMN_IMAGE_RESOURCE, food.getImageResource());
        contentValues.put(COLUMN_DATE, food.getDateString());
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }

    public Integer deleteFood(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "id = ? ", new String[]{id});
    }

    public boolean updateEvent(String id, Food event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, event.getTitle());
        contentValues.put(COLUMN_IMAGE_RESOURCE, event.getImageResource());
        contentValues.put(COLUMN_DATE, event.getDateString());
        db.update(TABLE_NAME, contentValues, "id = ? ", new String[]{id});
        return true;
    }
}
