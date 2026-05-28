package com.example.studytracker.ui // Cambia esto si creaste una carpeta adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.studytracker.R
import com.example.studytracker.entity.Tarea

class TareaAdapter : ListAdapter<Tarea, TareaAdapter.TareaViewHolder>(TareaDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TareaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_tarea, parent, false)
        return TareaViewHolder(view)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TareaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Enlazamos con los IDs del diseño nuevo y pro
        private val tvTitle: TextView = itemView.findViewById(R.id.tvCardTitle)
        private val tvSubject: TextView = itemView.findViewById(R.id.tvCardSubject)
        private val cbEstado: CheckBox = itemView.findViewById(R.id.cbEstadoTarea)
        private val btnEliminar: ImageView = itemView.findViewById(R.id.btnEliminarTarea)

        fun bind(tarea: Tarea) {
            // Asignamos los textos
            tvTitle.text = tarea.nombre
            tvSubject.text = "${tarea.curso.uppercase()} - ${tarea.fecha}"

            // Magia: Hacer clic en la tarjeta para abrir la vista de Detalles
            itemView.setOnClickListener {
                val context = it.context
                val intent = Intent(context, DetallesActivity::class.java).apply {
                    putExtra("TITULO", tarea.nombre)
                    putExtra("CURSO", "${tarea.curso} - ${tarea.fecha}")
                    putExtra("DESCRIPCION", tarea.descripcion)
                    putExtra("ESTADO", false) // Aquí luego le puedes pasar si está completado o no
                }
                context.startActivity(intent)
            }
        }
    }

    class TareaDiffCallback : DiffUtil.ItemCallback<Tarea>() {
        override fun areItemsTheSame(oldItem: Tarea, newItem: Tarea): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Tarea, newItem: Tarea): Boolean {
            return oldItem == newItem
        }
    }
}