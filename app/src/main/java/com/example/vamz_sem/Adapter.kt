package com.example.vamz_sem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.databinding.FilmObrItemBinding

class Adapter(private val filmy: List<FilmyData>) : RecyclerView.Adapter<FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = FilmObrItemBinding.inflate(from,parent,false)
        return FilmViewHolder(binding)
    }

    override fun getItemCount(): Int = filmList.size
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
         holder.bindFilm(filmList[position])
    }

}