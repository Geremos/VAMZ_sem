package com.example.vamz_sem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.vamz_sem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vytvorFilmy()
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController=Navigation.findNavController(this,R.id.activity_main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView,navController)

    }

    private fun vytvorFilmy() {
        Log.d("FilmDataNapln","DataZfilmu")
        var film1 = FilmyData("Avatar: The Way of Water ",
            R.drawable.avatar2,
            listOf("James Cameron"),
            listOf("James Cameron", "Rick Jaffa", "Amanda Silver", "Shane Salerno", "Josh Friedman"),
            listOf("Sam Worthington","Zoe Saldaña","Sigourney Weaver","Stephen Lang","Kate Winslet","Cliff Curtis"),
            "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure."
        )
        filmList.add(film1)

        var film2 = FilmyData("How to Train Your Dragon",
            R.drawable.howtotrainyourdrgon1,
            listOf("Chris Sanders"),
            listOf("Chris Sanders","Dean DeBlois","Cressida Cowell","William Davies"),
            listOf("Jay Baruchel","Gerard Butler","Craig Ferguson","America Ferrera","Jonah Hill","Christopher Mintz-Plasse"),
            "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father."
        )
        filmList.add(film2)

        var film3 = FilmyData("Despicable Me",
            R.drawable.despicableme1,
            listOf("Pierre Coffin","Chris Renaud"),
            listOf("Sergio Pablos"),
            listOf("Steve Carell","Miranda Cosgrove","Dana Gaier","Elsie Fisher","Jason Segel","Russell Brand"),
            "Villainous Gru lives up to his reputation as a despicable, deplorable and downright unlikable guy when he hatches a plan to steal the moon from the sky. But he has a tough time staying on task after three orphans land in his care."
            )
        filmList.add(film3)

        var film4 = FilmyData("Johnny English",
            R.drawable.johnyenglish1,
            listOf("Peter Howitt"),
            listOf("Neal Purvis","William Davies","Robert Wade"),
            listOf("Rowan Atkinson","Natalie Imbruglia","Ben Miller","John Malkovich","Greg Wise"),
            "A lowly pencil pusher working for MI7, Johnny English is suddenly promoted to super spy after Agent One is assassinated and every other agent is blown up at his funeral. When a billionaire entrepreneur sponsors the exhibition of the Crown Jewels—and the valuable gems disappear on the opening night and on English's watch—the newly-designated agent must jump into action to find the thief and recover the missing gems."
        )
        filmList.add(film4)

        var film5 = FilmyData("Pirates of the Caribbean: The Curse of the Black Pearl",
            R.drawable.pirates1,
            listOf("Gore Verbinski"),
            listOf("Gore Verbinski","Ted Elliott","Terry Rossio"),
            listOf("Johnny Depp","Orlando Bloom","Keira Knightley","Geoffrey Rush","Jack Davenport","Jonathan Pryce","Lee Arenberg","Mackenzie Crook"),
            "Jack Sparrow, a freewheeling 18th-century pirate, quarrels with a rival pirate bent on pillaging Port Royal. When the governor's daughter is kidnapped, Sparrow decides to help the girl's love save her."
        )
        filmList.add(film5)

        var film6 = FilmyData("Star Wars: Episode III - Revenge of the Sith ",
            R.drawable.starwars3,
            listOf("George Lucas"),
            listOf("George Lucas"),
            listOf("Hayden Christensen","Ewan McGregor","Natalie Portman","Ian McDiarmid","Samuel L. Jackson","Jimmy Smits","Frank Oz"),
            "The evil Darth Sidious enacts his final plan for unlimited power -- and the heroic Jedi Anakin Skywalker must choose a side."
        )
        filmList.add(film6)

        var film7 = FilmyData("Transformers",
            R.drawable.transformers1,
            listOf("Michael Bay"),
            listOf("Alex Kurtzman","Roberto Orci","John Rogers"),
            listOf("Shia LaBeouf","Megan Fox","Josh Duhamel","Tyrese Gibson","Rachael Taylor","John Turturro","Peter Cullen"),
            "Young teenager Sam Witwicky becomes involved in the ancient struggle between two extraterrestrial factions of transforming robots – the heroic Autobots and the evil Decepticons. Sam holds the clue to unimaginable power and the Decepticons will stop at nothing to retrieve it."
        )
        filmList.add(film7)

        filmList.add(film1)
        filmList.add(film2)
        filmList.add(film3)
        filmList.add(film4)
        filmList.add(film5)
        filmList.add(film6)
        filmList.add(film7)
    }
}