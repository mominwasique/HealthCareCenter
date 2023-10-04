package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 = "create table users(username text, email text, password text)";
        db.execSQL(qry1);

        String qry2 = "create table cart(username text,product text, price float,otype text)";
        db.execSQL(qry2);

        String qry3 = "create table orderplace(username text, fullname text,address text, contactno text,pincode int,date text,time text,amount float,otype text)";
        db.execSQL(qry3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, contentValues);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?", str);
        if (cursor.moveToFirst()) {
            result = 1;
        }
        return result;
    }

    public void addToCart(String username, String product, float price, String otype) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("product", product);
        contentValues.put("price", price);
        contentValues.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, contentValues);
        db.close();
    }

    public int checkCart(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from cart where username=? and product=?", str);
        if (cursor.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }

    public void removeCart(String username, String otype) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username=? and otype =?", str);
        db.close();

    }

    public ArrayList getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor cursor = db.rawQuery("select * from cart where username = ? and otype = ?", str);
        if (cursor.moveToFirst()) {
            do {
                String product = cursor.getString(1);
                String price = cursor.getString(2);
                arr.add(product + "$" + price);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contactno, String pincode, String date, String time, float price, String otype) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("fullname", fullname);
        contentValues.put("address", address);
        contentValues.put("contactno", contactno);
        contentValues.put("pincode", pincode);
        contentValues.put("date", date);
        contentValues.put("time", time);
        contentValues.put("amount", price);
        contentValues.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, contentValues);
        db.close();

    }

    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor cursor = db.rawQuery("select * from orderplace where username = ?", str);
        if (cursor.moveToFirst()) {
            do {
                arr.add(cursor.getString(1) + "$" + cursor.getString(2) + "$" + cursor.getString(3) + "$"
                        + cursor.getString(4) + "$" + cursor.getString(5) + "$" + cursor.getString(6) + "$"
                        + cursor.getString(7) + "$" + cursor.getString(8));
            } while (cursor.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkAppointmentExist(String username, String full_name, String address, String date, String time) {
        int result = 0;
        String str[] = new String[5];
        str[0] = username;
        str[1] = full_name;
        str[2] = address;
        str[3] = date;
        str[4] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from orderplace where username = ? and full_name = ? and address = ? and date = ? and time = ?", str);
        if (cursor.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }
}
