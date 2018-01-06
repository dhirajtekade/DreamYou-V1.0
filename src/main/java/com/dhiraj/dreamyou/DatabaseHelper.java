package com.dhiraj.dreamyou;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dhiraj on 12/19/2017.
 */

public class DatabaseHelper  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dreamyou15.db";

    public static final String CT_TABLE= "cattype_table";
        public static final String CT_ID = "id";
        public static final String CT_TYPE = "cat_type";
       // public static final String CT_NAME = "name";

    public static final String C_TABLE= "categories_table";
        public static final String C_ID = "id";
        public static final String C_NAME = "cat_name";
        public static final String C_TYPE = "cat_type";
        public static final String C_CREATE = "cat_created";
        public static final String C_UPDATE = "cat_updated";
        public static final String C_POINT = "cat_point";
        public static final String C_DESC = "cat_desc";

    public static final String TC_TABLE= "todaycat_table";
        public static final String TC_ID = "id";
        public static final String TC_DATE = "date";
        public static final String TC_CATID = "cat_id";


    public static final String S_TABLE= "summary_table";
        public static final String S_ID = "id";
        public static final String S_DATE = "date"; //with timestamp
        public static final String S_TODAYSCORE = "today_score";
        public static final String S_FINALSCORE = "final_score";
        public static final String S_DATEVALUE = "date_only";



   /* public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }*/
    //whenever below constructor will be called db will be created
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+CT_TABLE+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+CT_TYPE+" TEXT) ");
        db.execSQL("create table "+C_TABLE+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+C_NAME+" TEXT, "+C_TYPE+" INTEGER, "+C_CREATE+" INTEGER, "+C_UPDATE+" INTEGER, "+C_POINT+" INTEGER, "+C_DESC+" TEXT) ");
        db.execSQL("create table "+TC_TABLE+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+TC_DATE+" INTEGER, "+TC_CATID+" INTEGER) ");
        db.execSQL("create table "+S_TABLE+ " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+S_DATE+" INTEGER, "+S_TODAYSCORE+" INTEGER, "+S_FINALSCORE+" INTEGER, "+S_DATEVALUE+" INTEGER) ");

        //add default parent category types
        db.execSQL("INSERT or replace INTO "+CT_TABLE+" ("+CT_TYPE+") VALUES('Spiritual')");
        db.execSQL("INSERT or replace INTO "+CT_TABLE+" ("+CT_TYPE+") VALUES('Fitness')");
       // insertParentCategory("Spiritual");
       // insertParentCategory("Fitness");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Charanwidhi','1','2000')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Sthuldosh2-Dhrashti','1','-5000')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Satsang','1','1500')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('MBA','1','2500')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Visit','1','2000')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Aarti','1','500')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Samyakdrishti','1','500')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Sthuldosh1-Sparsh','1','-15000')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Dhrashtidosh','1','100')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Ratridosh','1','-500')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Earlywaking','2','50')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Yoga/Exercise','2','100')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Jogging','2','100')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('NischayShakti','1','100')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Pratikraman','1','100')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Swadyaya','1','500')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Samayik(15+ mins)','1','500')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Samayik(40+ mins)','1','1000')");
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+","+C_TYPE+","+C_POINT+") VALUES('Satsang Video(5+ mins)','1','100')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TC_TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+S_TABLE);
        onCreate(db);
    }

    public int countentrytemp() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Toast.makeText(FourthActivity.this, "tablecount countrecord", Toast.LENGTH_SHORT).show();
        String sql = "SELECT * FROM "+TC_TABLE+"";
        int recordCount = db.rawQuery(sql, null).getCount();
        //db.close();

        return recordCount;

    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();
        // Toast.makeText(FourthActivity.this, "tablecount countrecord", Toast.LENGTH_SHORT).show();
            String sql = "SELECT * FROM "+C_TABLE+"";
        int recordCount = db.rawQuery(sql, null).getCount();
        //db.close();

        return recordCount;
    }
    //public boolean insertNewCategory(String cat_name,Integer cat_type,Integer cat_created,Integer cat_point,String cat_desc) {
    public boolean insertNewCategory(String cat_name) {
        //Toast.makeText(DatabaseHelper.this, "tablecount countrecord", Toast.LENGTH_SHORT).show();

      /*  SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT or replace INTO "+C_TABLE+" ("+C_NAME+") VALUES(cat_name)");*/
        /*SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(C_NAME, cat_name);contentValues.put(C_TYPE, cat_type);
        contentValues.put(C_CREATE, cat_created);contentValues.put(C_POINT, cat_point);
        contentValues.put(C_DESC, cat_desc);
        long result = db.insert(C_TABLE, null, contentValues);
*/
        if(1 == -1) { return false; } else { return true;}
    }

    public boolean create(ObjectCategory objectCategory) {

        ContentValues values = new ContentValues();

        values.put("cat_name", objectCategory.cat_name);
        values.put("cat_type", objectCategory.cat_type);
       values.put("cat_point", objectCategory.cat_point);
         values.put("cat_desc", objectCategory.cat_desc);
        //values.put("cat_created", 1513946922);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("categories_table", null, values) > 0;
        db.close();

        return createSuccessful;
    }
