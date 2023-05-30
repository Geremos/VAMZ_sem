package com.example.vamz_sem.filmy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentFilmyDetailBinding
import kotlinx.coroutines.launch

class FilmyDetailFragment :
    BaseFragment<FragmentFilmyDetailBinding, FilmyDetailFragmentViewModel>(),
    BottomPanelClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmyDetailBinding.inflate(layoutInflater)
        updateData()
        binding.backIcon.setOnClickListener {
            Log.d("logBackFragmentId", "${globalViewModel.backFragmentId}")
            findNavController().navigate(globalViewModel.backFragmentId)
        }
        val film = globalViewModel.globalFilmData

        binding.bottomPanel.clickListener = this
        if (globalViewModel.globalFilmData.list == "history") {
            binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.history_orange)
        }

        if (globalViewModel.globalFilmData.list == "myList") {
            binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmark_added)
        }

        Log.d("logFilmDataDetail", "$film")
        if (film.id != null) {
            binding.imageDetailId.setImageResource(film.filmImage!!)
            binding.titleDetailId.text = film.title
            binding.directorDetailId.text = "Director:" + film.director
            binding.writersDetailId.text = "Writers:" + film.writers
            binding.castDetailId.text = "Cast:" + film.cast
            binding.plotDetailId.text = "Plot:" + film.plot
        }

        return binding.root
    }

    override fun getViewModel(): Class<FilmyDetailFragmentViewModel> =
        FilmyDetailFragmentViewModel::class.java

    override fun getFragmentView(): Int = R.layout.fragment_filmy_detail
    override fun addToHistory() {
        if (globalViewModel.globalFilmData.list == "myList") {
            Log.d("databaseList", "${globalViewModel.globalFilmData.list}")
            val pomFilm = globalViewModel.data.value?.get(globalViewModel.globalFilmData.id)
            val film = FilmyData(
                pomFilm!!.title,
                pomFilm.filmImage,
                pomFilm.director,
                pomFilm.writers,
                pomFilm.cast,
                pomFilm.language,
                pomFilm.Country,
                pomFilm.plot,
                pomFilm.genre,
                "history",
                pomFilm.id
            )

            lifecycleScope.launch {
                globalViewModel.database.updateData(film)
            }
            updateData()
            globalViewModel.globalFilmData = film
            binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.history_orange)
            binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmarkunchecked)
            Toast.makeText(context, "Addes to History from MyList", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("databaseList", "${globalViewModel.globalFilmData.list}")
            if (globalViewModel.globalFilmData.list != "history") {
                globalViewModel.globalFilmData.id.let { globalViewModel.data.value?.get(it) }?.list =
                    "history"
                lifecycleScope.launch {
                    globalViewModel.database.updateList("history", globalViewModel.globalFilmData.id)
                }
                updateData()
                globalViewModel.globalFilmData.list = "history"
                binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.history_orange)
                Toast.makeText(context, "Addes to History", Toast.LENGTH_SHORT).show()
            } else {
                globalViewModel.globalFilmData.id.let { globalViewModel.data.value?.get(it) }?.list =
                    ""
                lifecycleScope.launch {
                    globalViewModel.database.updateList("", globalViewModel.globalFilmData.id)
                }
                updateData()
                globalViewModel.globalFilmData.list = ""
                Toast.makeText(context, "Removed from history", Toast.LENGTH_SHORT).show()
                binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.icon_history)
            }
        }
    }

    override fun addToMyList() {
        if (globalViewModel.globalFilmData.list == "history") {
            Log.d("databaseList", "${globalViewModel.globalFilmData.list}")
            val pomFilm = globalViewModel.data.value?.get(globalViewModel.globalFilmData.id)
            val film = FilmyData(
                pomFilm!!.title,
                pomFilm.filmImage,
                pomFilm.director,
                pomFilm.writers,
                pomFilm.cast,
                pomFilm.language,
                pomFilm.Country,
                pomFilm.plot,
                pomFilm.genre,
                "myList",
                pomFilm.id
            )

            lifecycleScope.launch {
                globalViewModel.database.updateData(film)
            }
            updateData()
            globalViewModel.globalFilmData = film

            binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmark_added)
            binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.icon_history)
            Toast.makeText(context, "Addes to MyList from History", Toast.LENGTH_SHORT).show()
        } else {
            Log.d("databaseList", "${globalViewModel.globalFilmData.list}")
            if (globalViewModel.globalFilmData.list != "myList") {
                globalViewModel.globalFilmData.id.let { globalViewModel.data.value?.get(it) }?.list = "myList"
                lifecycleScope.launch {
                    globalViewModel.database.updateList("myList", globalViewModel.globalFilmData.id)
                }
                updateData()
                globalViewModel.globalFilmData.list = "myList"
                binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmark_added)
                Toast.makeText(context, "Addes to MyList", Toast.LENGTH_SHORT).show()
            } else {
                globalViewModel.globalFilmData.id.let { globalViewModel.data.value?.get(it) }?.list = ""
                lifecycleScope.launch {
                    globalViewModel.database.updateList("", globalViewModel.globalFilmData.id)
                }
                updateData()
                globalViewModel.globalFilmData.list = ""
                binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmarkunchecked)
                Toast.makeText(context, "Removed from MyList", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class FilmyDetailFragmentViewModel : ViewModel()