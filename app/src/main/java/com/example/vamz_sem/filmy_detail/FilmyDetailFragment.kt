package com.example.vamz_sem.filmy_detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.MainApplication
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentFilmyDetailBinding
import kotlinx.coroutines.launch


/**
 * Fragment pre zobrazenie detailov filmu.
 */
class FilmyDetailFragment :
    BaseFragment<FragmentFilmyDetailBinding>(),
    BottomPanelClickListener {

    /**
     * Metóda, ktorá sa volá po vytvorení zobrazenia fragmentu.
     * Nastavuje elementy layoutu na zobrazenie informácií o filme
     *
     * @param view [View] zobrazenie fragmentu
     * @param savedInstanceState [Bundle] stav fragmentu
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        binding.imageDetailId.setImageResource(film.filmImage!!)
        binding.titleDetailId.text = film.title
        binding.directorDetailId.text = film.director
        binding.writersDetailId.text = film.writers
        binding.castDetailId.text = film.cast
        binding.zanerId.text = film.genre
        binding.jazykId.text = film.language
        binding.krajinaId.text = film.country
        binding.plotDetailId.text = film.plot

    }

    /**
     * Metóda pre získanie ID layoutu fragmentu.
     *
     * @return ID layoutu fragmentu.
     */
    override fun getFragmentView(): Int = R.layout.fragment_filmy_detail

    /**
     * Metóda volaná pri pridávaní filmu do "histórie" videných filmov.
     */
    override fun addToHistory() {
        if (globalViewModel.globalFilmData.list == "myList") {
            Log.d("databaseList", "${globalViewModel.globalFilmData.list}")
            globalViewModel.globalFilmData.list = "history"

            lifecycleScope.launch {
                Log.d("databaseUpdate",globalViewModel.globalFilmData.id.toString())
                globalViewModel.database.updateData(globalViewModel.globalFilmData)
            }
            updateData()
            binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.history_orange)
            binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmarkunchecked)

            context?.let { MainApplication.showToast(it,R.drawable.ic_add,"Addes to History from MyList") }
        } else {
            Log.d("databaseList", "${globalViewModel.globalFilmData.list}")
            if (globalViewModel.globalFilmData.list != "history") {
                globalViewModel.globalFilmData.id.let { globalViewModel.data.value?.get(it - 1) }?.list = "history"
                lifecycleScope.launch {
                    Log.d("databaseUpdate", globalViewModel.globalFilmData.id.toString())
                    globalViewModel.database.updateList("history", globalViewModel.globalFilmData.id)
                }
                updateData()
                globalViewModel.globalFilmData.list = "history"
                binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.history_orange)
                context?.let { MainApplication.showToast(it,R.drawable.ic_add,"Addes to History") }
            } else {
                globalViewModel.globalFilmData.id.let { globalViewModel.data.value?.get(it - 1) }?.list =
                    ""
                lifecycleScope.launch {

                    Log.d("databaseUpdate", globalViewModel.globalFilmData.id.toString())
                    globalViewModel.database.updateList("", globalViewModel.globalFilmData.id)
                }
                updateData()
                globalViewModel.globalFilmData.list = ""
                context?.let { MainApplication.showToast(it,R.drawable.ic_remove,"Removed from history") }
                binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.icon_history)
            }
        }
    }

    /**
     * Metóda volaná pri pridávaní filmu do zoznamu Môj zoznam.
     */
    override fun addToMyList() {
        if (globalViewModel.globalFilmData.list == "history") {

            Log.d("databaseUpdate", globalViewModel.globalFilmData.id.toString())
            Log.d("databaseList", "${globalViewModel.globalFilmData.list}")
            globalViewModel.globalFilmData.list = "myList"

            Log.d("databaseUpdate", globalViewModel.globalFilmData.id.toString())
            lifecycleScope.launch {
                globalViewModel.database.updateData(globalViewModel.globalFilmData)
            }
            updateData()
            binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmark_added)
            binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.icon_history)
            context?.let { MainApplication.showToast(it,R.drawable.ic_add,"Addes to MyList from History") }
        } else {
            Log.d("databaseList", "${globalViewModel.globalFilmData.list}")
            if (globalViewModel.globalFilmData.list != "myList") {
                globalViewModel.globalFilmData.id.let { globalViewModel.data.value?.get(it - 1) }?.list = "myList"
                Log.d("databaseUpdate", globalViewModel.globalFilmData.id.toString())
                lifecycleScope.launch {
                    Log.d("databaseUpdateIn", globalViewModel.globalFilmData.id.toString())
                    globalViewModel.database.updateList("myList", globalViewModel.globalFilmData.id)
                }
                updateData()
                globalViewModel.globalFilmData.list = "myList"
                binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmark_added)
                context?.let { MainApplication.showToast(it,R.drawable.ic_add,"Addes to MyList") }
            } else {
                globalViewModel.globalFilmData.id.let { globalViewModel.data.value?.get(it - 1) }?.list = ""
                Log.d("databaseUpdate", globalViewModel.globalFilmData.id.toString())
                lifecycleScope.launch {
                    Log.d("databaseUpdateIn", globalViewModel.globalFilmData.id.toString())
                    globalViewModel.database.updateList("", globalViewModel.globalFilmData.id)
                }
                updateData()
                globalViewModel.globalFilmData.list = ""
                binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmarkunchecked)
                context?.let { MainApplication.showToast(it,R.drawable.ic_remove,"Removed from MyList") }
            }
        }
    }
}
