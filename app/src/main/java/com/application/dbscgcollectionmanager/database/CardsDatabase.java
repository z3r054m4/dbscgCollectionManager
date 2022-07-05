package com.application.dbscgcollectionmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CardsDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "dbscg_cards.sqlite";
    private static final String TABLE = "game_cards";
    private static String DB_PATH = "/data/data/com.application.dbscgcollectionmanager/databases/";
    private static final int DB_VERSION = 1;

    public static final String ID = "ID";
    public static final String CARD_ID = "IDCARD";
    public static final String CARD_NAME = "NAME";
    public static final String CARD_TYPE = "TYPE";
    public static final String CARD_RARITY = "RARITY";
    public static final String CARD_COLOR = "COLOR";
    public static final String QTY = "QTY";

    private SQLiteDatabase cdb;
    public Context context;
    Cursor c = null;

    public CardsDatabase(Context context) {
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

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public Cursor getAll(SQLiteDatabase sqldb){
        c = sqldb.rawQuery("SELECT * FROM " + TABLE, null);
        return c;
    }

    public Cursor searchById(String card) {
        cdb = this.getReadableDatabase();
        c = cdb.rawQuery("SELECT * FROM " + TABLE + " WHERE " + CARD_ID + " LIKE " + "'%" + card + "%';", null);
        return c;
    }

    public long addCard(){
        //public long addCard(){
        cdb = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("IDCARD", "T-001");
        contentValues.put("NAME", "cards");
        contentValues.put("TYPE", "type");
        contentValues.put("RARITY", "rarity");
        contentValues.put("COLOR", "color");
        contentValues.put("QTY", 0);
        long res = cdb.insert(TABLE, null, contentValues);

        cdb.close();
        return res;
    }

    //TODO: populate card DB
    public void copyFromAsset() throws IOException {
        cdb = this.getWritableDatabase();

        //Inputs
        InputStream myinput = context.getAssets().open(DB_NAME);

        //Outputs
        String output = DB_PATH + DB_NAME;
        OutputStream myoutput = new FileOutputStream(output);

        //Copy bytes
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();

        cdb.close();
    }
}
