package com.example.studenttoolkit

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class ViewStudentsActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    private lateinit var listView: ListView
    private lateinit var studentAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_students)

        dbHelper = DBHelper(this)
        listView = findViewById(R.id.studentListView)

        val students = dbHelper.getAllStudents() // Get students from DB
        val studentList = students.map {
            "Name: ${it.name}\nRoll: ${it.rollNumber}\nSemester: ${it.semester}\nSubjects: ${it.subject}\nCGPA: ${it.cgpa}\nAttendance: ${it.attendance}"
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentList)
        listView.adapter = adapter
    }
}
