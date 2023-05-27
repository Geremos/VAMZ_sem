package com.example.vamz_sem.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentHistoryBinding
import com.example.vamz_sem.filmy.history

class HistoryFragment : BaseFragment<FragmentHistoryBinding,HistoryFragmentViewModel>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun getViewModel(): Class<HistoryFragmentViewModel> = HistoryFragmentViewModel::class.java

    override fun getFragmentView(): Int = R.id.historyFragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AdapterHistory(history,globalViewModel).apply {
            binding.historyRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            binding.historyRecycler.adapter = this
        }
    }
}

class HistoryFragmentViewModel() : ViewModel()