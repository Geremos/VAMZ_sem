
package com.example.vamz_sem.filmy

import android.Manifest
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.vamz_sem.MainActivity
import com.example.vamz_sem.R
import kotlinx.coroutines.*
/**
 * BroadcastReceiver pre zobrazenie notifikácií v danom čase s náhodnými filmami.
 */
class RandomFilmBrReceiver() : BroadcastReceiver() {
    /**
     * Spracovanie prijatej aktivity.
     * @param context Aktuálny kontext.
     * @param intent Prijatej aktivity.
     */
    override fun onReceive(context: Context?, intent: Intent?) {
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

        val pomIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            pomIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
        Log.d("TimeNotification", "sent $filmyData")
        val addIntent = Intent(context, AddToMyListReceiver::class.java)
            .putExtra("title",title)
            .putExtra("filmImage",filmImage)
            .putExtra("director", director)
            .putExtra("writers",writers)
            .putExtra("cast",cast)
            .putExtra("language",language)
            .putExtra("Country", country)
            .putExtra("plot", plot)
            .putExtra("genre", genre)
            .putExtra("id",id)
            .putExtra("instanceId", 2)

        val pendingIntentAdd = PendingIntent.getBroadcast(context,System.currentTimeMillis().toInt(),addIntent,PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(context!!, "1")
            .setSmallIcon(R.drawable.ic_film_notification)
            .setContentTitle("Film of the Day")
            .setContentText(title)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(plot)
            )
            .addAction(R.drawable.ic_film_notification,"Add to MyList",pendingIntentAdd)
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        Log.d("TimeNotification", "sent $filmyData")
        notificationManager.notify(1,builder.build())
    }
}
