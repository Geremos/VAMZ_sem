package com.example.vamz_sem

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.room.Room
import com.example.vamz_sem.filmy.FilmyData
import com.example.vamz_sem.filmy.GlobalViewModel
import com.example.vamz_sem.databinding.ActivityMainBinding
import com.example.vamz_sem.filmy.FilmyDatabase
import com.example.vamz_sem.filmy.FilmyFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var globalViewModel : GlobalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        globalViewModel = ViewModelProvider(this)[GlobalViewModel::class.java]
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController=Navigation.findNavController(this,R.id.activity_main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView,navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.filmyDetailFragment -> binding.containerForBotNav.visibility = View.GONE
                else -> binding.containerForBotNav.visibility = View.VISIBLE
            }
        }


        Log.d("FilmDataNapln","DataZfilmu")
        var film1 = FilmyData(
            "Avatar: The Way of Water",
            R.drawable.avatar2,
            "James Cameron",
            "James Cameron, Rick Jaffa, Amanda Silver, Shane Salerno, Josh Friedman",
            "Sam Worthington, Zoe Saldaña, Sigourney Weaver, Stephen Lang, Kate Winslet, Cliff Curtis",
            "English",
            "USA",
            "Set more than a decade after the events of the first film, learn the story of the Sully family (Jake, Neytiri, and their kids), the trouble that follows them, the lengths they go to keep each other safe, the battles they fight to stay alive, and the tragedies they endure.",
            "Action, Adventure, Fantasy",""
        )

        var film2 = FilmyData(
            "How to Train Your Dragon",
            R.drawable.howtotrainyourdrgon1,
            "Chris Sanders",
            "Chris Sanders, Dean DeBlois, Cressida Cowell, William Davies",
            "Jay Baruchel, Gerard Butler, Craig Ferguson, America Ferrera, Jonah Hill, Christopher Mintz-Plasse",
            "English",
            "USA",
            "As the son of a Viking leader on the cusp of manhood, shy Hiccup Horrendous Haddock III faces a rite of passage: he must kill a dragon to prove his warrior mettle. But after downing a feared dragon, he realizes that he no longer wants to destroy it, and instead befriends the beast – which he names Toothless – much to the chagrin of his warrior father.",
            "Animation, Adventure, Family",
            ""
        )

        var film3 = FilmyData(
            "Despicable Me",
            R.drawable.despicableme1,
            "Pierre Coffin, Chris Renaud",
            "Sergio Pablos",
            "Steve Carell, Miranda Cosgrove, Dana Gaier, Elsie Fisher, Jason Segel, Russell Brand",
            "English",
            "USA",
            "Villainous Gru lives up to his reputation as a despicable, deplorable and downright unlikable guy when he hatches a plan to steal the moon from the sky. But he has a tough time staying on task after three orphans land in his care.",
            "Animation, Comedy, Family",
            ""
        )

        var film4 = FilmyData(
            "Johnny English",
            R.drawable.johnyenglish1,
            "Peter Howitt",
            "Neal Purvis, William Davies, Robert Wade",
            "Rowan Atkinson, Natalie Imbruglia, Ben Miller, John Malkovich, Greg Wise",
            "English",
            "USA",
            "A lowly pencil pusher working for MI7, Johnny English is suddenly promoted to super spy after Agent One is assassinated and every other agent is blown up at his funeral. When a billionaire entrepreneur sponsors the exhibition of the Crown Jewels—and the valuable gems disappear on the opening night and on English's watchthe newly-designated agent must jump into action to find the thief and recover the missing gems.",
            "Action, Adventure, Comedy",
            ""
        )

        var film5 = FilmyData(
            "Pirates of the Caribbean: The Curse of the Black Pearl",
            R.drawable.pirates1,
            "Gore Verbinski",
            "Gore Verbinski, Ted Elliott, Terry Rossio",
            "Johnny Depp, Orlando Bloom, Keira Knightley, Geoffrey Rush, Jack Davenport, Jonathan Pryce, Lee Arenberg, Mackenzie Crook",
            "English",
            "USA",
            "Jack Sparrow, a freewheeling 18th-century pirate, quarrels with a rival pirate bent on pillaging Port Royal. When the governor's daughter is kidnapped, Sparrow decides to help the girl's love save her.",
            "Action, Adventure, Fantasy",
            ""
        )

        var film6 = FilmyData(
            "Star Wars: Episode III - Revenge of the Sith",
            R.drawable.starwars3,
            "George Lucas",
            "George Lucas",
            "Hayden Christensen, Ewan McGregor, Natalie Portman, Ian McDiarmid, Samuel L. Jackson, Jimmy Smits, Frank Oz",
            "English",
            "USA",
            "The evil Darth Sidious enacts his final plan for unlimited power—and the heroic Jedi Anakin Skywalker must choose a side.",
            "Action, Adventure, Fantasy",
            ""
        )

        var film7 = FilmyData(
            "Transformers",
            R.drawable.transformers1,
            "Michael Bay",
            "Alex Kurtzman, Roberto Orci, John Rogers",
            "Shia LaBeouf, Megan Fox, Josh Duhamel, Tyrese Gibson, Rachael Taylor, John Turturro, Peter Cullen",
            "English",
            "USA",
            "Young teenager Sam Witwicky becomes involved in the ancient struggle between two extraterrestrial factions of transforming robots—the heroic Autobots and the evil Decepticons. Sam holds the clue to unimaginable power and the Decepticons will stop at nothing to retrieve it.",
            "Action, Adventure, Sci-Fi",
            ""
        )


        /*lifecycleScope.launch {
            globalViewModel.database.insertFilmyData(film1)
            globalViewModel.database.insertFilmyData(film2)
            globalViewModel.database.insertFilmyData(film3)
            globalViewModel.database.insertFilmyData(film4)
            globalViewModel.database.insertFilmyData(film5)
            globalViewModel.database.insertFilmyData(film6)
            globalViewModel.database.insertFilmyData(film7)

            globalViewModel.database.insertFilmyData(film1)
            globalViewModel.database.insertFilmyData(film2)
            globalViewModel.database.insertFilmyData(film3)
            globalViewModel.database.insertFilmyData(film4)
            globalViewModel.database.insertFilmyData(film5)
            globalViewModel.database.insertFilmyData(film6)
            globalViewModel.database.insertFilmyData(film7)

            globalViewModel.database.insertFilmyData(film1)
            globalViewModel.database.insertFilmyData(film2)
            globalViewModel.database.insertFilmyData(film3)
            globalViewModel.database.insertFilmyData(film4)
            globalViewModel.database.insertFilmyData(film5)
            globalViewModel.database.insertFilmyData(film6)
            globalViewModel.database.insertFilmyData(film7)
        }*/
    }

}