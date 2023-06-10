package com.example.vamz_sem.settings

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.vamz_sem.MainApplication
import com.example.vamz_sem.database.FilmyData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * BroadcastReceiver pre pridanie filmu do zoznamu "Môj zoznam".
 */
class AddToMyListReceiver : BroadcastReceiver() {

    /**
     * Metóda volaná pri stlačení tlačidla na notifikácií
     *
     * @param context kontext aktivity
     * @param intent prijatý intent
     */
    override fun onReceive(context: Context?, intent: Intent?) {
        val coroutineScope = CoroutineScope(Dispatchers.Main)
            val title = intent!!.getStringExtra("title")
            val filmImage = intent.getIntExtra("filmImage", 0)
            val director = intent.getStringExtra("director")
            val writers = intent.getStringExtra("writers")
            val cast = intent.getStringExtra("cast")
            val language = intent.getStringExtra("language")
            val country = intent.getStringExtra("Country")
            val plot = intent.getStringExtra("plot")
            val genre = intent.getStringExtra("genre")
            val id = intent.getIntExtra("id", 0)
            val filmyData = FilmyData(
                title,
                filmImage,
                director,
                writers,
                cast,
                language,
                country,
                plot,
                genre,
                "myList",
                id
            )
            Log.d("NotificationUpdateSent", filmyData.toString())
            coroutineScope.launch {
                MainApplication.database.filmyDataDao().updateData(filmyData)
            }
    }
}