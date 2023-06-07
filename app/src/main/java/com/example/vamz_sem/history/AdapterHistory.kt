package com.example.vamz_sem.history


import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FilmObrItemBinding
import com.example.vamz_sem.databinding.ListItemBinding
import com.example.vamz_sem.filmy.FilmViewHolder
import com.example.vamz_sem.filmy.FilmyData
import com.example.vamz_sem.filmy.GlobalViewModel

class AdapterHistory(
    var historyFilmy: List<FilmyData>,
    var globalViewModel: GlobalViewModel
) : RecyclerView.Adapter<HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(from, parent, false)
        historyFilmy = historyFilmy.sortedBy { it.id }
        return HistoryViewHolder(binding, globalViewModel)
    }

    override fun getItemCount(): Int = historyFilmy.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindFilm(historyFilmy[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(dataSet: List<FilmyData>) {
        this.historyFilmy = dataSet
        notifyDataSetChanged()
    }

}

class HistoryViewHolder(
    var filmListBinding: ListItemBinding,
    var globalViewModel: GlobalViewModel
) : RecyclerView.ViewHolder(filmListBinding.root) {
    fun bindFilm(film: FilmyData) {
        filmListBinding.listImage.setImageResource(film.filmImage!!)
        filmListBinding.titleListId.text = film.title

        filmListBinding.listImage.setOnClickListener {
            globalViewModel.globalFilmData = film
            globalViewModel.backFragmentId = R.id.historyFragment
            it.findNavController().navigate(R.id.filmyDetailFragment)
        }

    }

}