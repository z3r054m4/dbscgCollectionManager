package com.application.dbscgcollectionmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.fragment.app.FragmentActivity;

import com.application.dbscgcollectionmanager.ui.search.SearchFragment;
import com.application.dbscgcollectionmanager.ui.search.SearchViewModel;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "user_cards.sqlite";
    private static final String TABLE = "collection";
    private static final Object FragmentActivity = null;
    private static String DB_PATH = "/data/data/com.application.dbscgcollectionmanager/databases/";
    private static final int DB_VERSION = 1;

    public static final String ID = "ID";
    public static final String CARD_ID = "IDCARD";
    public static final String CARD_NAME = "NAME";
    public static final String CARD_TYPE = "TYPE";
    public static final String CARD_RARITY = "RARITY";
    public static final String CARD_COLOR = "COLOR";
    public static final String QTY = "QTY";

    private SQLiteDatabase db;
    public Context context;
    Cursor c = null;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create the collection DB for the user. Same schema as data.
        db.execSQL("CREATE TABLE " + TABLE + " (" + ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE, " + CARD_ID + " TEXT NOT NULL UNIQUE, " + CARD_NAME + " TEXT, " + CARD_TYPE + " TEXT,  " + CARD_RARITY + " TEXT, " + CARD_COLOR + " TEXT, " + QTY + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public Cursor getAllCards(SQLiteDatabase sqldb){

        c = sqldb.rawQuery("SELECT * FROM " + TABLE, null);
        return c;
    }

    public Cursor searchById(String card) {
        db = this.getReadableDatabase();
        c = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_ID + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByRef(String card) {
        db = this.getReadableDatabase();
        c = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_ID + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByName(String card) {
        db = this.getReadableDatabase();
        c = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_NAME + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByType(String card) {
        db = this.getReadableDatabase();
        c = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_TYPE + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByRarity(String card) {
        db = this.getReadableDatabase();
        c = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_RARITY + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByColor(String card) {
        db = this.getReadableDatabase();
        c = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_COLOR + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public Cursor searchByQty(int num) {
        db = this.getReadableDatabase();
        c = db.rawQuery("SELECT * FROM " + TABLE + " WHERE " + QTY + " IS " + "'" + num + "';", null);
        return c;
    }

    //public long addCard(String cardId, String name, String type, String rarity, String color, int qty){
    public long addCard(){
        db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("IDCARD", "cardId");
        contentValues.put("NAME", "Idriss");
        contentValues.put("TYPE", "type");
        contentValues.put("RARITY", "rarity");
        contentValues.put("COLOR", "color");
        contentValues.put("QTY", "qty");

        long res = db.insert(TABLE, null, contentValues);
        db.close();
        return res;
    }
}



