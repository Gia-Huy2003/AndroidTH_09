package com.example.bai93

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvResult = findViewById<TextView>(R.id.tvResult)
        val btnOpenDialog = findViewById<Button>(R.id.btnOpenDialog)

        btnOpenDialog.setOnClickListener {
            // Khởi tạo Custom AlertDialog
            val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null)
            val etFullName = dialogView.findViewById<EditText>(R.id.etFullName)
            val etLastName = dialogView.findViewById<EditText>(R.id.etLastName)
            val etDateOfBirth = dialogView.findViewById<EditText>(R.id.etDateOfBirth)

            val dialogBuilder = AlertDialog.Builder(this)
                .setView(dialogView)
                .setTitle("Nhập thông tin cá nhân")
                .setPositiveButton("Lưu") { dialog, _ ->
                    val fullName = etFullName.text.toString().trim()
                    val lastName = etLastName.text.toString().trim()
                    val dateOfBirth = etDateOfBirth.text.toString().trim()

                    if (fullName.isNotBlank() && lastName.isNotBlank() && dateOfBirth.isNotBlank()) {
                        val result = "Họ tên: $fullName $lastName\nNgày sinh: $dateOfBirth"
                        tvResult.text = result
                        dialog.dismiss()
                    } else {
                        Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Hủy") { dialog, _ ->
                    dialog.dismiss()
                }

            // Hiển thị AlertDialog
            dialogBuilder.create().show()
        }
    }
}
