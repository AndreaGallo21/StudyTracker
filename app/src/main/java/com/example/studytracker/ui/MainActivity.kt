package com.example.studytracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.studytracker.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // configuración pro de pantalla completa
        enableEdgeToEdge()

        // Usamos View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración de márgenes del sistema (Window Insets)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //  AQUÍ ENTRA LA FECHA
        //
        val sdf = SimpleDateFormat("EEEE, dd 'de' MMMM", Locale("es", "ES"))
        val fechaActual = sdf.format(Date()).replaceFirstChar { it.uppercase() }
        binding.tvFechaHoy.text = fechaActual

        // 1. Navegación hacia el Formulario (usando el ID del nuevo CardView)
        binding.btnCardRegistrar.setOnClickListener {
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)
        }

        // 2. Navegación hacia la Pantalla de Tareas Reales
        binding.btnCardVerTareas.setOnClickListener {
            val intent = Intent(this, TareasActivity::class.java)
            startActivity(intent)
        }
    }
}