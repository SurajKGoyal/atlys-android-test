package com.example.atlystest.di

import android.content.Context
import androidx.room.Room
import com.example.atlystest.db.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): MovieDatabase{
        return Room.databaseBuilder(
            context = appContext,
            MovieDatabase::class.java,
            "movie_database"
        ).build()
    }

    @Provides
    fun provideMovieDao(
        movieDatabase: MovieDatabase
    ) = movieDatabase.movieDao()
}