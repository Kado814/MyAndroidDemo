package com.myapplication.myandroiddemo.activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.view.View;

import com.myapplication.myandroiddemo.R;

import butterknife.OnClick;

public class SqlLiteActivity extends MyBaseActivity implements View.OnClickListener {

    private MyDBHelper dbHelper;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sqllite;
    }

    @Override
    protected void initData() {
        dbHelper = new MyDBHelper(mContext);
    }

    @Override
    protected void initView() {
    }


    @OnClick({R.id.createDatabase, R.id.updateDatabase, R.id.insert, R.id.update, R.id.query, R.id.delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createDatabase:
                //获取数据库帮助类对象，首次创建数据库
                dbHelper.getReadableDatabase();
                break;
            case R.id.updateDatabase:
                dbHelper.getReadableDatabase();
                break;
            case R.id.insert:
                ContentValues cv = new ContentValues();
                cv.put("id", 1);
                cv.put("name", "xiaoming");
                cv.put("age", 21);
                cv.put("sex", "male");
                dbHelper.insert(cv);
                break;
            case R.id.update:
                ContentValues cv2 = new ContentValues();
                cv2.put("age", "23");
                String whereClause = "id=?";
                String[] whereArgs = {String.valueOf(1)};
                dbHelper.update(cv2, whereClause, whereArgs);
                break;
            case R.id.query:
                Cursor cursor = dbHelper.query("id=?", new String[]{"1"}, null, null, null);
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    String age = cursor.getString(cursor.getColumnIndex("age"));
                    String sex = cursor.getString(cursor.getColumnIndex("sex"));
                    System.out.println("query------->" + "姓名：" + name + " " + "年龄：" + age + " " + "性别：" + sex);
                }
                break;
            case R.id.delete:
                String whereClauses = "id=?";
                String[] whereArg = {String.valueOf(2)};
                dbHelper.delete(whereClauses, whereArg);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dbHelper.getReadableDatabase().isOpen()) {
            dbHelper.getReadableDatabase().close();
        }
    }
}
