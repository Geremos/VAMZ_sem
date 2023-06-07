package com.example.vamz_sem.filmy

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FilmObrItemBinding

/**
 * Adaptér pre zobrazenie zoznamu filmov.
 *
 * @param filmy zoznam filmov
 * @param globalViewModel inštancia triedy GlobalViewModel
 */
class AdapterFilmy(
    var filmy:List<FilmyData>,
    var globalViewModel: GlobalViewModel
)
    : RecyclerView.Adapter<FilmViewHolder>() {

    /**
     * Vytvára nový objekt ViewHolderu pre jednotlivé položky filmu.
     *
     * @param parent rodičovský ViewGroup
     * @param viewType typ zobrazenia položky
     * @return nový objekt FilmViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = FilmObrItemBinding.inflate(from, parent, false)
        return FilmViewHolder(binding,globalViewModel)
    }

    /**
     * Vráti počet položiek v zozname filmov.
     * @return počet položiek v zozname filmov
     */
    override fun getItemCount(): Int = filmy.size

    /**
     * Vykreslí dáta pre konkrétny ViewHolder na základe jeho pozície.
     *
     * @param holder ViewHolder, do ktorého sa majú vykresliť dáta
     * @param position pozícia položky v zozname
     */
    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bindFilm(filmy[position])
    }

    /**
     * Aktualizuje dáta v zozname filmov a notifikuje adapter o zmene.
     *
     * @param dataSet nový zoznam filmov
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(dataSet: List<FilmyData>) {
        this.filmy = dataSet
        notifyDataSetChanged()
    }
}

/**
 * ViewHolder pre zobrazenie jednej položky filmu.
 *
 * @param filmBinding objekt prepojenia s layoutom jednej položky filmu
 * @param globalViewModel inštancia triedy GlobalViewModel
 */
class FilmViewHolder(
    var filmBinding: FilmObrItemBinding,
    var globalViewModel: GlobalViewModel
) : RecyclerView.ViewHolder(filmBinding.root) {

    /**
     * Nastavuje hodnoty pre zobrazenie jednej položky filmu.
     *
     * @param film dáta o filme
     */
    fun bindFilm(film: FilmyData) {
        filmBinding.imageId.setImageResource(film.filmImage!!)
        filmBinding.cardViewFilmyId.setOnClickListener {
            globalViewModel.globalFilmData = film
            globalViewModel.backFragmentId = R.id.filmyFragment
            it.findNavController().navigate(R.id.filmyDetailFragment)
        }

    }
}