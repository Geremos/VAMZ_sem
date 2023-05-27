package com.example.vamz_sem.my_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vamz_sem.BaseFragment
import com.example.vamz_sem.R
import com.example.vamz_sem.databinding.FragmentHistoryBinding
import com.example.vamz_sem.databinding.FragmentMyListBinding
import com.example.vamz_sem.filmy.myList


class MyListFragment : BaseFragment<FragmentMyListBinding, MyListFragmentViewModel>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        AdapterMyList(myList, globalViewModel).apply {
            binding.myListRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.myListRecycler.adapter = this
        }
    }

    override fun getViewModel(): Class<MyListFragmentViewModel> =
        MyListFragmentViewModel::class.java

    override fun getFragmentView(): Int = R.id.myListFragment
}

class MyListFragmentViewModel() : ViewModel()