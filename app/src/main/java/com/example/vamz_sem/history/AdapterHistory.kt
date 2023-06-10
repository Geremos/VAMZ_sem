package com.example.vamz_sem.history


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.ListItemBinding
import com.example.vamz_sem.database.FilmyData
import com.example.vamz_sem.GlobalViewModel
/**
 * Adaptér pre zobrazenie položiek histórie filmov v RecyclerView.
 * @property historyFilmy Zoznam filmových dát pre históriu.
 * @param globalViewModel Referencia na [GlobalViewModel] pre zdieľanie dát medzi fragmentmi.
 */
class AdapterHistory(
    var historyFilmy: List<FilmyData>,
    var globalViewModel: GlobalViewModel
) : RecyclerView.Adapter<HistoryViewHolder>() {
    /**
     * Vytvára a inicializuje [HistoryViewHolder].
     * @param parent Rodičovská ViewGroup, do ktorej sa vloží zobrazenie položky.
     * @param viewType Typ zobrazenia položky (nepoužíva sa).
     * @return Inštancia [HistoryViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(from, parent, false)
        historyFilmy = historyFilmy.sortedBy { it.id }
        return HistoryViewHolder(binding, globalViewModel)
    }
    /**
     * Vráti počet položiek v histórii.
     * @return Počet položiek v histórii.
     */
    override fun getItemCount(): Int = historyFilmy.size
    /**
     * Viazanie dát k zobrazeniu položky.
     * @param holder Inštancia [HistoryViewHolder].
     * @param position Pozícia položky v zozname.
     */
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bindFilm(historyFilmy[position])
    }

}
/**
 * View holder pre položku histórie filmov.
 *
 * @property filmListBinding Viazanie zobrazenia položky v layoute.
 * @property globalViewModel Referencia na [GlobalViewModel] pre zdieľanie dát medzi fragmentmi.
 */
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