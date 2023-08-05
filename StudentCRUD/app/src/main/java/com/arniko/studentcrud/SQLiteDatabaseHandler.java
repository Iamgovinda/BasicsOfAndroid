package com.arniko.studentcrud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "countryData";

    // Country table name
    private static final String TABLE_STUDENT= "Student";

    // Country Table Columns names
    private static final String KEY_ID = "id";
    private static final String STUDENT_ROLL = "roll";
    private static final String STUDENT_NAME = "name";
    private static final String STUDENT_FACULTY = "faculty";

    public SQLiteDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COUNTRY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_STUDENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + STUDENT_NAME + " TEXT," + STUDENT_FACULTY + " TEXT,"
                + STUDENT_ROLL  + " INTEGER" + ")";
        db.execSQL(CREATE_COUNTRY_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new country
    void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getName()); // Country Name
        values.put(STUDENT_FACULTY, student.getFaculty()); // Country Population
        values.put(STUDENT_ROLL, student.getRoll()); // Country Population


        // Inserting Row
        db.insert(TABLE_STUDENT, null, values);
        db.close(); // Closing database connection
    }

    // Getting single country
    Student getStudent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STUDENT, new String[] { KEY_ID,
                        STUDENT_NAME, STUDENT_FACULTY, STUDENT_ROLL}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getLong(2), cursor.getInt(2));
        // return country
        return student;
    }

    // Getting All Countries
    public List getAllStudents() {
        List studentList = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setFaculty(cursor.getString(2));
                // Adding country to list
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        // return country list
        return studentList;
    }

    // Updating single country
    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, student.getName());
        values.put(STUDENT_FACULTY, student.getFaculty());

        // updating row
        return db.update(TABLE_STUDENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
    }

    // Deleting single country
    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT, KEY_ID + " = ?",
                new String[] { String.valueOf(student.getId()) });
        db.close();
    }

//    // Deleting all countries
//    public void deleteAllCountries() {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_COUNTRY,null,null);
//        db.close();
//    }
//
//    // Getting countries Count
//    public int getCountriesCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_COUNTRY;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        // return count
//        return cursor.getCount();
//    }
}
