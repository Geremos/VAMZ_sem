package com.example.vamz_sem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vamz_sem.filmy.GlobalViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

abstract class BaseFragment<T : ViewDataBinding, VM : ViewModel> : Fragment() {
    protected lateinit var binding: T
    protected lateinit var viewModel: VM
    val globalViewModel by sharedViewModel<GlobalViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,getFragmentView(),container,false)
        viewModel = ViewModelProvider(this)[getViewModel()]
        return binding.root
    }

    abstract fun getViewModel(): Class<VM>

    abstract fun getFragmentView(): Int
}