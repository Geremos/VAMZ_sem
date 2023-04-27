package com.example.vamz_sem.filmy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentFilmyDetailBinding

class FilmyDetailFragment : Fragment(), FilmDetailClickListener {
    private lateinit var binding : FragmentFilmyDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentFilmyDetailBinding.inflate(layoutInflater)

        val globalViewModel = ViewModelProvider(this)[GlobalViewModel::class.java]

        val film = globalViewModel.globalFilmData

        Log.d("logFilmDataDetail","${film}")
        if(film.id != null){
            binding.imageDetailId.setImageResource(film.filmImage!!)
            binding.titleDetailId.text = film.title
            binding.directorDetailId.text = film.director.toString()//formatText("Director",film.director)
            binding.writersDetailId.text = film.writers.toString()//formatText("Writers",film.writers)
            binding.castDetailId.text = film.cast.toString()//formatText("Cast",film.cast)
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
        binding.backIcon.setOnClickListener { findNavController().navigate(R.id.back_to_filmy) }
        return binding.root
    }
}