package com.example.vamz_sem.database

import androidx.room.*

@Dao
interface FilmyDao {

    /**
     * Metóda pre získanie všetkých filmových údajov z databázy.
     */
    @Query("SELECT * FROM filmy_data")
    suspend fun getAllFilmyData(): List<FilmyData>

    /**
     * Metóda pre vloženie nových filmových údajov do databázy.
     *
     * @param filmyData Filmové údaje na vloženie.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFilmyData(filmyData: FilmyData)

    /**
     * Metóda pre aktualizáciu existujúcich filmových údajov v databáze.
     *
     * @param filmyData Aktualizované filmové údaje.
     */
    @Update
    suspend fun updateData(filmyData: FilmyData)
    /**
     * Metóda pre aktualizáciu zoznamu filmu v databáze.
     *
     * @param list Označenie zoznamu (napr. "history", "myList").
     * @param id Identifikátor filmu.
     */
    @Query("UPDATE filmy_data SET list = :list WHERE id = :id")
    suspend fun updateList(list: String, id: Int)

    /**
     * Metóda pre získanie filtrovaných filmových údajov na základe zadaného vstupu.
     *
     * @param input Vstupný reťazec pre filtrovanie.
     * @return Filtrované filmové údaje.
     */
    @Query("SELECT * FROM filmy_data WHERE title LIKE '%' || :input || '%'")
    suspend fun getFilteredByInput(input: String) : List<FilmyData>

}
