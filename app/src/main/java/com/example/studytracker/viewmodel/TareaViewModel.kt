package com.example.studytracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.studytracker.entity.Tarea
import com.example.studytracker.repository.TareaRepository
import kotlinx.coroutines.launch

class TareaViewModel(private val repository: TareaRepository) : ViewModel() {

    val listaDeTareas: LiveData<List<Tarea>> = repository.todasLasTareas.asLiveData()

    fun insertar(tarea: Tarea) = viewModelScope.launch {
        repository.insertar(tarea)
    }
}

class TareaViewModelFactory(private val repository: TareaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TareaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TareaViewModel(repository) as T
        }
        throw IllegalArgumentException("Clase ViewModel desconocida")
    }
}