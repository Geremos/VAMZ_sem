package com.example.vamz_sem.history

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentFilmyBinding
import com.example.vamz_sem.databinding.FragmentHistoryBinding
import com.example.vamz_sem.filmy.GlobalViewModel
import com.example.vamz_sem.filmy.history

class HistoryFragment : BaseFragment<FragmentHistoryBinding,HistoryFragmentViewmodel>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun getViewModel(): Class<HistoryFragmentViewmodel> = HistoryFragmentViewmodel::class.java

    override fun getFragmentView(): Int = R.id.historyFragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film = globalViewModel.globalFilmData
        Log.d("History Fragment", "History zaznam 5 pozicie ${history[4]}")
        AdapterHistory(history,globalViewModel).apply {
            binding.historyRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            binding.historyRecycler.adapter = this
        }
    }
}

class HistoryFragmentViewmodel() : ViewModel() {

}