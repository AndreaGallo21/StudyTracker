package com.example.studytracker.repository

import com.example.studytracker.dao.TareaDao
import com.example.studytracker.entity.Tarea
import kotlinx.coroutines.flow.Flow

class TareaRepository(private val tareaDao: TareaDao) {

    val todasLasTareas: Flow<List<Tarea>> = tareaDao.obtenerTodasLasTareas()

    suspend fun insertar(tarea: Tarea) {
        tareaDao.insertar(tarea)
    }
}