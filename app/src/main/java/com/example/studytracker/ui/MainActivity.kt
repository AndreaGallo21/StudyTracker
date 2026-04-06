package com.example.studytracker.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studytracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 1. Declaramos la variable que conectará nuestra vista (el XML) con el código
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. "Inflamos" el diseño (le decimos a Android que prepare el XML para usarlo)
        binding = ActivityMainBinding.inflate(layoutInflater)

        // 3. Le decimos a la actividad que muestre la vista conectada (la raíz)
        setContentView(binding.root)

        // Esto es para que la app respete los bordes de la pantalla del celular
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 4. ¡AQUÍ ESTÁ LA MAGIA DEL BOTÓN!
        // Como usamos ViewBinding, Android Studio ya sabe que existe un 'fabAddTask' en tu XML
        binding.fabAddTask.setOnClickListener {
            // Un Toast es un mensajito temporal que aparece abajo en la pantalla
            Toast.makeText(this, "¡Listo para crear tu primera tarea académica!", Toast.LENGTH_SHORT).show()
        }
    }
}