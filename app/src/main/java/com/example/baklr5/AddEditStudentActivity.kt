package com.example.baklr5

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.baklr5.databinding.ActivityAddEditStudentBinding
import com.google.android.material.snackbar.Snackbar

class AddEditStudentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddEditStudentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

            if (name.isBlank() || phone.isBlank()) {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                // Зберігаємо студента
                Toast.makeText(this, "Student saved: $name, $phone", Toast.LENGTH_SHORT).show()
                finish() // Закриває активність
            }
        }

        // Додаємо кнопку для показу push-подібного повідомлення
        binding.showNotificationButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

            if (name.isNotBlank() && phone.isNotBlank()) {
                // Відправляємо push-подібне повідомлення через Snackbar
                showPushNotification(name, phone)
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Функція для показу Snackbar як push-повідомлення
    private fun showPushNotification(name: String, phone: String) {
        val message = "Student: $name, Phone: $phone"
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)

        // Задаємо позицію на екрані (зверху)
        snackbar.anchorView = binding.showNotificationButton // Плашка буде внизу кнопки

        // Встановлюємо час відображення (10 секунд)
        snackbar.duration = 10000

        // Показуємо повідомлення
        snackbar.show()
    }
}
