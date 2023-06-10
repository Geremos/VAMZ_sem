package com.example.vamz_sem.my_list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.ListItemBinding
import com.example.vamz_sem.database.FilmyData
import com.example.vamz_sem.GlobalViewModel
/**
 * AdapterMyList je adaptér pre RecyclerView v MyListFragment.
 * Slúži na zobrazenie zoznamu filmov v "Mojom zozname".
 *
 * @param myListFilmy [List] zoznam filmov
 * @param globalViewModel [GlobalViewModel] inštancia triedy GlobalViewModel
 */
class AdapterMyList(
    var myListFilmy: List<FilmyData>,
    var globalViewModel: GlobalViewModel
) : RecyclerView.Adapter<MyListViewHolder>() {
    /**
     * Metóda, ktorá vytvára ViewHolder pre jednotlivé položky v RecyclerView.
     * @param parent [ViewGroup] rodičovský view
     * @param viewType [Int] typ položky
     * @return [MyListViewHolder] vytvorený ViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val from = LayoutInflater.from(parent.context)
        myListFilmy = myListFilmy.sortedBy { it.id }
        val binding = ListItemBinding.inflate(from, parent, false)
        return MyListViewHolder(binding, globalViewModel)
    }
    /**
     * Metóda, ktorá vracia počet položiek v zozname.
     * @return [Int] počet položiek v zozname
     */
    override fun getItemCount(): Int = myListFilmy.size
    /**
     * Metóda, ktorá sa volá pri viazaní dát na konkrétny ViewHolder.
     * @param holder [MyListViewHolder] ViewHolder, na ktorý sa viažu dáta
     * @param position [Int] pozícia položky v zozname
     */
    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.bindFilm(myListFilmy[position])
    }

}
/**
 * MyListViewHolder je ViewHolder pre položky v AdapterMyList.
 * Obsahuje metódu bindFilm(), ktorá viaže dáta filmu na jednotlivé views v položke.
 *
 * @param filmListBinding [ListItemBinding] viazanie views v položke
 * @param globalViewModel [GlobalViewModel] pre zdieľanie dát medzi fragmentmi
 */
class MyListViewHolder(
    var filmListBinding: ListItemBinding,
    var globalViewModel: GlobalViewModel
) : RecyclerView.ViewHolder(filmListBinding.root) {
    /**
     * Metóda, ktorá viaže dáta filmu na views v položke.
     * @param film [FilmyData] dáta filmu
     */
    fun bindFilm(film: FilmyData) {
        filmListBinding.listImage.setImageResource(film.filmImage!!)
        filmListBinding.titleListId.text = film.title

        filmListBinding.listImage.setOnClickListener {
            globalViewModel.globalFilmData = film
            globalViewModel.backFragmentId = R.id.myListFragment
            it.findNavController().navigate(R.id.filmyDetailFragment)
        }

    }

}