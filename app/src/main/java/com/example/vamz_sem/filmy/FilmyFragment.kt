package com.example.vamz_sem.filmy


import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.PopupMenu
import android.widget.PopupWindow
import android.widget.SearchView
import androidx.lifecycle.Observer

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R

import com.example.vamz_sem.databinding.FragmentFilmyBinding
import kotlinx.coroutines.launch

class FilmyFragment : BaseFragment<FragmentFilmyBinding>() {
    private lateinit var adapter: AdapterFilmy
private lateinit var layoutManager: GridLayoutManager
private lateinit var popupWindow : PopupWindow
    override fun getFragmentView(): Int = R.layout.fragment_filmy

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateData()
        layoutManager = if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            GridLayoutManager(requireContext(),4 , GridLayoutManager.VERTICAL, false)
        } else {
            GridLayoutManager(requireContext(),2 , GridLayoutManager.VERTICAL, false)
        }

        val popupView = layoutInflater.inflate(R.layout.popup_window, null)
        val zanerRecycler = popupView.findViewById<RecyclerView>(R.id.zaner_recycler)
        zanerRecycler.layoutManager = LinearLayoutManager(context)

        zanerRecycler.adapter =
            globalViewModel.genres.value?.let { AdapterZaner(it,globalViewModel) }

        globalViewModel.genres.observe(viewLifecycleOwner, Observer { value ->
            zanerRecycler.adapter = AdapterZaner(value,globalViewModel)
        })
        popupWindow = PopupWindow(popupView, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
        popupWindow.isFocusable = true

        binding.menuFilterBtn.setOnClickListener {
            Log.d("popUP","open")
            showCustomPopup()
        }

        adapter = AdapterFilmy(emptyList(), globalViewModel).apply {
            binding.filmyRecycler.layoutManager = layoutManager
            binding.filmyRecycler.adapter = this
        }

        globalViewModel.filterGenres.observe(viewLifecycleOwner, Observer { value ->
            val filterList = globalViewModel.data.value?.filter { containsAnyGenre(it.genre!!, value) }
            adapter = AdapterFilmy(filterList!!, globalViewModel).apply {
                binding.filmyRecycler.layoutManager = layoutManager
                binding.filmyRecycler.adapter = this
            }
        })
        globalViewModel.data.observe(viewLifecycleOwner, Observer { value ->
            adapter = AdapterFilmy(value, globalViewModel).apply {
                binding.filmyRecycler.layoutManager = layoutManager
                binding.filmyRecycler.adapter = this
                extractGenres()
            }
        })





        binding.searchBtnId.setOnClickListener {
            if (binding.searchViewFilmy.visibility == View.VISIBLE) {
                binding.searchViewFilmy.visibility = View.GONE
                binding.searchBtnId.setImageResource(R.drawable.ic_search)

            } else {
                binding.searchViewFilmy.visibility = View.VISIBLE
                binding.searchBtnId.setImageResource(R.drawable.ic_close)
            }
        }


        binding.searchViewFilmy.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrEmpty())
                    lifecycleScope.launch {
                        val pomData = globalViewModel.database.getFilteredByInput(newText)
                        adapter.updateDataSet(pomData)
                    } else {
                    globalViewModel.data.value?.let { adapter.updateDataSet(it) }
                }
                return true
            }
        })


    }

    private fun extractGenres() {
        lifecycleScope.launch {
            val zanre = ArrayList<String>()
            for (data in globalViewModel.database.getAllFilmyData()) {
                val pomZanerList = data.genre?.split(", ")?.toList()
                if (pomZanerList != null) {
                    for (i in pomZanerList)
                        if (!zanre.contains(i)) {
                            zanre.add(i)
                        }
                }
            }
            globalViewModel.genres.value = zanre
        }
    }

    private fun containsAnyGenre(genre: String, stringsToFind: List<String>): Boolean {
        val genres = genre.split(", ").map { it.trim() }
        return genres.any { stringsToFind.contains(it) }
    }

    private fun showCustomPopup() {
        popupWindow.showAtLocation(requireView(), Gravity.START, 0, 0)
    }

    private fun dismissCustomPopup() {
        popupWindow.dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dismissCustomPopup()
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            layoutManager = GridLayoutManager(requireContext(),4 , GridLayoutManager.VERTICAL, false)
        }
        globalViewModel.data.observe(viewLifecycleOwner, Observer { value ->
            adapter = AdapterFilmy(value, globalViewModel).apply {
                binding.filmyRecycler.layoutManager = layoutManager
                binding.filmyRecycler.adapter = this
            }
        })

    }

}
