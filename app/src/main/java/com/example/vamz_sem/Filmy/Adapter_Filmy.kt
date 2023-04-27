package com.example.vamz_sem

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.Filmy.FilmClickListener
import com.example.vamz_sem.Filmy.FilmyData
import com.example.vamz_sem.Filmy.GlobalViewModel
import com.example.vamz_sem.databinding.FilmObrItemBinding

class Adapter_Filmy(
    var filmy: List<FilmyData>,
    var clickListener: FilmClickListener,
    var globalViewModel: GlobalViewModel
)
    : RecyclerView.Adapter<FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = FilmObrItemBinding.inflate(from, parent, false)
        return FilmViewHolder(binding,clickListener,globalViewModel)
    }

    override fun getItemCount(): Int = filmy.size
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bindFilm(filmy[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(dataSet: List<FilmyData>) {
        this.filmy = dataSet
        notifyDataSetChanged()
    }
}

class FilmViewHolder(
    var filmBinding: FilmObrItemBinding,
    var clickListener: FilmClickListener,
    var globalViewModel: GlobalViewModel
) : RecyclerView.ViewHolder(filmBinding.root) {
    fun bindFilm(film: FilmyData) {
        filmBinding.imageId.setImageResource(film.filmImage!!)
        //film.filmImage?.let { filmBinding.imageId.setImageResource(it) }
        filmBinding.cardViewFilmyId.setOnClickListener {
            globalViewModel.globalFilmData = film
            clickListener.onClick(film)
        }

    }
}