package com.example.studenttoolkit

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class AttendanceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance)

        val subjectName = findViewById<EditText>(R.id.subjectName)
        val totalClasses = findViewById<EditText>(R.id.totalClasses)
        val classesAttended = findViewById<EditText>(R.id.classesAttended)
        val calculateBtn = findViewById<Button>(R.id.calculateBtn)
        val resultText = findViewById<TextView>(R.id.resultText)

        calculateBtn.setOnClickListener {
            val total = totalClasses.text.toString().toIntOrNull()
            val attended = classesAttended.text.toString().toIntOrNull()
            val name = subjectName.text.toString()

            if (total == null || attended == null || name.isBlank()) {
                Toast.makeText(this, "Please enter valid data.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val percentage = (attended.toDouble() / total) * 100
            val status = if (percentage >= 75) "Safe ✅" else "Short Attendance ⚠️"
            resultText.text = "$name: ${"%.2f".format(percentage)}% - $status"
        }
    }
}
