package com.example.vamz_sem.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentHistoryBinding
import com.example.vamz_sem.filmy.FilmyData

class HistoryFragment : BaseFragment<FragmentHistoryBinding,HistoryFragmentViewModel>() {
    private lateinit var adapter: AdapterHistory
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
        updateData()
        val pomData = ArrayList<FilmyData>()

        for(data in globalViewModel.data.value!!){
           if(data.list == "history"){
               pomData.add(data)
           }
        }

        AdapterHistory(pomData,globalViewModel).apply {
            binding.historyRecycler.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            binding.historyRecycler.adapter = this
        }
    }
}

class HistoryFragmentViewModel() : ViewModel()