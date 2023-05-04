package com.example.vamz_sem.koin

import com.example.vamz_sem.databinding.ActivityMainBinding
import com.example.vamz_sem.filmy.GlobalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vamzKoin = module {
    viewModel { GlobalViewModel() }
}