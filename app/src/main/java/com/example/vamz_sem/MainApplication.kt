package com.example.vamz_sem

import android.app.Application
import android.content.Context
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
/**
 * Hlavná aplikácia, ktorá rozširuje triedu `Application`.
 */
class MainApplication : Application() {
    companion object {
        /**
     * Spúšťa zobrazenie Toastu so zadanými parametrami.
     *
     * @param context Kontext aplikácie.
     * @param icon    ID ikony.
     * @param text    Textový obsah Toastu.
     */
        fun showToast(context: Context, icon: Int, text : String){
            val toast = Toast(context)
            val inflater: LayoutInflater = LayoutInflater.from(context)
            val layout: View = inflater.inflate(R.layout.custom_toast, null)
            layout.findViewById<TextView>(R.id.toast_text).text = text
            layout.findViewById<ImageView>(R.id.toast_image).setImageResource(icon)
            toast.view = layout
            toast.show()
        }
        /**
         * Inštancia databázy pre filmy.
         */
        lateinit var database: FilmyDatabase
            private set
    }

    /**
     * Metóda onCreate sa volá pri spustení aplikácie.
     */
    override fun onCreate() {
        super.onCreate()
        // Spustenie Koin frameworku
        startKoin {
            // Logovanie Koin do Android loggera
            androidLogger()
            // Referencia na Android kontext
            androidContext(this@MainApplication)
            // Načítanie modulov
            modules(vamzKoin)
        }

        // Vytvorenie inštancie databázy
        database = Room.databaseBuilder(
            applicationContext,
            FilmyDatabase::class.java, "MyFilmDatabase.db"
        ).build()
    }
}