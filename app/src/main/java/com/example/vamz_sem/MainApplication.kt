package com.example.vamz_sem

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.vamz_sem.filmy.FilmyDatabase
import com.example.vamz_sem.koin.vamzKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    companion object {
        lateinit var database: FilmyDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(vamzKoin)
        }
        Log.d("LogDatabase","dostal som sa pred")
        database = Room.databaseBuilder(
            applicationContext,
            FilmyDatabase::class.java, "MyFilmDatabase.db"
        ).build()
        Log.d("LogDatabase","dostal som sa sem")
    }
}