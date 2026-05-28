package com.example.studytracker.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studytracker.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth // Importante para Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth // Declaramos la variable de Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializamos la conexión con Firebase Auth
        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            // Usamos los IDs exactos del XML
            val email = binding.etLoginEmail.text.toString().trim()
            val pass = binding.etLoginPassword.text.toString().trim()

            // 1. Validar que no dejen los campos en blanco
            if (email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Oe, llena todos los campos pe", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 2. Mandar los datos a Firebase para comprobar
            auth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // ¡Pasó la prueba! Usuario verificado.
                        Toast.makeText(this, "¡Logeo exitoso!", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish() // Matamos el login para que no vuelvan atrás
                    } else {
                        // Falló (correo no existe o clave equivocada)
                        Toast.makeText(this, "Datos incorrectos. Intenta de nuevo.", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}