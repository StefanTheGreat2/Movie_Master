package com.example.moviemaster.di.data_base

import android.content.Context
import androidx.room.Room
import com.example.moviemaster.common.constants.DB_NAME
import com.example.moviemaster.data.local.MovieDao
import com.example.moviemaster.data.local.MovieDatabase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideMovieDb(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(
        movieDb: MovieDatabase
    ): MovieDao {
        return movieDb.movieDao
    }


}