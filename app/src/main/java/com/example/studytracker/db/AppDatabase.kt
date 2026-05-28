package com.example.studytracker.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.studytracker.dao.TareaDao
import com.example.studytracker.entity.Tarea

@Database(entities = [Tarea::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun tareaDao(): TareaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "studytracker_db"
                )
                    .fallbackToDestructiveMigration() // <--- AGREGA ESTA LÍNEA
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}