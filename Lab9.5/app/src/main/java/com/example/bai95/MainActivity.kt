package com.example.bai95

import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var nameList = mutableListOf("Nguyễn Văn A", "Trần Thị B", "Lê Quang C")
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        // Thiết lập adapter để hiển thị danh sách
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nameList)
        listView.adapter = adapter

        // Đăng ký ContextMenu cho ListView
        registerForContextMenu(listView)
    }

    // Tạo ContextMenu khi người dùng nhấn giữ vào mục trong ListView
    override fun onCreateContextMenu(
        menu: ContextMenu?, v: android.view.View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedName = nameList[info.position]

        menu?.setHeaderTitle(selectedName)
        menu?.add(0, 1, 0, "Chỉnh sửa")
        menu?.add(0, 2, 0, "Xóa")
    }

    // Xử lý sự kiện khi người dùng chọn một mục trong ContextMenu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val selectedName = nameList[info.position]

        return when (item.itemId) {
            1 -> { // Chỉnh sửa
                showEditDialog(info.position, selectedName)
                true
            }
            2 -> { // Xóa
                nameList.removeAt(info.position)
                adapter.notifyDataSetChanged() // Cập nhật lại ListView
                Toast.makeText(this, "Đã xóa: $selectedName", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    // Hiển thị AlertDialog để chỉnh sửa tên
    private fun showEditDialog(position: Int, currentName: String) {
        val editText = EditText(this)
        editText.setText(currentName)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Chỉnh sửa tên")
            .setView(editText)
            .setPositiveButton("Lưu") { _, _ ->
                val newName = editText.text.toString()
                nameList[position] = newName
                adapter.notifyDataSetChanged() // Cập nhật lại ListView
                Toast.makeText(this, "Đã chỉnh sửa tên", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Hủy", null)
            .create()

        dialog.show()
    }
}