package com.example.vamz_sem

import androidx.databinding.adapters.CardViewBindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.FilmyData
import com.example.vamz_sem.databinding.FilmObrItemBinding

class FilmViewHolder(private val filmBinding : FilmObrItemBinding) : RecyclerView.ViewHolder(filmBinding.root) {
    fun bindFilm(film : FilmyData) {
        filmBinding.imageId.setImageResource(film.filmImage!!)
    }
}