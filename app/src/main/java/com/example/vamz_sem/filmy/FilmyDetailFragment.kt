package com.example.vamz_sem.filmy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentFilmyDetailBinding

class FilmyDetailFragment : BaseFragment<FragmentFilmyDetailBinding, FilmyDetailFragmentViewModel>(),BottomPanelClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmyDetailBinding.inflate(layoutInflater)
        binding.backIcon.setOnClickListener {
            Log.d("logBackFragmentId","${globalViewModel.backFragmentId}")
            findNavController().navigate(globalViewModel.backFragmentId)
        }

        binding.bottomPanel.clickListener = this
        if(history.contains(globalViewModel.globalFilmData)){
            binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.history_orange)
        }

        if(myList.contains(globalViewModel.globalFilmData)){
            binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmark_added)
        }

        val film = globalViewModel.globalFilmData

        Log.d("logFilmDataDetail","$film")
        if(film.id != null){
            binding.imageDetailId.setImageResource(film.filmImage!!)
            binding.titleDetailId.text = film.title
            binding.directorDetailId.text = formatText("Director",film.director)
            binding.writersDetailId.text = formatText("Writers",film.writers)
            binding.castDetailId.text = formatText("Cast",film.cast)
            binding.plotDetailId.text = film.plot
        }

        return binding.root
    }

    private fun formatText(header: String, list: List<String>?) : String {
        var data = "$header: "
        if (list != null) {
            for (film in list) {
                data += " $film, "
            }
        }
        return data
    }

    override fun getViewModel(): Class<FilmyDetailFragmentViewModel> = FilmyDetailFragmentViewModel::class.java

        override fun getFragmentView(): Int = R.layout.fragment_filmy_detail
    override fun addToHistory() {
        if(myList.contains(globalViewModel.globalFilmData)){
            myList.remove(globalViewModel.globalFilmData)
            history.add(globalViewModel.globalFilmData)
            binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.history_orange)
            binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmarkunchecked)
            Toast.makeText(context,"Addes to History from MyList", Toast.LENGTH_SHORT).show()
        } else {
            if (!history.contains(globalViewModel.globalFilmData)) {
                history.add(globalViewModel.globalFilmData)
                binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.history_orange)
                Toast.makeText(context, "Addes to History", Toast.LENGTH_SHORT).show()
            } else {
                binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.icon_history)
                history.remove(globalViewModel.globalFilmData)
                Toast.makeText(context, "Removed from history", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun addToMyList() {
        if(history.contains(globalViewModel.globalFilmData)){
            history.remove(globalViewModel.globalFilmData)
            myList.add(globalViewModel.globalFilmData)
            binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmark_added)
            binding.bottomPanel.addToHistoryBtn.setImageResource(R.drawable.icon_history)
            Toast.makeText(context,"Addes to MyList from History", Toast.LENGTH_SHORT).show()
        } else {
            if (!myList.contains(globalViewModel.globalFilmData)) {
                myList.add(globalViewModel.globalFilmData)
                binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmark_added)
                Toast.makeText(context, "Addes to MyList", Toast.LENGTH_SHORT).show()
            } else {
                binding.bottomPanel.addToMyListBtn.setImageResource(R.drawable.bookmarkunchecked)
                myList.remove(globalViewModel.globalFilmData)
                Toast.makeText(context, "Removed from MyList", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class FilmyDetailFragmentViewModel : ViewModel()