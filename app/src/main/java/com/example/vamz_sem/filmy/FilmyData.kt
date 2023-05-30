package com.example.vamz_sem.filmy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters


@Entity(tableName = "filmy_data")
data class FilmyData(
    @ColumnInfo(name = "title")
    val title : String?,
    @ColumnInfo(name = "filmImage")
    val filmImage: Int?,
    @ColumnInfo(name = "director")
    val director: String?,
    @ColumnInfo(name = "writers")
    val writers: String?,
    @ColumnInfo(name = "cast")
    val cast: String?,
    @ColumnInfo(name = "language")
    val language : String?,
    @ColumnInfo(name = "Country")
    val Country : String?,
    @ColumnInfo(name = "plot")
    val plot : String?,
    @ColumnInfo(name = "genre")
    val genre : String?,
    @ColumnInfo(name = "list")
    var list: String? = "",
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
)