package com.example.studytracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studytracker.databinding.ActivityFormularioBinding

class FormularioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFormularioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Acción del botón Guardar
        binding.btnSaveTask.setOnClickListener {
            // El .trim() quita espacios en blanco accidentales
            val nombre = binding.etTaskName.text.toString().trim()
            val curso = binding.etTaskSubject.text.toString().trim()
            val fecha = binding.etTaskDate.text.toString().trim()

            // Validamos que NINGÚN campo esté vacío
            if (nombre.isNotEmpty() && curso.isNotEmpty() && fecha.isNotEmpty()) {
                // Mensaje formal de éxito
                Toast.makeText(this, "Tarea guardada exitosamente.", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                // 🔥 AQUÍ ESTÁ EL ARREGLO: Mensaje formal de error 🔥
                Toast.makeText(this, "Error: Todos los campos son obligatorios.", Toast.LENGTH_LONG).show()
            }
        }
    }
}