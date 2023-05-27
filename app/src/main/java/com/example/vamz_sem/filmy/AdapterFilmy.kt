package com.example.vamz_sem.filmy

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FilmObrItemBinding

class AdapterFilmy(
    var filmy: ArrayList<FilmyData>,
    var globalViewModel: GlobalViewModel
)
    : RecyclerView.Adapter<FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = FilmObrItemBinding.inflate(from, parent, false)
        return FilmViewHolder(binding,globalViewModel)
    }

    override fun getItemCount(): Int = filmy.size
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        filmy.let { holder.bindFilm(it[position]) }
    }

}

class FilmViewHolder(
    var filmBinding: FilmObrItemBinding,
    var globalViewModel: GlobalViewModel
) : RecyclerView.ViewHolder(filmBinding.root) {
    fun bindFilm(film: FilmyData) {
        filmBinding.imageId.setImageResource(film.filmImage!!)
        filmBinding.cardViewFilmyId.setOnClickListener {
            globalViewModel.globalFilmData = film
            globalViewModel.backFragmentId = R.id.filmyFragment
            it.findNavController().navigate(R.id.filmyDetailFragment)
        }

    }
}