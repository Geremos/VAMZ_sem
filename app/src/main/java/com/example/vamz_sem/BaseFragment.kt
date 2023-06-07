package com.example.vamz_sem

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.vamz_sem.filmy.FilmyData
import com.example.vamz_sem.filmy.GlobalViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment<T : ViewDataBinding> : Fragment() {
    protected lateinit var binding: T
    val globalViewModel by sharedViewModel<GlobalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,getFragmentView(),container,false)
        return binding.root
    }

    abstract fun getFragmentView(): Int

    fun updateData(){
        lifecycleScope.launch {
            globalViewModel.data.value =  globalViewModel.database.getAllFilmyData()
            globalViewModel.data.value =  globalViewModel.data.value!!.sortedBy { it.id }
        }
    }
}