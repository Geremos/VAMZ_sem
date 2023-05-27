package com.example.vamz_sem.filmy

var filmList = ArrayList<FilmyData>()
var myList = ArrayList<FilmyData>()
var history = ArrayList<FilmyData>()

data class FilmyData(
    var title : String?,
    var filmImage: Int?,
    var director: List<String>?,
    var writers: List<String>?,
    var cast: List<String>?,
    //var language : String?,
    //var Country : String?,
    var plot : String?,
    var id : Int? = filmList.size
)
