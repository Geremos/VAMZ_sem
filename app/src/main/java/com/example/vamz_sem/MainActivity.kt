package com.example.vamz_sem

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Xml
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.vamz_sem.databinding.ActivityMainBinding
import com.example.vamz_sem.database.FilmyData
import kotlinx.coroutines.launch
import org.xmlpull.v1.XmlPullParser


/**
 * Hlavná aktivita aplikácie.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var globalViewModel: GlobalViewModel

    /**
     * Metóda onCreate sa volá pri vytvorení aktivity.
     *
     * @param savedInstanceState Uchováva stav aktivity v prípade obnovenia.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        globalViewModel = ViewModelProvider(this)[GlobalViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = Navigation.findNavController(this, R.id.activity_main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView, navController)


        // Nastavenie viditeľnosti komponentu v závislosti od aktuálnej destinácie navigácie
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.filmyDetailFragment -> binding.containerForBotNav.visibility = View.GONE
                else -> binding.containerForBotNav.visibility = View.VISIBLE
            }
        }
        // Vytvorenie notifikačného kanála
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = R.string.channel_name
            val descriptionText = R.string.channel_description
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("1", name.toString(), importance).apply {
                description = descriptionText.toString()
            }
            val notificationManager =
                this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Načítane dát z XML do databázy
        /*val filmydataXML = assets.open("filmy_data_xml.xml")
        val parser: XmlPullParser = Xml.newPullParser()
        parser.setInput(filmydataXML, null)

        for(film in parseFilmyDataXml(parser)){
            lifecycleScope.launch{
                globalViewModel.database.insertFilmyData(film)
            }
        }*/
    }

    /**
     * Metóda pre spracovanie XML pomocou XmlPullParser a vytvorenie zoznamu [FilmyData] objektov.
     *
     * @param parser XmlPullParser objekt pre spracovanie XML dát.
     * @return Zoznam [FilmyData] objektov.
     */
    private fun parseFilmyDataXml(parser: XmlPullParser): List<FilmyData> {
        val filmyDataList = mutableListOf<FilmyData>()
        var title: String? = null
        var filmImage: String? = null
        var director: String? = null
        var writers: String? = null
        var cast: String? = null
        var language: String? = null
        var country: String? = null
        var plot: String? = null
        var genre: String? = null

        while (parser.eventType != XmlPullParser.END_DOCUMENT) {
            if (parser.eventType == XmlPullParser.START_TAG) {
                // Ak ide o začiatočný tag
                when (parser.name) {
                    "title" -> title = parser.nextText()
                    "filmImage" -> filmImage = parser.nextText()
                    "director" -> director = parser.nextText()
                    "writers" -> writers = parser.nextText()
                    "cast" -> cast = parser.nextText()
                    "language" -> language = parser.nextText()
                    "country" -> country = parser.nextText()
                    "plot" -> plot = parser.nextText()
                    "genre" -> genre = parser.nextText()
                }
            } else if (parser.eventType == XmlPullParser.END_TAG && parser.name == "FilmyData") {
                // Ak ide o ukončovací tag pre 'FilmyData'
                val resField = R.drawable::class.java.getDeclaredField(filmImage!!)
                val imageID = resField.getInt(null) // Získanie ID obrázka
                val filmyData = FilmyData(
                    title,
                    imageID,
                    director,
                    writers,
                    cast,
                    language,
                    country,
                    plot,
                    genre,
                    ""
                )
                filmyDataList.add(filmyData)
            }
              parser.next() // Prechod na ďalší element XML
        }
        return filmyDataList
    }

}