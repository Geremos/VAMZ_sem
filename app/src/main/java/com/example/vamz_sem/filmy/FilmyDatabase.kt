package com.example.vamz_sem.filmy

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FilmyData::class], version = 1)
abstract class FilmyDatabase: RoomDatabase() {
    abstract fun filmyDataDao() : FilmyDao
}