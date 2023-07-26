package com.example.moviemaster.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviemaster.data.convertors.Convertor
import com.example.moviemaster.data.models.entities.Movie

@TypeConverters(Convertor::class)
@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
}