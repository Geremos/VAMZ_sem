package com.example.vamz_sem.filmy


import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentFilmyBinding
import kotlinx.coroutines.launch

class FilmyFragment : BaseFragment<FragmentFilmyBinding, FilmyFragmentViewModel>() {
    private lateinit var adapter: AdapterFilmy
    override fun getViewModel(): Class<FilmyFragmentViewModel> = FilmyFragmentViewModel::class.java

    override fun getFragmentView(): Int = R.layout.fragment_filmy

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        updateData()

        globalViewModel.data.observe(viewLifecycleOwner,Observer{value ->
            adapter = AdapterFilmy(value, globalViewModel).apply {
                binding.filmyRecycler.layoutManager = layoutManager
                binding.filmyRecycler.adapter = this
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


        binding.searchViewFilmy.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
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

}

class FilmyFragmentViewModel() : ViewModel() {
}