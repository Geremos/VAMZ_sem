package com.example.vamz_sem.Filmy

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vamz_sem.*
import com.example.vamz_sem.databinding.FragmentFilmyBinding

class FilmyFragment : Fragment(), FilmClickListener {
    private lateinit var binding : FragmentFilmyBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = FragmentFilmyBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFilmyBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val filmyActivity = this
        val layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        val globalViewModel = ViewModelProvider(this)[GlobalViewModel::class.java]

        Adapter_Filmy(filmList,filmyActivity,globalViewModel).apply {
            binding.filmyRecycler.layoutManager = layoutManager
            binding.filmyRecycler.adapter = this
            updateDataSet(filmList)
        }
    }

    override fun onClick(film: FilmyData) {
        val intent = Intent(context, FilmyDetailActivity::class.java)
        intent.putExtra(EXTRA_FILM,film.id)
        startActivity(intent)
    }
}