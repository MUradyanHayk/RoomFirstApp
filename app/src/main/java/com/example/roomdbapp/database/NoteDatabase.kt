package com.example.roomdbapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var instance: NoteDatabase? = null
        private val LOCK = Any()

//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance?: createDatabase(context).also { instance = it }
//        }

        operator fun invoke(context: Context): NoteDatabase? {
            if (instance == null) {
                synchronized(LOCK) {
                    if (instance == null) {
                        instance = createDatabase(context)
                    }
                }
            }
            return instance
        }

        private fun createDatabase(context: Context): NoteDatabase {
            return Room.databaseBuilder(context.applicationContext, NoteDatabase::class.java, "note_db").allowMainThreadQueries().build()
        }
    }
}