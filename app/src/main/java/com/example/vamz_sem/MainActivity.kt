package com.example.vamz_sem

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.vamz_sem.databinding.ActivityMainBinding
import com.example.vamz_sem.filmy.FilmyData
import com.example.vamz_sem.filmy.GlobalViewModel


/**
 * Hlavná aktivita aplikácie.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var drawer: DrawerLayout
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var globalViewModel : GlobalViewModel
    /**
     * Metóda onCreate sa volá pri vytvorení aktivity.
     *
     * @param savedInstanceState Uchováva stav aktivity v prípade obnovenia.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        globalViewModel = ViewModelProvider(this)[GlobalViewModel::class.java]
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController=Navigation.findNavController(this,R.id.activity_main_nav_host_fragment)
        setupWithNavController(binding.bottomNavigationView,navController)


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



        Log.d("FilmDataNapln","DataZfilmu")

        // Vytvorenie a inicializácia objektov FilmyData pre rôzne filmy
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

        val film8 = FilmyData(
            "Iron Man",
            R.drawable.ironmam1,
            "Jon Favreau",
            "Mark Fergus, Hawk Ostby, Art Marcum, Matt Holloway",
            "Robert Downey Jr., Gwyneth Paltrow, Terrence Howard, Jeff Bridges",
            "English",
            "USA",
            "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight against evil forces and becomes the superhero Iron Man.",
            "Action, Adventure, Sci-Fi",
            ""
        )

        val film9 = FilmyData(
            "Star Wars: Episode IV - A New Hope",
            R.drawable.starwars4,
            "George Lucas",
            "George Lucas",
            "Mark Hamill, Harrison Ford, Carrie Fisher, Alec Guinness",
            "English",
            "USA",
            "Luke Skywalker joins forces with a Jedi Knight, a cocky pilot, a Wookiee, and two droids to save the galaxy from the Empire's world-destroying battle station, while also attempting to rescue Princess Leia from the mysterious Darth Vader.",
            "Action, Adventure, Fantasy",
            ""
        )

        val film10 = FilmyData(
            "Toy Story",
            R.drawable.toystory1,
            "John Lasseter",
            "John Lasseter, Pete Docter, Andrew Stanton, Joe Ranft, Joss Whedon",
            "Tom Hanks, Tim Allen, Don Rickles, Jim Varney",
            "English",
            "USA",
            "A cowboy doll is profoundly threatened and jealous when a new spaceman figure supplants him as top toy in a boy's room.",
            "Animation, Adventure, Comedy",
            ""
        )

        val film11 = FilmyData(
            "The Avengers",
            R.drawable.avengers,
            "Joss Whedon",
            "Joss Whedon, Zak Penn",
            "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth, Scarlett Johansson, Jeremy Renner",
            "English",
            "USA",
            "Earth's mightiest heroes must come together and learn to fight as a team to stop the mischievous Loki and his alien army from enslaving humanity.",
            "Action, Adventure, Sci-Fi",
            ""
        )

        val film12 = FilmyData(
            "Indiana Jones: Raiders of the Lost Ark",
            R.drawable.indianajohneslostark,
            "Steven Spielberg",
            "Lawrence Kasdan",
            "Harrison Ford, Karen Allen, Paul Freeman",
            "English",
            "USA",
            "Archaeologist and adventurer Indiana Jones is hired by the U.S. government to find the Ark of the Covenant before the Nazis.",
            "Action, Adventure",
            ""
        )

        val film13 = FilmyData(
            "Finding Nemo",
            R.drawable.findingnemo,
            "Andrew Stanton, Lee Unkrich",
            "Andrew Stanton, Bob Peterson, David Reynolds",
            "Albert Brooks, Ellen DeGeneres, Alexander Gould",
            "English",
            "USA",
            "After his son is captured in the Great Barrier Reef and taken to Sydney, a timid clownfish sets out on a journey to bring him home.",
            "Animation, Adventure, Comedy",
            ""
        )

        val film14 = FilmyData(
            "Spider-Man: Homecoming",
            R.drawable.spidermanhomecoming,
            "Jon Watts",
            "Jonathan Goldstein, John Francis Daley, Jon Watts, Christopher Ford, Chris McKenna, Erik Sommers",
            "Tom Holland, Michael Keaton, Robert Downey Jr., Marisa Tomei",
            "English",
            "USA",
            "Peter Parker balances his life as an ordinary high school student in Queens with his superhero alter-ego Spider-Man, and finds himself on the trail of a new menace prowling the skies of New York City.",
            "Action, Adventure, Sci-Fi",
            ""
        )

        val film15 = FilmyData(
            "Star Wars: Episode VI - Return of the Jedi",
            R.drawable.starwars6,
            "Richard Marquand",
            "Lawrence Kasdan, George Lucas",
            "Mark Hamill, Harrison Ford, Carrie Fisher, Billy Dee Williams",
            "English",
            "USA",
            "After a daring mission to rescue Han Solo from Jabba the Hutt, the Rebels dispatch to Endor to destroy the second Death Star. Meanwhile, Luke Skywalker struggles to bring Darth Vader back to the light side of the Force.",
            "Action, Adventure, Fantasy",
            ""
        )

        val film16 = FilmyData(
            "Frozen",
            R.drawable.frozen,
            "Chris Buck, Jennifer Lee",
            "Jennifer Lee, Chris Buck, Shane Morris",
            "Kristen Bell, Idina Menzel, Jonathan Groff, Josh Gad",
            "English",
            "USA",
            "When the newly crowned Queen Elsa accidentally uses her power to turn things into ice to curse her home in infinite winter, her sister Anna teams up with a mountain man, his playful reindeer, and a snowman to change the weather condition.",
            "Animation, Adventure, Comedy",
            ""
        )

        val film17 = FilmyData(
            "Guardians of the Galaxy",
            R.drawable.guardiansofthegalaxy,
            "James Gunn",
            "James Gunn, Nicole Perlman",
            "Chris Pratt, Zoe Saldana, Dave Bautista, Vin Diesel, Bradley Cooper",
            "English",
            "USA",
            "A group of intergalactic criminals is forced to work together to stop a fanatical warrior from taking control of the universe.",
            "Action, Adventure, Comedy",
            ""
        )

        val film18 = FilmyData(
            "Avengers: Infinity War",
            R.drawable.avengersinfinitywar,
            "Anthony Russo, Joe Russo",
            "Christopher Markus, Stephen McFeely",
            "Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans, Scarlett Johansson",
            "English",
            "USA",
            "The Avengers and their allies must be willing to sacrifice all in an attempt to defeat the powerful Thanos before his blitz of devastation and ruin puts an end to the universe.",
            "Action, Adventure, Fantasy",
            ""
        )

        val film19 = FilmyData(
            "Star Wars: Episode V - The Empire Strikes Back",
            R.drawable.starwars5,
            "Irvin Kershner",
            "Leigh Brackett, Lawrence Kasdan, George Lucas",
            "Mark Hamill, Harrison Ford, Carrie Fisher, Billy Dee Williams",
            "English",
            "USA",
            "After the rebels are overpowered by the Empire on the ice planet Hoth, Luke Skywalker begins Jedi training with Yoda, while his friends are pursued by Darth Vader.",
            "Action, Adventure, Fantasy",
            ""
        )

        val film20 = FilmyData(
            "Thor: Ragnarok",
            R.drawable.thorragnarok,
            "Taika Waititi",
            "Eric Pearson, Craig Kyle, Christopher L. Yost",
            "Chris Hemsworth, Tom Hiddleston, Cate Blanchett, Mark Ruffalo",
            "English",
            "USA",
            "Imprisoned on the planet Sakaar, Thor must race against time to return to Asgard and stop Ragnarok, the destruction of his world, at the hands of the powerful and ruthless villain Hela.",
            "Action, Adventure, Comedy",
            ""
        )

        val film21 = FilmyData(
            "Avengers: Endgame",
            R.drawable.avengersendgame,
            "Anthony Russo, Joe Russo",
            "Christopher Markus, Stephen McFeely",
            "Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth, Scarlett Johansson",
            "English",
            "USA",
            "After the devastating events of Avengers: Infinity War, the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
            "Action, Adventure, Drama",
            ""
        )

        /*
        lifecycleScope.launch {
            globalViewModel.database.insertFilmyData(film1)
            globalViewModel.database.insertFilmyData(film2)
            globalViewModel.database.insertFilmyData(film3)
            globalViewModel.database.insertFilmyData(film4)
            globalViewModel.database.insertFilmyData(film5)
            globalViewModel.database.insertFilmyData(film6)
            globalViewModel.database.insertFilmyData(film7)
            globalViewModel.database.insertFilmyData(film8)
            globalViewModel.database.insertFilmyData(film9)
            globalViewModel.database.insertFilmyData(film10)
            globalViewModel.database.insertFilmyData(film11)
            globalViewModel.database.insertFilmyData(film12)
            globalViewModel.database.insertFilmyData(film13)
            globalViewModel.database.insertFilmyData(film14)
            globalViewModel.database.insertFilmyData(film15)
            globalViewModel.database.insertFilmyData(film16)
            globalViewModel.database.insertFilmyData(film17)
            globalViewModel.database.insertFilmyData(film18)
            globalViewModel.database.insertFilmyData(film19)
            globalViewModel.database.insertFilmyData(film20)
            globalViewModel.database.insertFilmyData(film21)
        }*/
    }

}