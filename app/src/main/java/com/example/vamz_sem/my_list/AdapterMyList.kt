package com.example.vamz_sem.my_list


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.ListItemBinding
import com.example.vamz_sem.filmy.FilmyData
import com.example.vamz_sem.filmy.GlobalViewModel

class AdapterMyList(
    var myListFilmy: List<FilmyData>,
    var globalViewModel: GlobalViewModel
) : RecyclerView.Adapter<MyListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyListViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(from, parent, false)
        return MyListViewHolder(binding, globalViewModel)
    }

    override fun getItemCount(): Int = myListFilmy.size

    override fun onBindViewHolder(holder: MyListViewHolder, position: Int) {
        holder.bindFilm(myListFilmy[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(dataSet: List<FilmyData>) {
        this.myListFilmy = dataSet
        notifyDataSetChanged()
    }

}

class MyListViewHolder(
    var filmListBinding: ListItemBinding,
    var globalViewModel: GlobalViewModel
) : RecyclerView.ViewHolder(filmListBinding.root) {
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