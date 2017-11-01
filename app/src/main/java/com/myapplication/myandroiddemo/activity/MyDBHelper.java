package com.myapplication.myandroiddemo.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2016/7/22.
 */
public class MyDBHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final String DB_NAME = "db_test";
    private static final int DB_VERSION = 2;
    private String table_name = "table_test";
    private String[] columns = new String[]{"_id", "id", "name", "age", "sex"};
    private SQLiteDatabase database;


    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    // 当第一次创建数据库的时候，调用该方法
    public void onCreate(SQLiteDatabase db) {
        //注意必须要有空格间隔
        String sql = "create table " + table_name +
                "(" +
                columns[0] + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                columns[1] + " int," +
                columns[2] + " varchar(10)," +
                columns[3] + " int," +
                columns[4] + " varchar(10)" +
                ")";
        Log.i(TAG, "create Database------->" + sql);
        db.execSQL(sql);
    }

    //当更新数据库的时候执行该方法
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "onUpgrade Database------------->");
    }

    //插入整条记录
    public void insert(ContentValues cv) {
        getDB().insert(table_name, null, cv);
        Log.i(TAG, "insert Database------------->");
    }

    //修改记录
    public void update(ContentValues cv, String whereClause, String[] whereArgs) {
        getDB().update(table_name, cv, whereClause, whereArgs);
        getDB().close();
        Log.i(TAG, "update Database------------->");
    }

    //查询所有记录
    public Cursor query(String selection, String[] selectionArgs, String groupBy, String having, String orderBy) {
        Cursor cursor = getDB().query(table_name, columns, selection, selectionArgs, groupBy, having, orderBy);
        Log.i(TAG, "query Database------------->");
        return cursor;
    }

    /**
     * 删除单条记录
     **/
    public void delete(String whereClause, String[] whereArgs) {
        getDB().delete(table_name, whereClause, whereArgs);
        Log.i(TAG, "delete Database------------->");
    }

    private SQLiteDatabase getDB() {
        if (database != null && database.isOpen()) {
            return database;
        } else {
            database = getReadableDatabase();
            return database;
        }
    }


}

