package com.example.vamz_sem

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.databinding.FilmObrItemBinding

class Adapter_Filmy(val filmy: List<FilmyData>) : RecyclerView.Adapter<FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = FilmObrItemBinding.inflate(from,parent,false)
        return FilmViewHolder(binding)
    }

    override fun getItemCount(): Int = filmy.size
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
         holder.bindFilm(filmy[position])
    }

}

class FilmViewHolder(val filmBinding : FilmObrItemBinding) : RecyclerView.ViewHolder(filmBinding.root) {
    fun bindFilm(film : FilmyData) {
        Log.d("FilmViewHolder","Bind image ${film.filmImage}")
        filmBinding.imageId.setImageResource(film.filmImage!!)

    }
}