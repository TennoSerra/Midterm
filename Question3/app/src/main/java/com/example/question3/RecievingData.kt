package com.example.question3

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RecievingData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recieving_data)
        // Get the Intent that started this activity
        val intent = intent

        // Retrieve data from the Intent
        val GradeResult = intent.getStringExtra("gradingResult")
        val CalculatorResult = intent.getIntExtra("calculatorResult", 0)

        // Use the retrieved data
        val textView: TextView = findViewById(R.id.recievedData)
        textView.text = "Grade App Result: $GradeResult\nInt: $CalculatorResult\n"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}