package com.example.vamz_sem.filmy


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vamz_sem.MainActivity
import com.example.vamz_sem.MainApplication


class GlobalViewModel() : ViewModel() {
    var database: FilmyDao = MainApplication.database.filmyDataDao()
    val data: MutableLiveData<List<FilmyData>> by lazy {
        MutableLiveData<List<FilmyData>>()
    }
    var globalFilmData = FilmyData(null,null,null,null,null,null,null,null,null,null)
    var backFragmentId : Int = 0
}