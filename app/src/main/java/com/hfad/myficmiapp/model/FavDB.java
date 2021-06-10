package com.hfad.myficmiapp.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FavDB extends SQLiteOpenHelper {
    private static int DB_VERSION = 1;
    private static String DATABASE_NAME = "MessagesDB";
    private static String TABLE_NAME = "FavouritesTable";
    public static String KEY_ID = "id";
    public static String ITEM_TOPIC = "itemTopic";
    public static String ITEM_SPEAKER = "itemSpeaker";
    public static String FAVORITE_STATUS = "fStatus";

    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + KEY_ID + "TEXT," + ITEM_TOPIC + "TEXT,"
            + ITEM_SPEAKER + "TEXT," + FAVORITE_STATUS + "TEXT)";

    public FavDB (Context context){
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    //CREATE EMPTY TABLE
    public void insertEmpty(){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //enter ur values
        for (int i = 1; i < 11;i++){
            cv.put(KEY_ID,i);
            cv.put(FAVORITE_STATUS, "0");

            db.insert(TABLE_NAME,null,cv);
        }
    }
    //insert data into database
    public void insertIntoTheDatabase(String item_topic, String item_speaker, String id, String fav_status){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEM_TOPIC , item_topic);
        cv.put(ITEM_SPEAKER, item_speaker);
        cv.put(KEY_ID, id);
        cv.put(FAVORITE_STATUS, fav_status);
        db.insert(TABLE_NAME, null, cv);
        Log.d("FavDb Status", item_topic + ". favstatus -" + fav_status + "= ." + cv );
    }
    public Cursor readAllData(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where " + KEY_ID + " = " + id + "";
        return db.rawQuery(sql, null, null);
    }
    //remove line from database
    public void removeFav(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE "+ TABLE_NAME + " SET " + FAVORITE_STATUS + " = '0' where "+ KEY_ID + "="+ id + " ";
        db.execSQL(sql);
        Log.d("remove", id.toString());
    }

    //select all favourite list
    public Cursor select_all_favorite_list(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select * from " + TABLE_NAME + " where "+ FAVORITE_STATUS+ " = '1' ";
        return db.rawQuery(sql, null, null);

    }
}
