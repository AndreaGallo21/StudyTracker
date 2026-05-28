package com.example.studytracker.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.studytracker.databinding.ActivityDetallesBinding

class DetallesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ¡Corregido! Ahora busca el ID exacto que pusiste en tu XML (btnVolverDetalle)
        binding.btnVolverDetalle.setOnClickListener {
            finish() // Cierra esta pantalla y te devuelve suavemente a la lista
        }

        // Recibir los datos enviados desde el Adapter (Lista)
        val titulo = intent.getStringExtra("TITULO") ?: "Sin título"
        val curso = intent.getStringExtra("CURSO") ?: "Sin curso"
        val descripcion = intent.getStringExtra("DESCRIPCION") ?: "Sin descripción"
        val estado = intent.getBooleanExtra("ESTADO", false)

        // Mostrar los datos en la pantalla
        binding.tvDetalleTitulo.text = titulo
        binding.tvDetalleCurso.text = curso
        binding.tvDetalleDescripcion.text = descripcion

        // Cambia el color y texto dependiendo si ya la marcaste como lista
        if (estado) {
            binding.tvDetalleEstado.text = "Estado: Finalizado"
            binding.tvDetalleEstado.setTextColor(Color.parseColor("#00E676")) // Verde pro
        } else {
            binding.tvDetalleEstado.text = "Estado: Pendiente"
            binding.tvDetalleEstado.setTextColor(Color.parseColor("#FFC107")) // Amarillo advertencia
        }
    }
}