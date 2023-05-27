package com.example.vamz_sem.filmy


import android.os.Bundle
import android.view.*
import android.widget.SearchView

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentFilmyBinding
import okhttp3.OkHttpClient
import okhttp3.Request

class FilmyFragment : BaseFragment<FragmentFilmyBinding, FilmyFragment.FilmyFragmentViewModel>() {

    override fun getViewModel(): Class<FilmyFragmentViewModel> = FilmyFragmentViewModel::class.java

    override fun getFragmentView(): Int = R.layout.fragment_filmy

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)


        AdapterFilmy(filmList, globalViewModel).apply {
            binding.filmyRecycler.layoutManager = layoutManager
            binding.filmyRecycler.adapter = this
        }

        binding.searchBtnId.setOnClickListener {
            if (binding.searchViewFilmy.visibility == View.VISIBLE) {
                binding.searchViewFilmy.visibility = View.GONE
                binding.searchBtnId.setImageResource(R.drawable.ic_search)

            } else {
                binding.searchViewFilmy.visibility = View.VISIBLE
                binding.searchBtnId.setImageResource(R.drawable.ic_close)
            }
        }

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://imdb8.p.rapidapi.com/auto-complete?q=lord")
            .get()
            .addHeader("X-RapidAPI-Key", "b7ba4e990cmshb5ea839ebe2e918p147e8ajsn9a24d4423523")
            .addHeader("X-RapidAPI-Host", "imdb8.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()

        binding.searchViewFilmy.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                AdapterFilmy(filterData(query), globalViewModel).apply {
                    binding.filmyRecycler.layoutManager = layoutManager
                    binding.filmyRecycler.adapter = this
                }
                binding.searchViewFilmy.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                AdapterFilmy(filterData(newText), globalViewModel).apply {
                    binding.filmyRecycler.layoutManager = layoutManager
                    binding.filmyRecycler.adapter = this
                }
                return true
            }
        })
    }

    private fun filterData(newText: String?) : ArrayList<FilmyData> {
        val  filtered : ArrayList<FilmyData> = ArrayList<FilmyData>()
        for (data in filmList){
            if (data.title!!.contains(newText ?: "", ignoreCase = true)) {
                filtered.add(data)
            }
        }
        return filtered
    }


    class FilmyFragmentViewModel() : ViewModel() {
    }
}

