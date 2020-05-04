package com.team16.ecoffee;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

public class MyDBHandler extends SQLiteOpenHelper {
    //Σταθερές για τη ΒΔ (όνομα ΒΔ, έκδοση, πίνακες κλπ)
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Users.db";
    public static final String TABLE_PRODUCTS = "data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PRODUCTNAME = "name";
    public static final String COLUMN_QUANTITY = "pass";
    public static final String COLUMN_CARD = "card";
    public static final String COLUMN_EXPIRYDATE = "excard";



    //Constructor
    public MyDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Δημιουργία του σχήματος της ΒΔ (πίνακας products)
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_PRODUCTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRODUCTNAME + " TEXT," +
                COLUMN_QUANTITY + " TEXT,"+ COLUMN_CARD + " TEXT," +
                COLUMN_EXPIRYDATE + " TEXT"+ ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);
    }

    //Αναβάθμιση ΒΔ: εδώ τη διαγραφώ και τη ξαναδημιουργώ ίδια
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    //Μέθοδος για προσθήκη ενός προιόντος στη ΒΔ
    public void addUser(Data product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCTNAME, product.getDataName());
        values.put(COLUMN_QUANTITY, product.getPass());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    public void addCard(Data product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CARD, product.getCard());
        values.put(COLUMN_EXPIRYDATE, product.getExcard());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PRODUCTS, null, values);
        db.close();
    }

    //Μέθοδος για εύρεση προιόντος βάσει ονομασίας του
    public Data findUser(String productname) {
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCTNAME + " = '" + productname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Data product = new Data();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            product.setID(Integer.parseInt(cursor.getString(0)));
            product.setDataName(cursor.getString(1));
            product.setPass(cursor.getString(2));
            product.setCard(cursor.getString(3));
            product.setExcard(cursor.getString(4));

            cursor.close();
        } else {
            product = null;
        }
        db.close();
        return product;
    }

    //Μέθοδος για διαγραφή προιόντος βάσει ονομασίας του
    public boolean deleteUser(String productname) {
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_PRODUCTS + " WHERE " +
                COLUMN_PRODUCTNAME + " = '" + productname + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Data product = new Data();
        if (cursor.moveToFirst()) {
            product.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_PRODUCTS, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(product.getID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


    public String[] returnUsers() {
        String query = "SELECT " + COLUMN_PRODUCTNAME + " FROM " + TABLE_PRODUCTS ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        String[] users = new String[cursor.getCount()];
        for(int i = 0; i < users.length; i++){
            cursor.moveToNext();
            users[i] = cursor.getString(1);
        }
        db.close();
        return users;
    }

}