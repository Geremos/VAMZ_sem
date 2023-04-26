package com.example.vamz_sem

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vamz_sem.databinding.ActivityMainBinding
import com.example.vamz_sem.databinding.FragmentFilmyBinding

class FilmyFragment : Fragment() {
    private lateinit var binding : FragmentFilmyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Log.d("FilmData","DataZfilmu ${filmList.get(1)}")
        binding = FragmentFilmyBinding.inflate(layoutInflater)
        binding.filmyRecycler.layoutManager = GridLayoutManager(this.context, 2,GridLayoutManager.VERTICAL,false)
        binding.filmyRecycler.adapter = Adapter_Filmy(filmList)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilmyBinding.inflate(layoutInflater)
        binding.filmyRecycler.layoutManager = GridLayoutManager(this.context, 2,GridLayoutManager.VERTICAL,false)
        binding.filmyRecycler.adapter = Adapter_Filmy(filmList)
        return inflater.inflate(R.layout.fragment_filmy, container, false)
    }
}