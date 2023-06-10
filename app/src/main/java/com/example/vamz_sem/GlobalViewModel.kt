package com.example.vamz_sem


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vamz_sem.database.FilmyDao
import com.example.vamz_sem.database.FilmyData

/**
 * Globalny view model používaný v aplikácii na zdieľanie dát medzi fragmentami.
 */
class GlobalViewModel() : ViewModel() {
    var database: FilmyDao = MainApplication.database.filmyDataDao()
    /**
     * LiveData obsahujúce zoznam filmových dát.
     */
    val data: MutableLiveData<List<FilmyData>> by lazy {
        MutableLiveData<List<FilmyData>>()
    }
    /**
     * LiveData obsahujúce žánre filmov podľa ktorých sa filtruje.
     */
    val filterGenres: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }

    /**
     * LiveData obsahujúce zoznam všetkých žánrov filmov.
     */
    val genres: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>()
    }
    /**
     * Dáta s aktuálne "nakliknutým" filmom.
     */
    var globalFilmData = FilmyData(null,null,null,null,null,null,null,null,null,null)
    var backFragmentId : Int = 0
}