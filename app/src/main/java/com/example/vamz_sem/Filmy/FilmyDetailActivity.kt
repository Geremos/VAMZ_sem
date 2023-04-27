package com.example.vamz_sem.Filmy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.vamz_sem.databinding.ActivityFilmyDetailBinding

class FilmyDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFilmyDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilmyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filmID = intent.getIntExtra(EXTRA_FILM,-1)
        val film = filmFromID(filmID)
        if(film != null){
            binding.imageDetailId.setImageResource(film.filmImage!!)
            binding.titleDetailId.text = film.title
            binding.directorDetailId.text = film.director.toString()
            binding.writersDetailId.text = film.writers.toString()
            binding.castDetailId.text = film.cast.toString()
            binding.plotDetailId.text = film.plot
        }
    }

    private fun formatText(header: String, list : ArrayList<String>) : String {

    }

    private fun filmFromID(filmID: Int): FilmyData? {
        for(film in filmList){
            if(film.id == filmID){
                return film
            }
        }
        return null
    }
}