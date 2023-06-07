package com.example.vamz_sem.filmy

import androidx.room.Database
import androidx.room.RoomDatabase
/**
 * Trieda reprezentuje databázu pre filmy.
 *
 * @property filmyDataDao Objekt typu [FilmyDao], cez ktorý je možné pristupovať k dátam v databáze.
 */
@Database(entities = [FilmyData::class], version = 1)
abstract class FilmyDatabase: RoomDatabase() {
    abstract fun filmyDataDao() : FilmyDao
}