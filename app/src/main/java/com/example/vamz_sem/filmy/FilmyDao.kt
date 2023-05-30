package com.example.vamz_sem.filmy

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface FilmyDao {
    @Query("SELECT * FROM filmy_data")
    suspend fun getAllFilmyData(): List<FilmyData>

    @Insert
    suspend fun insertFilmyData(filmyData: FilmyData)

    @Update
    suspend fun updateData(filmyData: FilmyData)

    @Query("UPDATE filmy_data SET list = :list WHERE id = :id")
    suspend fun updateList(list: String, id: Int)

    @Query("SELECT * FROM filmy_data WHERE title LIKE '%' || :input || '%'")
    suspend fun getFilteredByInput(input: String) : List<FilmyData>

    @Query("SELECT * FROM filmy_data WHERE genre = :genre")
    suspend fun getFilteredByGenre(genre: String) : List<FilmyData>

}
