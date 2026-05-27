package com.example.studytracker.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.studytracker.R
import com.example.studytracker.db.AppDatabase
import com.example.studytracker.entity.Tarea
import com.example.studytracker.repository.TareaRepository
import com.example.studytracker.viewmodel.TareaViewModel
import com.example.studytracker.viewmodel.TareaViewModelFactory

class FormularioActivity : AppCompatActivity() {

    // 1. Preparamos la Base de Datos, el Repositorio y el ViewModel
    private val database by lazy { AppDatabase.getDatabase(this) }
    private val repository by lazy { TareaRepository(database.tareaDao()) }
    private val viewModel: TareaViewModel by viewModels {
        TareaViewModelFactory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        // 2. Conectamos las variables de Kotlin con las cajitas de tu XML
        val etTaskName = findViewById<EditText>(R.id.etTaskName)
        val etTaskSubject = findViewById<EditText>(R.id.etTaskSubject)
        val etTaskDate = findViewById<EditText>(R.id.etTaskDate)
        val btnSaveTask = findViewById<Button>(R.id.btnSaveTask)

        // 3. Le damos vida al botón "Guardar Tarea"
        btnSaveTask.setOnClickListener {
            val nombre = etTaskName.text.toString().trim()
            val curso = etTaskSubject.text.toString().trim()
            val fecha = etTaskDate.text.toString().trim()

            // Validamos que el pata no deje nada vacío
            if (nombre.isEmpty() || curso.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(this, "¡Habla causa! Llena todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 4. Armamos la tarea (el ID se genera solito gracias a Room)
            val nuevaTarea = Tarea(
                nombre = nombre,
                curso = curso,
                fecha = fecha
            )

            // 5. ¡El momento de la verdad! Mandamos a guardar usando el ViewModel
            viewModel.insertar(nuevaTarea)

            // Avisamos que todo salió joya
            Toast.makeText(this, "¡Tarea guardada como un campeón!", Toast.LENGTH_SHORT).show()

            // Cerramos la pantalla del formulario
            finish()
        }
    }
}