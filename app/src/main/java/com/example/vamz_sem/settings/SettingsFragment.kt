package com.example.vamz_sem.settings


import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle

import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.MainActivity
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentSettingsBinding
import com.example.vamz_sem.filmy.FilmyData
import kotlinx.coroutines.launch
import kotlin.random.Random


class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsFragmentViewModel>() {


    override fun getViewModel(): Class<SettingsFragmentViewModel> {
        return SettingsFragmentViewModel::class.java
    }

    override fun getFragmentView(): Int {
        return R.layout.fragment_settings
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateData()
        binding.notificationButton.setOnClickListener {
            sendNotification()
        }
    }

    fun sendNotification() {
        val pomData = ArrayList<FilmyData>()
        for (data in globalViewModel.data.value!!) {
            if (data.list == "") {
                pomData.add(data)
            }
        }

        val randomFilm = pomData[Random.nextInt(0, pomData.size)]
        val rFilmPictureBitmap = BitmapFactory.decodeFile(randomFilm.filmImage.toString())

        val intent = Intent(requireContext(), MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            1,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name, importance).apply {
                description = descriptionText
            }
            val notificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val addIntent = Intent(requireContext(), SettingsFragment::class.java).apply {
            randomFilm.list = "myList"
            lifecycleScope.launch {
                globalViewModel.database.updateData(randomFilm)
            }
        }

        val pendingIntentAdd = PendingIntent.getBroadcast(context,1,addIntent,PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(requireContext(), "1")
            .setSmallIcon(R.drawable.ic_film_notification)
            .setContentTitle("Film of the Day")
            .setContentText(randomFilm.title)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.ic_film_notification,"Add to my List",pendingIntentAdd)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(randomFilm.plot)
            )
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(rFilmPictureBitmap)

            )
            // Set the intent that will fire when the user taps the notification
            .setAutoCancel(true)

        val notificationManager = NotificationManagerCompat.from(requireContext())


        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(1, builder.build())
    }
}


class SettingsFragmentViewModel() : ViewModel()