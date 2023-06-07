package com.example.vamz_sem.filmy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Dátová trieda reprezentujúca informácie o filme.
 *
 * @param title Názov filmu.
 * @param filmImage Obrázok filmu.
 * @param director Režisér filmu.
 * @param writers Scenáristi filmu.
 * @param cast Obsadenie filmu.
 * @param language Jazyk filmu.
 * @param country Krajina pôvodu filmu.
 * @param plot Zápletka filmu.
 * @param genre Žánre filmu oddelené čiarkou.
 * @param list Označenie zoznamu, v ktorom sa film nachádza (napr. "history", "myList").
 * @param id Identifikátor filmu v databáze.
 */
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
    val country : String?,
    @ColumnInfo(name = "plot")
    val plot : String?,
    @ColumnInfo(name = "genre")
    val genre : String?,
    @ColumnInfo(name = "list")
    var list: String? = "",
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0
)