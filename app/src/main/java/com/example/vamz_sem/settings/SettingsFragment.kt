package com.example.vamz_sem.settings

import android.Manifest
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.fragment.findNavController
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.MainActivity
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentSettingsBinding
import com.example.vamz_sem.filmy.AddToMyListReceiver
import com.example.vamz_sem.filmy.FilmyData
import com.example.vamz_sem.filmy.RandomFilmBrReceiver
import kotlin.random.Random


class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    private lateinit var alarmManager: AlarmManager

    override fun getFragmentView(): Int {
        return R.layout.fragment_settings
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateData()

        binding.notificationButton.setOnClickListener {
            sendNotification()
        }

        binding.randomfilmbutton.setOnClickListener {
            globalViewModel.globalFilmData = randomFilm()
            globalViewModel.backFragmentId = R.id.settingsFragment
            findNavController().navigate(R.id.filmyDetailFragment)
        }

        binding.timePicker.setIs24HourView(true)

        binding.settime.setOnClickListener {
            val randomFilm = randomFilm()
            alarmManager = context?.getSystemService(ALARM_SERVICE) as AlarmManager
            val notificationIntent =
                Intent(context, RandomFilmBrReceiver::class.java)
                    .putExtra("title",randomFilm.title)
                    .putExtra("filmImage",randomFilm.filmImage)
                    .putExtra("director", randomFilm.director)
                    .putExtra("writers",randomFilm.writers)
                    .putExtra("cast",randomFilm.cast)
                    .putExtra("language",randomFilm.language)
                    .putExtra("Country", randomFilm.Country)
                    .putExtra("plot", randomFilm.plot)
                    .putExtra("genre", randomFilm.genre)
                    .putExtra("id",randomFilm.id)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                System.currentTimeMillis().toInt(),
                notificationIntent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val selectedTime = Calendar.getInstance()
            selectedTime.set(Calendar.HOUR_OF_DAY, binding.timePicker.hour)
            selectedTime.set(Calendar.MINUTE, binding.timePicker.minute)
            selectedTime.set(Calendar.SECOND, 0)
            Log.d("TimeOfNotification", selectedTime.time.toString())
            // Set the selected time as the trigger time for the notification
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, selectedTime.timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent)
        }
    }


    private fun sendNotification() {
        val randomFilm = randomFilm()

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )


        Log.d("NotificationRFilm", randomFilm.toString() )
        val addIntent = Intent(context, AddToMyListReceiver::class.java).apply {
            putExtra("title", randomFilm.title)
            putExtra("filmImage", randomFilm.filmImage)
            putExtra("director", randomFilm.director)
            putExtra("writers", randomFilm.writers)
            putExtra("cast", randomFilm.cast)
            putExtra("language", randomFilm.language)
            putExtra("Country", randomFilm.Country)
            putExtra("plot", randomFilm.plot)
            putExtra("genre", randomFilm.genre)
            putExtra("id", randomFilm.id)
        }
        val pendingIntentAdd = PendingIntent.getBroadcast(context,System.currentTimeMillis().toInt(),addIntent,PendingIntent.FLAG_IMMUTABLE)


        val builder = context?.let {
            NotificationCompat.Builder(it, "1")
                .setSmallIcon(R.drawable.ic_film_notification)
                .setContentTitle("Film of the Day")
                .setContentText(randomFilm.title)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(randomFilm.plot)
                )
                .addAction(R.drawable.ic_film_notification,"Add to MyList",pendingIntentAdd)
                // Set the intent that will fire when the user taps the notification
                .setAutoCancel(true)
        }


        if (context?.let {
                ActivityCompat.checkSelfPermission(
                    it,
                    Manifest.permission.POST_NOTIFICATIONS
                )
            } != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        if (builder != null) {
            context?.let { NotificationManagerCompat.from(it) }?.notify(1, builder.build())
        }
    }

    private fun randomFilm(): FilmyData {
        val pomData = ArrayList<FilmyData>()
        for (data in globalViewModel.data.value!!) {
            if (data.list == "") {
                pomData.add(data)
            }
        }
        val pom = pomData[Random.nextInt(0, pomData.size)]
        Log.d("NotificationRFilm", pom.toString() )
        return pom
    }
}