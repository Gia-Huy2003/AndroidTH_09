package com.example.bai96


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

// BroadcastReceiver theo dõi trạng thái kết nối mạng
class NetworkReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Lấy hệ thống quản lý kết nối mạng
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo

        // Kiểm tra xem thiết bị có kết nối mạng hay không
        if (networkInfo != null && networkInfo.isConnected) {
            // Nếu có kết nối mạng
            Toast.makeText(context, "Đã kết nối mạng", Toast.LENGTH_SHORT).show()
        } else {
            // Nếu không có kết nối mạng
            Toast.makeText(context, "Không có kết nối mạng", Toast.LENGTH_SHORT).show()
        }
    }
}
