package com.example.bai92

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etInput = findViewById<EditText>(R.id.etInput)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnDeleteAll = findViewById<Button>(R.id.btnDeleteAll)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // Khởi tạo RecyclerView
        val items = mutableListOf<String>()
        itemAdapter = ItemAdapter(items)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Xử lý nút "Thêm nội dung"
        btnAdd.setOnClickListener {
            val input = etInput.text.toString()
            if (input.isNotBlank()) {
                items.add(input)
                itemAdapter.notifyItemInserted(items.size - 1)
                etInput.text.clear()
            } else {
                Toast.makeText(this, "Nội dung không được để trống!", Toast.LENGTH_SHORT).show()
            }
        }

        // Xử lý nút "Xóa tất cả"
        btnDeleteAll.setOnClickListener {
            if (items.isNotEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Xác nhận xóa")
                builder.setMessage("Bạn có chắc chắn muốn xóa toàn bộ dữ liệu không?")
                builder.setPositiveButton("Xác nhận") { dialog, _ ->
                    itemAdapter.clear()
                    Toast.makeText(this, "Đã xóa dữ liệu", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }
                builder.setNegativeButton("Hủy") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.create().show()
            } else {
                Toast.makeText(this, "Không có dữ liệu để xóa!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
