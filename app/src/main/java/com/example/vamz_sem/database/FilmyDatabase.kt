package com.example.vamz_sem.database

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Trieda reprezentuje databázu pre filmy.
 *
 * @property filmyDataDao Objekt typu [FilmyDao], cez ktorý je možné pristupovať k dátam v databáze.
 */
@Database(entities = [FilmyData::class], version = 2)
abstract class FilmyDatabase: RoomDatabase() {
    abstract fun filmyDataDao() : FilmyDao
}