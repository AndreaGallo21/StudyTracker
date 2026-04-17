package com.example.studytracker.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studytracker.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val user = binding.etUser.text.toString()
            val pass = binding.etPass.text.toString()

            // Requisito del profe: Validar sin Base de Datos
            if (user == "admin" && pass == "123") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Cierra el login para no poder retroceder
            } else {
                Toast.makeText(this, "Datos incorrectos papu :v", Toast.LENGTH_SHORT).show()
            }
        }
    }
}