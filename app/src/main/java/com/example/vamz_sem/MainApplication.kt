package com.example.vamz_sem

import android.app.Application
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import com.example.vamz_sem.filmy.FilmyDatabase
import com.example.vamz_sem.koin.vamzKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    companion object {
        fun showToast(context: Context, icon: Int, text : String){
            val toast = Toast(context)
            val inflater: LayoutInflater = LayoutInflater.from(context)
            val layout: View = inflater.inflate(R.layout.custom_toast, null)
            layout.findViewById<TextView>(R.id.toast_text).text = text
            layout.findViewById<ImageView>(R.id.toast_image).setImageResource(icon)
            toast.view = layout
            toast.show()
        }
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
        database = Room.databaseBuilder(
            applicationContext,
            FilmyDatabase::class.java, "MyFilmDatabase.db"
        ).build()
    }
}