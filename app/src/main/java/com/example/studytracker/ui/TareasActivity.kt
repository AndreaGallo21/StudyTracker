package com.example.studytracker.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studytracker.R
import com.example.studytracker.entity.Tarea
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class TareasActivity : AppCompatActivity() {

    // 1. Declaramos las herramientas de Firebase y el Adapter
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: TareaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas)

        // 2. Inicializamos Firebase
        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        // 3. Conectamos la lista (RecyclerView)
        val rvTareas = findViewById<RecyclerView>(R.id.rvTareas)
        adapter = TareaAdapter()
        rvTareas.adapter = adapter
        rvTareas.layoutManager = LinearLayoutManager(this)

        // 4. Llamamos a la función que trae la data de la nube
        cargarTareasDeFirestore()
    }

    private fun cargarTareasDeFirestore() {
        val currentUser = auth.currentUser
        if (currentUser == null) {
            Toast.makeText(this, "Error: No hay usuario activo", Toast.LENGTH_SHORT).show()
            return
        }

        // Buscamos en Firestore SOLO las tareas del usuario logeado
        db.collection("tareas")
            .whereEqualTo("userId", currentUser.uid)
            .get() // Trae los datos de la nube
            .addOnSuccessListener { documentos ->
                val listaTareas = mutableListOf<Tarea>()

                for (documento in documentos) {
                    // Rescatamos los datos del documento en Firestore
                    val nombre = documento.getString("nombre") ?: "Sin nombre"
                    val curso = documento.getString("curso") ?: "Sin curso"
                    val fecha = documento.getString("fecha") ?: "Sin fecha"
                    val descripcion = documento.getString("descripcion") ?: ""
                    // val completada = documento.getBoolean("completada") ?: false

                    // Creamos el objeto Tarea para dárselo a tu Adapter
                    // Nota Pro: Usamos documento.id.hashCode() para simular el ID numérico que usaba Room y no romper tu modelo
                    val tarea = Tarea(
                        id = documento.id.hashCode(),
                        nombre = nombre,
                        curso = curso,
                        fecha = fecha,
                        descripcion = descripcion
                        // estaCompletada = completada (descomenta si tu data class Tarea te lo pide)
                    )
                    listaTareas.add(tarea)
                }

                // Le pasamos la lista fresca al Adapter para que dibuje las tarjetas
                adapter.submitList(listaTareas)
            }
            .addOnFailureListener { e ->
                Log.e("FIRESTORE", "Error al cargar: ", e)
                Toast.makeText(this, "Hubo un error al cargar tus tareas", Toast.LENGTH_SHORT).show()
            }
    }
}