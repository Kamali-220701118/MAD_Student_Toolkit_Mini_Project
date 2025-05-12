package com.example.studenttoolkit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class ExamCountdownActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_countdown)

        val examDateInput = findViewById<EditText>(R.id.examDateInput)
        val calculateCountdownBtn = findViewById<Button>(R.id.calculateCountdownBtn)
        val countdownResult = findViewById<TextView>(R.id.countdownResult)

        calculateCountdownBtn.setOnClickListener {
            val inputDateStr = examDateInput.text.toString()
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)

            try {
                val examDate = sdf.parse(inputDateStr)
                val today = Calendar.getInstance().time

                val diffInMillis = examDate.time - today.time
                val daysLeft = (diffInMillis / (1000 * 60 * 60 * 24)).toInt()

                if (daysLeft >= 0) {
                    countdownResult.text = "Days until exam: $daysLeft"
                } else {
                    countdownResult.text = "Exam date has already passed!"
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Invalid date format!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
