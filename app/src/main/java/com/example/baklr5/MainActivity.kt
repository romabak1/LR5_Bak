package com.example.baklr5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baklr5.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val students = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddEditStudentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        val adapter = StudentAdapter(students) { student ->
            sendSms(student.phone)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    // Функція для відправки SMS
    private fun sendSms(phone: String) {
        val smsIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("smsto:$phone") // Формуємо адресу для SMS
            putExtra("sms_body", "Hello from University App!") // Текст повідомлення
        }

        try {
            if (smsIntent.resolveActivity(packageManager) != null) {
                startActivity(smsIntent) // Запускаємо SMS додаток
                showSnackbar("SMS sent to $phone") // Показуємо повідомлення
            } else {
                showSnackbar("No SMS app found") // Якщо додаток для SMS не знайдено
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showSnackbar("Error sending SMS")
        }
    }

    // Функція для показу повідомлення через Snackbar
    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}
