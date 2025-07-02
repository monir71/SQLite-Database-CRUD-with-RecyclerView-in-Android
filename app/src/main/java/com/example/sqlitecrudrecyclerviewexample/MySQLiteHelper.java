package com.example.sqlitecrudrecyclerviewexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "studentsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TBL_NAME = "students";
    private static final String STUDENTS_COL_STUDENT_ID = "student_id";
    private static final String STUDENTS_COL_ROLL_NUMBER = "roll_number";
    private static final String STUDENTS_COL_REGISTRATION_NUMBER = "registration_number";
    private static final String STUDENTS_COL_NAME = "name";
    private static final String STUDENTS_COL_DOB = "dob";
    private static final String STUDENTS_COL_ENROLLMENT_CLASS = "enrollment_class";
    private static final String STUDENTS_COL_STUDY_GROUP = "study_group";
    private static final String STUDENTS_COL_FATHER_NAME = "father_name";
    private static final String STUDENTS_COL_MOTHER_NAME = "mother_name";
    private static final String STUDENTS_COL_SEX = "sex";
    private static final String STUDENTS_COL_FULL_ADDRESS = "full_address";
    private static final String STUDENTS_COL_DISTRICT = "district";
    private static final String STUDENTS_COL_COUNTRY = "country";

    //student_id, roll_number, registration_number, name, dob, enrollment_class, study_group, father_name, mother_name, sex, full_address, district, country

    public MySQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TBL_NAME + " ( " +
                STUDENTS_COL_STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                STUDENTS_COL_ROLL_NUMBER + " INTEGER NOT NULL, " +
                STUDENTS_COL_REGISTRATION_NUMBER + " INTEGER NOT NULL, " +
                STUDENTS_COL_NAME + " TEXT NOT NULL, " +
                STUDENTS_COL_DOB + " TEXT NOT NULL, " +
                STUDENTS_COL_ENROLLMENT_CLASS + " TEXT NOT NULL, " +
                STUDENTS_COL_STUDY_GROUP + " TEXT NOT NULL, " +
                STUDENTS_COL_FATHER_NAME + " TEXT NOT NULL, " +
                STUDENTS_COL_MOTHER_NAME + " TEXT NOT NULL, " +
                STUDENTS_COL_SEX + " TEXT NOT NULL, " +
                STUDENTS_COL_FULL_ADDRESS + " TEXT NOT NULL, " +
                STUDENTS_COL_DISTRICT + " TEXT NOT NULL, " +
                STUDENTS_COL_COUNTRY + " TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(int roll_number, int registration_number, String name, String dob, String enrollment_class,
                           String study_group, String father_name, String mother_name, String sex, String full_address, String district, String country)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENTS_COL_ROLL_NUMBER, roll_number);
        values.put(STUDENTS_COL_REGISTRATION_NUMBER, registration_number);
        values.put(STUDENTS_COL_NAME, name);
        values.put(STUDENTS_COL_DOB, dob);
        values.put(STUDENTS_COL_ENROLLMENT_CLASS, enrollment_class);
        values.put(STUDENTS_COL_STUDY_GROUP, study_group);
        values.put(STUDENTS_COL_FATHER_NAME, father_name);
        values.put(STUDENTS_COL_MOTHER_NAME, mother_name);
        values.put(STUDENTS_COL_SEX, sex);
        values.put(STUDENTS_COL_FULL_ADDRESS, full_address);
        values.put(STUDENTS_COL_DISTRICT, district);
        values.put(STUDENTS_COL_COUNTRY, country);

        db.insert(TBL_NAME, null, values);
    }

    public void updateData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENTS_COL_STUDY_GROUP, "Software Engineering");
        db.update(TBL_NAME, values, STUDENTS_COL_STUDENT_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public ArrayList<MyModel> readData()
    {
        ArrayList<MyModel> data = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TBL_NAME, null);
        while(cursor.moveToNext())
        {
            MyModel model = new MyModel();
            model.student_id = cursor.getInt(0);
            model.roll_number = cursor.getInt(1);
            model.registration_number = cursor.getInt(2);
            model.name = cursor.getString(3);
            model.dob = cursor.getString(4);
            model.enrollment_class = cursor.getString(5);
            model.study_group = cursor.getString(6);
            model.father_name = cursor.getString(7);
            model.mother_name = cursor.getString(8);
            model.sex = cursor.getString(9);
            model.full_address = cursor.getString(10);
            model.district = cursor.getString(11);
            model.country = cursor.getString(12);
            data.add(model);
        }
        return data;
    }

    public void deleteData(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TBL_NAME, STUDENTS_COL_STUDENT_ID + " = ?", new String[]{String.valueOf(id)});
    }
}

