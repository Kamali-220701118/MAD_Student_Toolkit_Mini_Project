package com.example.studenttoolkit

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val rollInput = findViewById<EditText>(R.id.rollInput)
        val semesterInput = findViewById<EditText>(R.id.semesterInput)
        val subjectInput = findViewById<EditText>(R.id.subjectInput)
        val cgpaInput = findViewById<EditText>(R.id.cgpaInput)
        val attendanceInput = findViewById<EditText>(R.id.attendanceInput)
        val saveButton = findViewById<Button>(R.id.saveButton)

        val db = DBHelper(this)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            val roll = rollInput.text.toString()
            val semester = semesterInput.text.toString()
            val subjects = subjectInput.text.toString()
            val cgpa = cgpaInput.text.toString().toDoubleOrNull() ?: 0.0
            val attendance = attendanceInput.text.toString().toDoubleOrNull() ?:0.0

            if (db.addStudent(name, roll, semester, subjects, cgpa, attendance)) {
                Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to Add", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
