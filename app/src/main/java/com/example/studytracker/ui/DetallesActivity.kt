package com.example.studytracker.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studytracker.databinding.ActivityDetallesBinding

class DetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acción del botón Volver
        binding.btnBack.setOnClickListener {
            finish() // Cierra esta pantalla y te devuelve suavemente al Dashboard
        }
    }
}