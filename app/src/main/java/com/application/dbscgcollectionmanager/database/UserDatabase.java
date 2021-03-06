package com.application.dbscgcollectionmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabase extends SQLiteOpenHelper{

    private static final String DB_NAME = "user_collection.sqlite";
    private static final String TABLE = "user_cards";
    private static String DB_PATH = "/data/data/com.application.dbscgcollectionmanager/databases/";
    private static final int DB_VERSION = 1;

    public static final String ID = "ID";
    public static final String CARD_ID = "IDCARD";
    public static final String CARD_NAME = "NAME";
    public static final String CARD_TYPE = "TYPE";
    public static final String CARD_RARITY = "RARITY";
    public static final String CARD_COLOR = "COLOR";
    public static final String QTY = "QTY";

    private SQLiteDatabase udb;
    public Context context;
    Cursor c = null;

    public UserDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create the collection DB for the user. Same schema as data.
        db.execSQL(
                "CREATE TABLE " +
                        TABLE + " (" +
                        ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " +
                        CARD_ID + " TEXT NOT NULL UNIQUE, " +
                        CARD_NAME + " TEXT, " +
                        CARD_TYPE + " TEXT,  " +
                        CARD_RARITY + " TEXT, " +
                        CARD_COLOR + " TEXT, " +
                        QTY + " INTEGER)"
        );
    }

    public int getVersion(){

        return DB_VERSION ;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public Cursor getAll(SQLiteDatabase sqldb){
        c = sqldb.rawQuery("SELECT * FROM " + TABLE, null);
        return c;
    }

    public Cursor searchById(String card) {
        udb = this.getReadableDatabase();
        c = udb.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_ID + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByRef(String card) {
        udb = this.getReadableDatabase();
        c = udb.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_ID + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByName(String card) {
        udb = this.getReadableDatabase();
        c = udb.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_NAME + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByType(String card) {
        udb = this.getReadableDatabase();
        c = udb.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_TYPE + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByRarity(String card) {
        udb = this.getReadableDatabase();
        c = udb.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_RARITY + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByColor(String card) {
        udb = this.getReadableDatabase();
        c = udb.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_COLOR + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByQty(int num) {
        udb = this.getReadableDatabase();
        c = udb.rawQuery("SELECT * FROM " + TABLE + " WHERE " + QTY + " IS " + "'" + num + "';", null);
        return c;
    }

    public long addCard(String cardId, String name, String type, String rarity, String color, int qty){
    //public long addCard(){
        udb = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("IDCARD", cardId);
        contentValues.put("NAME", name);
        contentValues.put("TYPE", type);
        contentValues.put("RARITY", rarity);
        contentValues.put("COLOR", color);
        contentValues.put("QTY", qty);
        long res = udb.insert(TABLE, null, contentValues);

        udb.close();
        return res;
    }
}



