package com.example.moviemaster.data.local

import androidx.room.*
import com.example.moviemaster.common.constants.DB_NAME
import com.example.moviemaster.data.models.entities.Movie
import kotlinx.coroutines.flow.Flow
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMovie(movie: Movie)

    @Delete
    fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM $DB_NAME ")
    fun getAllMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM $DB_NAME WHERE id = :id")
    suspend fun getMovie(id: Long): Movie?
}