package com.dhiraj.dreamyou;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dhiraj on 12/22/2017.
 */

public class TableControllerCategory extends DatabaseHelper {

    public TableControllerCategory(Context context) {
        super(context);
    }


    public boolean addTodaycat(Long Todaycat_tableTimestamp, Integer cat_id) {

        ContentValues values = new ContentValues();

        values.put("date", Todaycat_tableTimestamp);
        values.put("cat_id", cat_id);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean addedSuccessful = db.insert("todaycat_table", null, values) > 0;
        db.close();

        return addedSuccessful;
    }


    public boolean create(ObjectCategory objectCategory) {

     //   DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
      //  Date currentDayTime = Calendar.getInstance().getTime();
         Integer CategoryCreated =  1513946911;//Integer.parseInt(df.format(currentDayTime));



        ContentValues values = new ContentValues();

        values.put("cat_name", objectCategory.cat_name);
        values.put("cat_type", objectCategory.cat_type);
       values.put("cat_point", objectCategory.cat_point);
        values.put("cat_desc", objectCategory.cat_desc);
      //  values.put("cat_created", 1513946911);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("categories_table", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();
       // Toast.makeText(FourthActivity.this, "tablecount countrecord", Toast.LENGTH_SHORT).show();
    //    String sql = "SELECT * FROM categories_table";
        int recordCount = 6;//db.rawQuery(sql, null).getCount();
        //db.close();

        return recordCount;

    }

    public int geCount() {
        SQLiteDatabase db = this.getWritableDatabase();
        //  Cursor res = db.rawQuery("SELECT * FROM "+SUM_TABLE_NAME+" WHERE ID = (SELECT MAX(ID)  FROM "+SUM_TABLE_NAME+ ")", null);
        Cursor res = db.rawQuery("SELECT * FROM categories_table", null);
        int recordCount = res.getCount();
        return recordCount;
    }






    public List<ObjectCategory> read() {

        List<ObjectCategory> recordsList = new ArrayList<ObjectCategory>();

        String sql = "SELECT * FROM categories_table ORDER BY cat_name";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String categoryName = cursor.getString(cursor.getColumnIndex("cat_name"));
               // int categoryType = Integer.parseInt(cursor.getString(cursor.getColumnIndex("cat_type")));
                int categoryPoint = Integer.parseInt(cursor.getString(cursor.getColumnIndex("cat_point")));

                ObjectCategory objectCategory = new ObjectCategory();
                objectCategory.id = id;
                objectCategory.cat_name = categoryName;
              //  objectCategory.cat_type = categoryType;
                objectCategory.cat_point = categoryPoint;

                recordsList.add(objectCategory);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    public ObjectCategory readSingleRecord(int categoryId) {

        ObjectCategory objectCategory = null;

        String sql = "SELECT * FROM categories_table WHERE id = " + categoryId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String categoryName = cursor.getString(cursor.getColumnIndex("cat_name"));

            // int categoryType = Integer.parseInt(cursor.getString(cursor.getColumnIndex("cat_type")));
            //  int categoryPoint = Integer.parseInt(cursor.getString(cursor.getColumnIndex("cat_point")));

            objectCategory = new ObjectCategory();
            objectCategory.id = id;
            objectCategory.cat_name = categoryName;
            //objectStudent.email = email;

        }

        cursor.close();
        db.close();

        return objectCategory;

    }



    public boolean update(ObjectCategory objectCategory) {

        ContentValues values = new ContentValues();

        values.put("cat_name", objectCategory.cat_name);
      //  values.put("cat_type", objectCategory.cat_type);

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(objectCategory.id) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("categories_table", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;

    }

    public boolean delete(int id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("categories_table", "id ='" + id + "'", null) > 0;
        db.close();

        return deleteSuccessful;

    }

}
