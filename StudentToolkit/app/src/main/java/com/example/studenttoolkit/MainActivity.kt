package com.example.studenttoolkit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    // Only keep views that exist in XML
    private lateinit var gradeInputs: List<EditText>
    private lateinit var calculateButton: Button
    private lateinit var resultText: TextView
    private lateinit var attendanceButton: Button
    private lateinit var examCountdownBtn: Button
    private lateinit var addStudentButton: Button
    private lateinit var viewStudentsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize only views that exist in XML
        gradeInputs = listOf(
            findViewById(R.id.editTextGrade1),
            findViewById(R.id.editTextGrade2),
            findViewById(R.id.editTextGrade3),
            findViewById(R.id.editTextGrade4),
            findViewById(R.id.editTextGrade5)
        )

        calculateButton = findViewById(R.id.buttonCalculate)
        resultText = findViewById(R.id.textViewResult)
        attendanceButton = findViewById(R.id.attendanceBtn)
        examCountdownBtn = findViewById(R.id.examCountdownBtn)
        addStudentButton = findViewById(R.id.addStudentButton)
        viewStudentsButton = findViewById(R.id.viewStudentsButton)

        // Set click listeners
        calculateButton.setOnClickListener {
            calculateCGPA()
        }

        attendanceButton.setOnClickListener {
            startActivity(Intent(this, AttendanceActivity::class.java))
        }

        examCountdownBtn.setOnClickListener {
            startActivity(Intent(this, ExamCountdownActivity::class.java))
        }

        addStudentButton.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }

        viewStudentsButton.setOnClickListener {
            startActivity(Intent(this, ViewStudentsActivity::class.java))
        }
    }

    private fun calculateCGPA() {
        try {
            val grades = gradeInputs.map {
                it.text.toString().takeIf { str -> str.isNotEmpty() }?.toDouble() ?: 0.0
            }
            val cgpa = grades.average()
            resultText.text = "Your CGPA: %.2f".format(cgpa)
        } catch (e: Exception) {
            Toast.makeText(this, "Please enter valid grades", Toast.LENGTH_SHORT).show()
        }
    }
}