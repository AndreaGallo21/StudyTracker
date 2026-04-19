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
            val nombre = binding.etTaskName.text.toString().trim()
            val curso = binding.etTaskSubject.text.toString().trim()

            if (nombre.isNotEmpty() && curso.isNotEmpty()) {
                Toast.makeText(this, "¡Tarea '$nombre' guardada!", Toast.LENGTH_SHORT).show()
                // El finish() cierra el formulario y te devuelve suavemente al Dashboard
                finish()
            } else {
                Toast.makeText(this, "Por favor llena los datos pe :v", Toast.LENGTH_SHORT).show()
            }
        }
    }
}