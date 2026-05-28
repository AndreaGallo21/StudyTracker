package com.example.studytracker.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studytracker.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

class FormularioActivity : AppCompatActivity() {

    // 1. Declaramos las herramientas de Firebase
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        // 2. Inicializamos Firebase
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        val etTaskName = findViewById<EditText>(R.id.etTaskName)
        val etTaskSubject = findViewById<EditText>(R.id.etTaskSubject)
        val etTaskDate = findViewById<EditText>(R.id.etTaskDate)
        val etTaskDescription = findViewById<EditText>(R.id.etTaskDescription)
        val btnSaveTask = findViewById<Button>(R.id.btnSaveTask)

        // Calendario (Este código está perfecto, lo dejamos igual) ---
        etTaskDate.setOnClickListener {
            val calendario = Calendar.getInstance()
            val anio = calendario.get(Calendar.YEAR)
            val mes = calendario.get(Calendar.MONTH)
            val dia = calendario.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(this, { _, yearSeleccionado, monthSeleccionado, daySeleccionado ->
                val fechaFormateada = "$daySeleccionado/${monthSeleccionado + 1}/$yearSeleccionado"
                etTaskDate.setText(fechaFormateada)
            }, anio, mes, dia)

            datePickerDialog.show()
        }

        // --- LÓGICA DE GUARDAR EN FIRESTORE ---
        btnSaveTask.setOnClickListener {
            val nombre = etTaskName.text.toString().trim()
            val curso = etTaskSubject.text.toString().trim()
            val fecha = etTaskDate.text.toString().trim()
            val descripcion = etTaskDescription.text.toString().trim()

            if (nombre.isEmpty() || curso.isEmpty() || fecha.isEmpty()) {
                Toast.makeText(this, "Llena los campos obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificamos quién es el usuario logeado
            val currentUser = auth.currentUser
            if (currentUser == null) {
                Toast.makeText(this, "Error: No hay usuario activo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 3. Empaquetamos los datos como Firestore los pide (HashMap)
            val tareaMap = hashMapOf(
                "userId" to currentUser.uid, // Guardamos el ID del creador
                "nombre" to nombre,
                "curso" to curso,
                "fecha" to fecha,
                "descripcion" to descripcion,
                "completada" to false // Estado inicial
            )

            // 4. Lo mandamos a la colección "tareas"
            db.collection("tareas")
                .add(tareaMap)
                .addOnSuccessListener {
                    Toast.makeText(this, "¡Guardado en la NUBE con éxito!", Toast.LENGTH_SHORT).show()
                    finish() // Regresamos a la pantalla anterior
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
                }
        }
    }
}