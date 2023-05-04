package com.example.vamz_sem.filmy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentFilmyBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class FilmyFragment : BaseFragment<FragmentFilmyBinding, FilmyFragmentViewModel>() {
    private lateinit var bottomNavigationView : BottomNavigationView
    override fun getViewModel(): Class<FilmyFragmentViewModel> = FilmyFragmentViewModel::class.java

    override fun getFragmentView(): Int = R.layout.fragment_filmy

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)

        //bottomNavigationView = view.findViewById(R.id.bottomNavigationView)!!
        //bottomNavigationView.visibility = View.INVISIBLE
        AdapterFilmy(filmList,globalViewModel).apply {
            binding.filmyRecycler.layoutManager = layoutManager
            binding.filmyRecycler.adapter = this
        }
    }

}

class FilmyFragmentViewModel() :ViewModel(){

}