/*
public String getCategoryListForCheckbox() {

}*/


    public boolean insertParentCategory(String category_name) {

        SQLiteDatabase ct_db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CT_TYPE, category_name);
        long result = ct_db.insert(CT_TABLE, null, contentValues);
        if(result == -1) { return false; } else { return true;}
    }

    public boolean insertDataTodaycat_table(Integer Todaycat_tableTimestamp, Integer cat_id) {
        /*SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(TC_DATE, Todaycat_tableTimestamp);contentValues.put(TC_CATID, cat_id);

        long result = db.insert(TC_TABLE, null, contentValues);

        if(result == -1) { return false; } else { return true;}*/
        return true;
    }

    public Cursor getCat_InfoFromCat_id(Integer cat_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM "+C_TABLE+" WHERE "+C_ID+"="+cat_id;
        Cursor catInfoValues = db.rawQuery(sql, null);
        //db.close();

        return catInfoValues;
    }

    public Cursor getSummaryTableData() {
        SQLiteDatabase db = this.getWritableDatabase();
        //  Cursor res = db.rawQuery("SELECT * FROM "+SUM_TABLE_NAME+" WHERE ID = (SELECT MAX(ID)  FROM "+SUM_TABLE_NAME+ ")", null);
        Cursor res = db.rawQuery("select * from "+S_TABLE+" group BY (date) ORDER BY id desc LIMIT 1", null);
        return res;
    }

    public Cursor getHighestFinalScore() {
        SQLiteDatabase db = this.getWritableDatabase();
        //  Cursor res = db.rawQuery("SELECT MAX(Finalscore) FROM "+SUM_TABLE_NAME, null);
        Cursor res = db.rawQuery("SELECT SUM("+S_FINALSCORE+") AS hfs FROM  "+S_TABLE+" GROUP BY ("+S_DATEVALUE+") ORDER BY hfs DESC LIMIT 1", null);
        return res;
    }

    public Cursor getLowestFinalScore() {
        SQLiteDatabase db = this.getWritableDatabase();
        //  Cursor res = db.rawQuery("SELECT MIN(Finalscore) FROM "+SUM_TABLE_NAME, null);
        Cursor res = db.rawQuery("SELECT SUM("+S_FINALSCORE+") AS hfs FROM  "+S_TABLE+" GROUP BY ("+S_DATEVALUE+") ORDER BY hfs ASC LIMIT 1", null);
        return res;
    }

    public Cursor getHighestTodayScore() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cursor res = db.rawQuery("SELECT MAX(todayscore) FROM "+SUM_TABLE_NAME, null);
        Cursor res = db.rawQuery("SELECT SUM("+S_TODAYSCORE+") AS hts FROM  "+S_TABLE+" GROUP BY ("+S_DATEVALUE+") ORDER BY hts DESC LIMIT 1", null);
        return res;
    }


    public boolean summaryInsertData(Long date, Integer todayscore, Integer finalscore, Integer currentDateString) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(S_DATE, date);contentValues.put(S_TODAYSCORE, todayscore);
        contentValues.put(S_FINALSCORE, finalscore);contentValues.put(S_DATEVALUE, currentDateString);
        long result = db.insert(S_TABLE, null, contentValues);
        if(result == -1) { return false; } else { return true;}
    }

    public Cursor getListSummaryTableData() {
        SQLiteDatabase db = this.getWritableDatabase();
        //  Cursor res = db.rawQuery("SELECT * FROM "+SUM_TABLE_NAME+" WHERE ID = (SELECT MAX(ID)  FROM "+SUM_TABLE_NAME+ ")", null);
        Cursor res = db.rawQuery("select * from "+S_TABLE+" group BY ("+S_DATEVALUE+") ORDER BY date", null);
        return res;
    }
/*
    public Cursor countSummaryRecord() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+S_TABLE+ " GROUP BY (date)", null);
        return res;
    }*/



    public List<ObjectSummary> readSummary() {

        List<ObjectSummary> recordsList = new ArrayList<ObjectSummary>();

        // String sql = "SELECT ID, todaycatid, date, SUM(todayscore) AS todayscore, SUM(Finalscore) AS Finalscore  FROM "+SUM_TABLE_NAME+ " GROUP BY (date) ORDER BY date";
        String sql = "SELECT *  FROM "+S_TABLE+ "  ORDER BY date";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                long summaryDate = Long.parseLong(cursor.getString(cursor.getColumnIndex("date")));
                int summaryTodayscore = Integer.parseInt(cursor.getString(cursor.getColumnIndex("today_score")));
                int summaryFinalscore = Integer.parseInt(cursor.getString(cursor.getColumnIndex("final_score")));
                int summaryDateonly = Integer.parseInt(cursor.getString(cursor.getColumnIndex("date_only")));

                ObjectSummary objectSummary = new ObjectSummary();
                objectSummary.id = id;
                objectSummary.date = summaryDate;
                objectSummary.today_score = summaryTodayscore;
                objectSummary.final_score = summaryFinalscore;
                objectSummary.date_only = summaryDateonly;

                recordsList.add(objectSummary);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }


    public Cursor getAllSummaryDayWise() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT "+S_DATE+",SUM("+S_FINALSCORE+") AS tfs,SUM("+S_TODAYSCORE+") AS tts, "+S_DATEVALUE+" FROM "+S_TABLE+" GROUP BY ("+S_DATEVALUE+") ORDER BY "+S_DATE, null);
        return res;
    }

}
