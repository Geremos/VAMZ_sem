package com.example.vamz_sem.filmy

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentFilmyBinding
import com.example.vamz_sem.databinding.FragmentFilmyDetailBinding

class FilmyDetailFragment : BaseFragment<FragmentFilmyDetailBinding, FilmyDetailFragmentViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmyDetailBinding.inflate(layoutInflater)
        binding.backIcon.setOnClickListener {
            Log.d("logBackFragmentId","${globalViewModel.backFragmentId}")
            findNavController().navigate(globalViewModel.backFragmentId)
        }
        val film = globalViewModel.globalFilmData

        Log.d("logFilmDataDetail","$film")
        if(film.id != null){
            binding.imageDetailId.setImageResource(film.filmImage!!)
            binding.titleDetailId.text = film.title
            binding.directorDetailId.text = formatText("Director",film.director)
            binding.writersDetailId.text = formatText("Writers",film.writers)
            binding.castDetailId.text = formatText("Cast",film.cast)
            binding.plotDetailId.text = film.plot
        }

        return binding.root
    }

    private fun formatText(header: String, list: List<String>?) : String {
        var data = "$header: "
        if (list != null) {
            for (film in list) {
                data += " $film, "
            }
        }
        return data
    }

    override fun getViewModel(): Class<FilmyDetailFragmentViewModel> = FilmyDetailFragmentViewModel::class.java

        override fun getFragmentView(): Int = R.layout.fragment_filmy_detail
}

class FilmyDetailFragmentViewModel : ViewModel()