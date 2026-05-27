package com.example.studytracker.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tareas")
data class Tarea(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val nombre: String,
    val curso: String,
    val fecha: String
)
