package com.example.moviemaster.di.reposistory

import com.example.moviemaster.data.local.MovieDao
import com.example.moviemaster.data.remote.MovieService
import com.example.moviemaster.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMovieRepo(
        movieService: MovieService,
        movieDao: MovieDao
    ): MovieRepository = MovieRepository(movieService, movieDao)

}