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

        // Acción al presionar el botón de Ingresar
        binding.btnLogin.setOnClickListener {
            // 🔥 El .trim() es MAGIA: borra los espacios invisibles al principio o al final
            val user = binding.etUser.text.toString().trim()
            val pass = binding.etPass.text.toString().trim()

            if (user == "admin" && pass == "123") {
                // Viajamos al Dashboard (MainActivity)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                // 🔥 MATAMOS el Login para que no puedan regresar con el botón de "Atrás"
                finish()
            } else {
                // 🔥 ESTE ES EL ESPÍA: Te dirá EXACTAMENTE qué leyó el sistema entre los corchetes
                Toast.makeText(this, "Error. Leyó: [$user] y [$pass]", Toast.LENGTH_LONG).show()
            }
        }
    }
}