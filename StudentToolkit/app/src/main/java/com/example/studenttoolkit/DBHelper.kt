package com.example.studenttoolkit

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val TAG = "DBHelper"
        private const val DATABASE_NAME = "student_toolkit.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_STUDENTS = "students"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_STUDENTS (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                roll_number TEXT NOT NULL,
                semester TEXT,
                subject TEXT,
                cgpa REAL,
                attendance REAL
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENTS")
            onCreate(db)
        }
    }

    fun addStudent(
        name: String,
        rollNumber: String,
        semester: String,
        subject: String,
        cgpa: Double,
        attendance: Double
    ): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("roll_number", rollNumber)
            put("semester", semester)
            put("subject", subject)
            put("cgpa", cgpa)
            put("attendance", attendance)
        }
        return try {
            db.insert(TABLE_STUDENTS, null, values) != -1L
        } catch (e: Exception) {
            Log.e(TAG, "Error adding student", e)
            false
        } finally {
            db.close()
        }
    }

    fun getAllStudents(): List<Student> {
        val studentList = mutableListOf<Student>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_STUDENTS", null)

        try {
            if (cursor.moveToFirst()) {
                do {
                    studentList.add(
                        Student(
                            cursor.getString(cursor.getColumnIndexOrThrow("name")),
                            cursor.getString(cursor.getColumnIndexOrThrow("roll_number")),
                            cursor.getString(cursor.getColumnIndexOrThrow("semester")),
                            cursor.getString(cursor.getColumnIndexOrThrow("subject")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("cgpa")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("attendance"))
                        )
                    )
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error getting students", e)
        } finally {
            cursor.close()
            db.close()
        }
        return studentList
    }
}