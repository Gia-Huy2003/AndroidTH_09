package com.example.bai91

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPickDateTime = findViewById<Button>(R.id.btnPickDateTime)
        val tvDateTime = findViewById<TextView>(R.id.tvDateTime)

        btnPickDateTime.setOnClickListener {
            // Lấy ngày hiện tại
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Hiển thị DatePickerDialog
            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                // Hiển thị TimePickerDialog sau khi chọn ngày
                val hour = calendar.get(Calendar.HOUR_OF_DAY)
                val minute = calendar.get(Calendar.MINUTE)

                TimePickerDialog(this, { _, selectedHour, selectedMinute ->
                    val selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                    // Hiển thị kết quả trên TextView
                    tvDateTime.text = "Ngày: $selectedDate - Giờ: $selectedTime"
                }, hour, minute, true).show()

            }, year, month, day).show()
        }
    }
}
