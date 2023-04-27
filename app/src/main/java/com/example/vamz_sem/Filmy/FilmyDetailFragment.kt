package com.example.vamz_sem.Filmy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.vamz_sem.databinding.FragmentFilmyDetailBinding

class FilmyDetailFragment : Fragment() {
    private lateinit var binding : FragmentFilmyDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFilmyDetailBinding.inflate(layoutInflater)

        val globalViewModel = ViewModelProvider(this)[GlobalViewModel::class.java]

        val film = globalViewModel.globalFilmData
        if(film.id != null){
            binding.imageDetailId.setImageResource(film.filmImage!!)
            binding.titleDetailId.text = film.title
            binding.directorDetailId.text = formatText("Director",film.director)
            binding.writersDetailId.text = formatText("Writers",film.writers)
            binding.castDetailId.text = formatText("Cast",film.cast)
            binding.plotDetailId.text = film.plot
        }
    }

    private fun formatText(header: String, list: List<String>?) : String {
        var data = "$header: "
        if (list != null) {
            for (film in list) {
                data = "$film, "
            }
        }
        return data
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmyDetailBinding.inflate(layoutInflater)
        return binding.root
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